package chapter2.section4;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Determine empirically the percentage of time heapsort spends in the construction phase for N = 10^3, 10^6, and 10^9.
 */
public class Ex_39 {
    /**
     * Prints out the percentage of time heapsort spends in the construction
     */
    public static void timeTrial(int[] capacity, int times) {
        StdOut.printf("%7s | %8s | %8s | %8s\n", "capacity", "construction(s)", "sortdown(s)", "construction / total(%)");
        for (int i = 0; i < capacity.length; i += 1) {
            double total1 = 0;
            double total2 = 0;
            for (int t = 0; t < times; t += 1) {
                Comparable[] a = generateArray(capacity[i]);
                Stopwatch timer = new Stopwatch();
                heapConstruction(a);
                total1 += timer.elapsedTime();

                timer = new Stopwatch();
                sortDown(a);
                total2 += timer.elapsedTime();
            }
            StdOut.printf("%7d %8.5f %8.5f %8.5f\n", capacity[i], total1 / times, total2 / times, total1 * 100 / (total1 + total2));
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
     * Heap construction part of heapsort
     */
    public static void heapConstruction(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k > 0; k -= 1) {
            sink(a, k, N);
        }
    }

    /**
     * Sort down part of heapsort
     */
    public static void sortDown(Comparable[] a) {
        int N = a.length;
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

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

    /** off-by-one */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    /** off-by-one */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    public static void main(String[] args) {
        int[] capacity = new int[7];
        for (int i = 1; i < capacity.length; i += 1) {
            capacity[i - 1] = (int)Math.pow(10, i);
        }
        timeTrial(capacity, 100);
       
        // Output
        // capacity | construction(s) | sortdown(s) | construction / total(%)
        //      10  0.00000  0.00001  0.00000
        //     100  0.00003  0.00004 42.85714 (too small to be precise)

        //    1000  0.00004  0.00022 15.38462
        //   10000  0.00011  0.00144  7.09677
        //  100000  0.00142  0.02350  5.69823
        // 1000000  0.01695  0.50400  3.25367 (The proportion decreases as the size increases)

        //       0  0.00000  0.00000      NaN (OutOfMemory)
    }

}

