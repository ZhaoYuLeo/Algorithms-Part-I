package chapter2.section4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Implement a version of heapsort based on Floyd's sink-to-the-bottom-and-then-swim idea, as described in the
 * text. Count the number of compares used by your program and the number of compares used by the standard
 * implementation, for randomly ordered distinct keys with N = 10^3, 10^6, and 10^9.
 */
public class Ex_40_FloydsMethod {

    private static int compares = 0;

    public static void sort(Comparable[] a) {
        compares = 0;

        int N = a.length;
        for (int k = N / 2; k >= 1; k -= 1) {
            sink(a, k, N);
        }

        int k = N;
        while (k >= 1) {
            exch(a, 1, k--);
            sinkToBottomAndSwim(a, 1, k);
        }
    }

    public static void stdSort(Comparable[] a) {
        compares = 0;

        int N = a.length;
        for (int k = N / 2; k >= 1; k -= 1) {
            sink(a, k, N);
        }

        int k = N;
        while (k > 1) {
            exch(a, 1, k--);
            sink(a, 1, k);
        }
    }

    // off by 1 mapping [1, ..., N] to [0, ..., N - 1]
    private static boolean less(Comparable[] a, int i, int j) {
        compares += 1;
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    /**
     * Top-down reheapify.
     * Violate the heap order because the node's key becomes smaller than one or both of that node's
     * children's keys. We fix the violation by exchanging the node with the larger of its two children.
     */
    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j += 1;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    /**
     * Avoiding the check for whether the item has reached its position since most items go to the bottom.
     * Promoting the larger of the two children until the bottom is reached, then moving back up the heap
     * to the proper position.
     */
    private static void sinkToBottomAndSwim(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j += 1;
            }
            exch(a, k, j);
            k = j;
        }
        swim(a, k);
    }

    /**
     * Bottom-up reheapify.
     * Violate the heap order because the node's key becomes larger than that node's parent's key.
     * We fix the violation by exchanging the node with its parent.
     */
    private static void swim(Comparable[] a, int k) {
        while (k > 1 && less(a, k / 2, k)) {
            exch(a, k / 2, k);
            k = k / 2;
        }
    }

    public static void main(String[] args) {
        StdOut.printf("%10s %15s %15s\n", "N", "Std Compares", "Floyds Compares");
        for (int i = 3; i < 9; i += 1) {
            int N =  (int)Math.pow(10, i);
            Double[] a = new Double[N];
            for (int j = 0; j < N; j += 1) {
                a[j] = StdRandom.uniform();
            }
            Ex_40_FloydsMethod.sort(a);
            int compares = Ex_40_FloydsMethod.compares;
            Ex_40_FloydsMethod.stdSort(a);
            int stdCompares = Ex_40_FloydsMethod.compares;
            StdOut.printf("%10d %15d %15d\n", N, stdCompares, compares);
        }
        // Cuts the number of compares by a factor of 2 asymptotically.
        // Useful when the cost of compares is relatively high like strings
        //         N    Std Compares Floyds Compares
        //      1000           17583           10517
        //     10000          244460          138918
        //    100000         3112517         1723040
        //   1000000        37692069        20526449
        //  10000000       445162673       238624534
    }

}

