package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

public class Ex_03_OrderedArrayMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] pq;   // ordered array stored the elements in the priority queue
    private int N;      // the number of keys is the priority queue

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_03_OrderedArrayMaxPQ(int max) {
        pq = (Key[]) new Comparable[max];
        N = 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        // TODO: Why not delete the smallest value when the queue is almost full?
        if (pq.length == N) {
            throw new RuntimeException("PQ is full");
        }
        int i;
        for (i = N - 1; i >= 0 && less(key, pq[i]); i -= 1) {
            pq[i + 1] = pq[i];
        }
        pq[i + 1] = key;
        N += 1; 
    }

    @Override
    public Key delMax() {
        return pq[--N];
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Ex_03_OrderedArrayMaxPQ<Integer> pq = new Ex_03_OrderedArrayMaxPQ<>(10);
        pq.insert(18);
        pq.insert(2);
        pq.insert(20);
        pq.insert(12);
        pq.insert(17);
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }

}

