package chapter2.section3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Write a program to compute the exact value of CN, and compare the exact value with
 * the approximation 2NlnN, for N = 100, 1,000, and 10,000.
 */
public class Ex_06 {
    private static int compares;

    private static void sort(Comparable[] a) {
        compares = 0;
        StdRandom.shuffle(a);
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
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(Comparable v, Comparable w) {
        compares += 1;
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        for (int N = 100; N <= 10000; N *= 10) {
            Double[] a = new Double[N];
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform();
            }
            sort(a);
            Double theroy = 2 * N * Math.log(N);
            StdOut.printf("%6d %8d %10.1f %10.1f\n", N, compares, theroy, compares/theroy);
        }
    }
}
