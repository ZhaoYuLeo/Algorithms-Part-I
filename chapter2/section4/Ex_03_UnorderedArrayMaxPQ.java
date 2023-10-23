package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

public class Ex_03_UnorderedArrayMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] pq;   // unordered array stored the elements in the priority queue
    private int N;      // the number of keys is the priority queue

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_03_UnorderedArrayMaxPQ(int max) {
        pq = (Key[]) new Comparable[max];
        N = 0;
    }

    @Override
    public int size() {
        return N;
    }

    // You can add resizing-array code to ensure the data structure is always at least one-quarter full and never overflows.
    // private void resize(int capacity) {}

    @Override
    public void insert(Key key) {
        pq[N++]  = key;
    }

    @Override
    public Key delMax() {
        int max = 0;
        for (int i = 0; i < N; i += 1) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, N - 1);

        return pq[--N];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        Ex_03_UnorderedArrayMaxPQ<Integer> pq = new Ex_03_UnorderedArrayMaxPQ<>(10);
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

