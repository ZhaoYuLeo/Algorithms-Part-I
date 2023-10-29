package chapter2.section4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * Design a data type that supports insert in logarithmic time, find the median in constant time,
 * and delete the median in logarithmic time.
 */
public class Ex_30_DynamicMedianFinding<Key extends Comparable<Key>> {
    private HeapMaxPQ<Key> max;      // max-oriented heap for keys less than the median key
    private HeapMinPQ<Key> min;      // min-oriented heap for keys greater than the median key
    private Key median;              // median item in the sequences

    /** Initialize */
    public Ex_30_DynamicMedianFinding() {
        max = new HeapMaxPQ<>();
        min = new HeapMinPQ<>();
        median = null;
    }

    /** Return the number of items in this data type */
    public int size() {
        if (median == null) {
            return 0;
        }
        return 1 + max.size() + min.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /** Insert item in logarithmic time */
    public void insert(Key key) {
        if (median == null) {
            median = key;
            return;
        }
        if (max.size() == min.size()) {
            if (less(key, median)) {
                max.insert(key);
            } else {
                min.insert(key);
            }
        } else if (max.size() > min.size()) {
            if (less(key, median)) {
                min.insert(median);
                max.insert(key);
                median = max.delMax();
            } else {
                min.insert(key);
            }
        } else {
            if (less(key, median)) {
                max.insert(key);
            } else {
                max.insert(median);
                min.insert(key);
                median = min.delMin();
            }
        }
    }

    /** Find the median in consant time */
    public Key getMedian() {
        return median;
    }

    /** Delete the median in logarithmic time */
    public Key delMedian() {
        Key temp = median;
        if (max.size() < min.size()) {
            median = min.delMin();
        } else {
            median = max.delMax();
        }
        return temp;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Ex_30_DynamicMedianFinding<Integer> pq = new Ex_30_DynamicMedianFinding<>();
        while (!StdIn.isEmpty()) {
            pq.insert(StdIn.readInt());
        }
        StdOut.println("median: " + pq.getMedian());
        Queue<Integer> queue = new Queue<>();
        while (!pq.isEmpty()) {
            queue.enqueue(pq.delMedian());
        }
        for (Integer t: queue) {
            StdOut.printf(t + " ");
        }
        StdOut.println();
    }

}

