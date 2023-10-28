package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class HeapMinPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // heap-ordered complete binary tree
    private int N = 0;      //    in pq[1..N] with pq[0] unuse

    /**
     * Create a priority queue of initial capacity max
     */
    public HeapMinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * Create an empty priority queue
     */
    public HeapMinPQ() {
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
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
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

    public static void main(String[] args) {
        // Print the Top M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        HeapMinPQ<Transaction> pq = new HeapMinPQ<>(M + 1);
        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) {
                pq.delMin();    // Remove minimum if M+1 entries on the PQ.
            }
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }

}

