package chapter2.section4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Index priority-queue implementation. Implement the basic operations in the index priority-queue
 * API on page 320 by modifying ALGORITHM 2.6 as follows: Change pq[] to hold indices, add an array
 * keys[] to hold the key values, and add an array qp[] that is the inverse of qp[] - qp[i] gives
 * the position of i in pq[] (the index j such that pql[j] is i). Then modify the code in ALGORITHM
 * 2.6 to maintain these data structures. Use the convention that qp[i] = -1 if i is not on the
 * queue, and include a method contains() that tests this condition. You need to modify the helper
 * methods exch() and less() but not sink() or swim().
 */
public class Ex_33_IndexPriorityQueue<Item extends Comparable<Item>> {
    public Item[] keys;  // the key values
    public int[] pq;    // indices of the keys
    public int[] qp;    // the inverse of pq: pq[qp[i]] = i
    public int N = 0;   // the number of elements

    /**
     * Create an empty priority queue
     */
    public Ex_33_IndexPriorityQueue(int maxN) {
        int capacity = maxN + 1;
        keys = (Item[]) new Comparable[capacity];
        pq = new int[capacity];
        qp = new int[capacity];
        for (int i = 0; i < capacity; i += 1) {
            qp[i] = -1;
        }
    }
    
    /**
     * Top-down reheapify
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) {
                j += 1;
            }
            if (less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * Bottom-up reheapify
     */
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;

        qp[temp] = j;
        qp[pq[i]] = i;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    /**
     * change the item associated with k to item
     */
    public void change(int k, Item item) {
        // int cmp = item.compareTo(keys[k]); 
        // keys[k] = item;
        // int index = qp[k];
        // if (index == 0) {
        //     return;
        // }
        // if (cmp < 0) {
        //     swim(index);
        //     return;
        // }
        // sink(index);
        keys[k] = item;
        // Better. Swim and sink will check
        swim(qp[k]);
        sink(qp[k]);
    }

    /**
     * Insert item; associate it with k
     */
    public void insert(int k, Item item) {
        // resize
        N += 1;
        pq[N] = k;
        qp[k] = N;
        keys[k] = item;
        swim(N);
    }

    /**
     * is k associated with some item?
     */
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /**
     * remove k and its associated item
     */
    public void delete(int k) {
        exch(k, N--);
        swim(qp[k]);
        sink(qp[k]);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        // pq[N + 1] = null; // remove key stored in pq
    }

    /**
     * return a minimal item
     */
    public Item min() {
        return keys[pq[1]];
    }

    /**
     * return a minimal item's index
     */
    public int minIndex() {
        return pq[1];
    }

    /**
     * remove a minimal item and return its index
     */
    public int delMin() {
        int minIndex = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null; 
        qp[pq[N + 1]] = -1;
        return minIndex;
    }

    /**
     * is the priority queue empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * number of items in the priority queue
     */
    public int size() {
        return N;
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i += 1) {
            streams[i] = new In(args[i]);
        }
        Ex_33_IndexPriorityQueue<String> pq = new Ex_33_IndexPriorityQueue<String>(N);
        for (int i = 0; i < N; i += 1) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
        while (!pq.isEmpty()) {
            StdOut.println(pq.min());
            int i = pq.delMin();
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
            if (StdRandom.random() < 0.4) {
                int key = StdRandom.uniform(N);
                StdOut.println("delete " + key);
                if (pq.contains(key)) {
                    pq.delete(key);
                }
            }
        }
    }
}
