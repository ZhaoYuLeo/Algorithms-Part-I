package chapter2.section4;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a version of heapsort based on complete heap-ordered 3-ary and 4-ary trees, as described in the text.
 * Count the number of compares used by each and the number of compares used by the standard implementation, for
 * randomly ordered distinct keys with N = 10^3, 10^6, and 10^9.
 */
public class Ex_41_MultiwayHeaps {
    private static int compares = 0; // counter

    /**
     * Prints out the percentage of time heapsort spends in the construction
     */
    public static void timeTrial(int[] capacity, int times) {
        StdOut.printf("%7s %5s %8s \n", "capacity", "type", "compares");
        for (int i = 0; i < capacity.length; i += 1) {
            for (int ary = 2; ary < 5; ary += 1) {
                int total = 0;
                for (int t = 0; t < times; t += 1) {
                    Comparable[] a = generateArray(capacity[i]);
                    compares = 0;
                    heapSort(a, ary);
                    total += compares;
                }
                StdOut.printf("%7d %5d-ary %8.1f \n", capacity[i], ary, compares * 1.0 / times);
            }
        }
    }

    /**
     * Return Double array of size N
     */
    public static Double[] generateArray(int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    /**
     * Heap sort with different tree
     */
    public static void heapSort(Comparable[] a, int ary) {
        int N = a.length;
        for (int k = (N + ary - 2) / ary; k > 0; k -= 1) {
            sink(a, k, N, ary);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N, ary);
        }
    }

    // oo   (2 * k, 2 * k + 1)
    // ooo  (3 * k - 1, 3 * k, 3 * k + 1)
    // oooo (4 * k - 2, 4 * k - 1, 4 * k, 4 * k + 1)
    private static void sink(Comparable[] a, int k, int N, int ary) {
        while (ary * k - ary + 2 <= N) {
            int j = ary * k - ary + 2;
            j = findMax(a, j, Math.min(j + ary - 1, N));
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    private static int findMax(Comparable[] a, int start, int end) {
        int max = start;
        for (int i = start; i <= end; i += 1) {
            if (less(a, max, i)) {
                max = i;
            }
        }
        return max;
    }

    /** off-by-one */
    private static boolean less(Comparable[] a, int i, int j) {
        compares += 1;
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    /** off-by-one */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    public static void main(String[] args) {
        // Double[] a = generateArray(10);
        // for (int i = 2; i < 5; i += 1) {
        //     heapSort(a, i);
        //     for (Double d : a) {
        //         StdOut.printf(d + " ");
        //     }
        //     StdOut.println();
        // }

        int[] capacity = new int[7];
        for (int i = 1; i < capacity.length; i += 1) {
            capacity[i - 1] = (int)Math.pow(10, i);
        }
        timeTrial(capacity, 100);
       
        // Output
        // capacity    type  compares 
        //      10     2-ary      0.5 
        //      10     3-ary      0.5 
        //      10     4-ary      0.6 
        //     100     2-ary     15.8 
        //     100     3-ary     13.4 
        //     100     4-ary     13.5 
        //    1000     2-ary    253.1 
        //    1000     3-ary    218.9 
        //    1000     4-ary    220.8 
        //   10000     2-ary   3527.7 
        //   10000     3-ary   3023.7 
        //   10000     4-ary   3036.9 
        //  100000     2-ary  45291.9 
        //  100000     3-ary  38633.6 
        //  100000     4-ary  38471.9 
        // 1000000     2-ary 551911.3 
        // 1000000     3-ary 470587.1 
        // 1000000     4-ary 469415.7 
    }

}

