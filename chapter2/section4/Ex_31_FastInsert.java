package chapter2.section4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a compare-based implementation of the MinPQ API such that insert uses ~ log log N compares and delete
 * the minimum uses ~2logN compares.
 * Use binary search on parent pointers to find the ancestor in swim()
 */
public class Ex_31_FastInsert<Key extends Comparable<Key>> {
    public Key[] pq;   // heap-ordered complete binary tree
    private int N = 0;      //    in pq[1..N] with pq[0] unuse

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_31_FastInsert(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * Create an empty priority queue
     */
    public Ex_31_FastInsert() {
        this(1);
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(Key key) {
        if (N == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = key;
        swim(N);
    }

    public Key delMin() {
        Key min = pq[1];        // Retrieve min key from top.
        exch(1, N--);           // Exchange with last item.
        pq[N + 1] = null;       // Avoid loitering.
        sink(1);                // Restore heap property.
        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return min;
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
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
     * Violate the heap order because the node's parent's key becomes greater than that node's key.
     * We fix the violation by exchanging the node with its parent.
     */
    private void swim(int k) {
        if (k == 1) {
            return;
        }
        int ceiling = binarySearch(k);
        Key temp = pq[k];
        while(k > ceiling) {
            exch(k / 2, k);
            k = k / 2;
        }
        pq[k] = temp;
    }

    /**
     * Find the smallest parent'key that is greater than the given key(ceiling)
     */
    private int binarySearch(int item) {
        int hi = log2(item);    // move to the root
        int lo = 0;             // stay on the leaf
        while (lo < hi) {
            int mid = hi + (lo - hi) / 2;
            int k = item / ((int)Math.pow(2, mid));
            if (greater(k, item)) {
                lo = mid; 
            } else {
                hi = mid - 1;
            }
        }
        return item / ((int)Math.pow(2, lo));
    } 

    // private static int binarySearchStd(Comparable[] a, Comparable key) {
    //     int N = a.length;
    //     int lo = 0;
    //     int hi = N - 1;
    //     while (lo < hi) {
    //         int mid = lo + (hi - lo) / 2;
    //         StdOut.println("mid is " + mid + "; lo is " + lo + "; hi is " + hi);
    //         if (a[mid].compareTo(key) > 0) {
    //             hi = mid;  // keep hi greater than the searching key
    //         } else {
    //             lo = mid + 1;  // move to the nearnest maximum value
    //         }
    //     }
    //     StdOut.println("return " + lo + ", " + hi);
    //     return lo;
    // }

    /**
     * Return the floor of the log base 2 of N
     */
    private int log2(int N) {
        return (int) (Math.log(N) / Math.log(2));
    }

    /**
     * Top-down reheapify.
     * Violate the heap order because the node's key becomes larger than one or both of that node's
     * children's keys. We fix the violation by exchanging the node with the smaller of its two children.
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) {
                j += 1;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /** For test */
    public void printPQ() {
        for (Key i : pq) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Ex_31_FastInsert<Integer> pq = new Ex_31_FastInsert<>();
        pq.insert(1);
        pq.insert(2);
        pq.insert(4);
        for (int i = 5; i < 29; i += 1) {
            pq.insert(i);
        }
        pq.insert(3);

        pq.printPQ();
    }

}

