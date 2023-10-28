package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * Add a min() method to MaxPQ. Use constant time and constant extra space.
 */
public class Ex_27_FindMin<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] pq;   // heap-ordered complete binary tree
    private int N = 0;      //    in pq[1..N] with pq[0] unuse
    private Key min;    // the minimum item


    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_27_FindMin(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public Ex_27_FindMin() {
        this(1);
    }

    /**
     * Return the minimum item
     */
    public Key min() {
        return this.min;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        if (N == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = key;
        swim(N);

        // Update minimum after insert()
        if (min == null || less(key, min)) {
            min = key;
        }
    }

    @Override
    public Key delMax() {
        Key max = pq[1];        // Retrieve max key from top.
        exch(1, N--);           // Exchange with last item.
        pq[N + 1] = null;       // Avoid loitering.
        sink(1);                // Restore heap property.
        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        if (isEmpty()) {
            this.min = null;    // We will never delete the min unless there are no extra item.
        }
        return max;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    // resize pq to the given capacity
    private void resize(int capacity) {
        assert capacity > N : "lose data";
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i += 1) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Bottom-up reheapify.
     * Violate the heap order because the node's key becomes larger than that node's parent's key.
     * We fix the violation by exchanging the node with its parent.
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * Top-down reheapify.
     * Violate the heap order because the node's key becomes smaller than one or both of that node's
     * children's keys. We fix the violation by exchanging the node with the larger of its two children.
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j += 1;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        // Print the bottom M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        Ex_27_FindMin<Transaction> pq = new Ex_27_FindMin<>(M + 1);
        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            StdOut.println(pq.min());
            if (pq.size() > M) {
                pq.delMax();    // Remove maximum if M+1 entries on the PQ.
            }
        }

        StdOut.println();

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }

}

