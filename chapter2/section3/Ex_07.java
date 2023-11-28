package chapter2.section3;

import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Find the expected number of subarrays of size 0, 1, and 2 when quicksort is used
 * to sort an array of N items with distinct keys. If you are mathematically inclined,
 * do the math; if not, run some experiments to develop hypotheses.
 */
public class Ex_07 {
    private static Map<Integer, Integer> result = new HashMap<>();

    public static void experiment(int N) {
        result = new HashMap<>();
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = i;
        }
        // Exchange a[i] with random element in a[i..N-1]
        StdRandom.shuffle(a);
        sort(a);
    }

    /**
     * Record the number of subarrays of size 0, 1, and 2 when sorting.
     */
    private static void recordSize(int lo, int hi) {
        int size = hi - lo + 1;
        if (size == 0 || size == 1 || size == 2) {
            result.put(size, result.getOrDefault(size, 0) + 1);
        }
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);       // eliminate dependence on input
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        recordSize(lo, hi);

        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) { // redundant: v won't be less than a[lo] for they are same.
                    break;
                }
            }
            if (j <= i) { // equal when the item has the same value with v.
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a ) {
        for (Comparable i : a) {
            StdOut.print(i + ", ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = {1, 6, 3, 0, 9, 21, 4, 4, 7, 9};
        show(a);
        sort(a);
        show(a);

        int maxScale = 100000;
        StdOut.printf("%12s | %16s | %16s | %16s\n", "Array Size", "Number of Size 0", "Number of Size 1", "Number of Size 2");
        for (int n = 1000; n < maxScale; n += n) {
            experiment(n);
            StdOut.printf("%12d | %10d(%1.2f) | %10d(%1.2f) | %10d(%1.2f)\n", n
                    , result.get(0), result.get(0) * 1.0/n 
                    , result.get(1), result.get(1) * 1.0/n 
                    , result.get(2), result.get(2) * 1.0/n);
        }
        //  Subarray size 0/1: 1/3 N; 2: 1/6N:
        //  Array Size | Number of Size 0 | Number of Size 1 | Number of Size 2
        //        1000 |        327(0.33) |        337(0.34) |        163(0.16)          
        //        2000 |        639(0.32) |        681(0.34) |        330(0.17)
        //        4000 |       1293(0.32) |       1354(0.34) |        641(0.16)
        //        8000 |       2719(0.34) |       2641(0.33) |       1342(0.17)
        //       16000 |       5333(0.33) |       5334(0.33) |       2643(0.17)
        //       32000 |      10619(0.33) |      10691(0.33) |       5273(0.16)
        //       64000 |      21479(0.34) |      21261(0.33) |      10795(0.17)
    }
}
