package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Implement the constructor for MaxPQ that takes an array of items as argument, using
 * the bottom-up heap construction method described on page 323 in the text.
 * NlogN, by proceeding from left to right through the array, using swim() to ensure that
 * the items to the left of the scanning pointer make up a heap-ordered complete tree,
 * like successive priority queue insertions.
 */
public class Ex_19<Key extends Comparable<Key>> extends HeapMaxPQ<Key> {

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_19(Comparable[] a) {
        super(a.length);
        // danger, should be private, better not change them
        N = a.length;
        System.arraycopy(a, 0, pq, 1, N);
        for (int k = N; k > 1; k -= 1) {
            swim(k);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {5, 16, 2, 0, 12, 20, 15, 7, 18, 17};
        Ex_19<Integer> pq = new Ex_19<>(a);
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }

}

