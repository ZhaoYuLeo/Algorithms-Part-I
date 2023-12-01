package chapter2.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/**
 * Modify the code in ALGORITHM 2.5 to remove both bounds checks in the
 * inner while loops. The test against the left end of the subarray is
 * redundant since the partitioning item acts as a sentinel(v is never 
 * less than a[lo]). To enable removal of the other test, put an item
 * whose key is the largest in the whole array into a[length-1] just after
 * the shuffle. This item will never move(except possibly to be swapped
 * with an item having the same key) and will serve as a sentinel in all
 * subarrays involving the end of the array. Note: When sorting interior
 * subarrays, the leftmost entry in the subarray to the right serves as a
 * sentinel for the right end of the subarray.
 */
public class Ex_17_Sentinels {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        // Put the largest item to the end of the array served as a sentinel.
        // Cause side effects. TODO: Any improvement?
        int maxIndex = 0;
        for (int i = 1; i < a.length; i += 1) {
            if (less(a[maxIndex], a[i])) {
                maxIndex = i;
            }
        }
        exch(a, maxIndex, a.length - 1);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v =a[lo];
        while (true) {
            while (less(a[++i], v)) {}
            while (less(v, a[--j])) {}
            if (j <= i) {
                break;
            }
            exch(a, j, i);
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

    private static void display(Comparable[] a) {
        for (Comparable i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = {32, 9, 1, 3, 5, 9, 0, 2};
        display(a);
        sort(a);
        display(a);
    }
}
