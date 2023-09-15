package chapter2.section3;

import edu.princeton.cs.algs4.StdOut;

/**
 * Write a program that produces a best-case array (with no duplicates)
 * for sort() in Algorithm 2.5: an array of N items with distinct keys
 * having the property that every partition will produce subarrays that
 * differ in size by at most 1 (the same subarray sizes that would happen
 * for an array of N equal keys). (For the purposes of this exercise,
 * ignore the initial shufﬂe.)
 */
public class Ex_16_BestCase {
    private static void best(int[] a, int lo, int hi) {
        // assert a[i] = i

        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        exch(a, lo, mid);
    }

    public static int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i += 1) {
            a[i] = i;
        }
        best(a, 0, n - 1);
        return a;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int n = Integer.parseInt(args[0]);
        int[] a = best(n);
        for (int i = 0; i < n; i += 1) {
            StdOut.print(alphabet.charAt(a[i]));
        }
        StdOut.println();
    }
}
