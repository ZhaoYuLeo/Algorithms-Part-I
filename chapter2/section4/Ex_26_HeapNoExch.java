package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class Ex_26_HeapNoExch<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private Key[] pq;   // heap-ordered complete binary tree
    private int N = 0;      //    in pq[1..N] with pq[0] unuse

    /**
     * Create a priority queue of initial capacity max
     */
    public Ex_26_HeapNoExch(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * Create a priority queue without given capacity
     */
    public Ex_26_HeapNoExch() {
        this(1);
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
    }

    @Override
    public Key delMax() {
        Key max = pq[1];        // Retrieve max key from top.
        pq[1] = pq[N--];        // Exchange with last item.
        pq[N + 1] = null;       // Avoid loitering.
        sink(1);         // Restore heap property.
        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
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
        Key temp = pq[k];
        while (k > 1 && less(pq[k / 2], temp)) {
            pq[k] = pq[k / 2];
            k = k / 2;
        }
        pq[k] = temp;
    }

    /**
     * Top-down reheapify.
     * Violate the heap order because the node's key becomes smaller than one or both of that node's
     * children's keys. We fix the violation by exchanging the node with the larger of its two children.
     */
    private void sink(int k) {
        Key temp = pq[k];
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq[j], pq[j + 1])) {
                j += 1;
            }
            if (!less(temp, pq[j])) {
                break;
            }
            pq[k] = pq[j];
            k = j;
        }
        pq[k] = temp;
    }

    public static void main(String[] args) {
        // Print the bottom M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        Ex_26_HeapNoExch<Transaction> pq = new Ex_26_HeapNoExch<>(M + 1);
        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) {
                pq.delMax();    // Remove maximum if M+1 entries on the PQ.
            }
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }

}

