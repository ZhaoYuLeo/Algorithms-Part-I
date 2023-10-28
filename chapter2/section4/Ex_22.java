package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Prove that the number of array accesses required is proportional to at most log N
 * for insert, delete the maximum, in an amortized sense.
 */
public class Ex_22<Key extends Comparable<Key>> implements MaxPQ<Key> {
    protected Key[] pq;   // heap-ordered complete binary tree
    protected int N = 0;      //    in pq[1..N] with pq[0] unuse
    private final int INIT_CAPCITY = 1;

    private int accesses = 0; // the number of array accesses
    private int compare = 0; // the number of compare

    /**
     * Create a priority queue without given capacity
     */
    public Ex_22() {
        this(10000);
    }

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_22(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        this.accesses = 0;
        this.compare = 0;

        if (N == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++N] = key;

        this.accesses += 1;

        swim(N);
    }

    @Override
    public Key delMax() {
        this.accesses = 0;
        this.compare = 0;

        Key max = pq[1];        // Retrieve max key from top.
        exch(1, N--);           // Exchange with last item.
        pq[N + 1] = null;       // Avoid loitering.
        sink(1);                // Restore heap property.
        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        this.accesses += 2;

        return max;
    }

    private boolean less(int i, int j) {
        this.accesses += 2;

        this.compare += 1;
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;

        this.accesses += 4;
    }

    // resize pq to the given capacity
    private void resize(int capacity) {
        assert capacity > N : "lose data";

        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i += 1) {
            temp[i] = pq[i];

            this.accesses += 2;
        }
        pq = temp;
    }

    /**
     * Bottom-up reheapify.
     * Violate the heap order because the node's key becomes larger than that node's parent's key.
     * We fix the violation by exchanging the node with its parent.
     */
    protected void swim(int k) {
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

    private void draw(double avgAccess, double avgCompare, int i, int rate) {
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(i, accesses);

        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.point(i, compare);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(i, avgCompare);

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(i, avgAccess);

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.point(i, rate * (Math.log(i) / Math.log(2)));
    }


    public static void main(String[] args) {
        // Print the bottom M lines in the input stream.
        int N = Integer.parseInt(args[0]);
        Ex_22<Double> pq = new Ex_22<>();
        int k = (int)(Math.log(N) / Math.log(2));
        int height = 150;

        StdDraw.setXscale(-1, N + 1);
        StdDraw.setYscale(-1, height + k);
        StdDraw.setPenRadius(0.003);
        // Randomly insert
        int total = 0;
        int totalCompare = 0;
        for (int i = 1; i < N; i += 1) {
            pq.insert(StdRandom.uniform());

            total += pq.accesses;  // O(log N)
            totalCompare += pq.compare;
            pq.draw(1.0 * total / i, 1.0 * totalCompare / i, i, 1);
        }

        // Reandomly delete the max
        total = 0;
        totalCompare = 0;
        StdDraw.setYscale(- 1 - height / 3,  height + k);
        pq = new Ex_22<>();
        for (int i = 1; i < N; i += 1) {
            for (int j = i; j > 0; j -= 1) {
                pq.insert(StdRandom.uniform());
            }
            pq.delMax();

            total += pq.accesses;
            totalCompare += pq.compare;  // O(log N)
            pq.draw(1.0 * total / i, 1.0 * totalCompare / i, i, 13);
        }
    }

}

