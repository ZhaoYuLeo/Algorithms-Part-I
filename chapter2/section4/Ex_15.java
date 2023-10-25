package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Design a linear-time certification algorithm to check whether an array pq[] is a min-oriented heap.
 */
public class Ex_15 {
    public static boolean check(Comparable[] pq) {
        int N = pq.length - 1;
        int i = 1;
        // when the node has child(one or two)
        while (2 * i <= N) {
            // check the left child
            if (less(i * 2, i, pq)) {
                return false;
            }
            // check the right child
            if (2 * i + 1 <= N && less(i * 2 + 1, i, pq)) {
                return false;
            }
            // move on
            i += 1;
        }
        return true;
    }

    private static boolean less(int i, int j, Comparable[] pq) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public static void main(String[] args) {
        Integer[] pq = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Integer[] pq1 = {-1, 0, 2, 1, 6, 2, 5, 3, 7, 8, 9, 10};
        StdOut.println(check(pq1));
    }
}
