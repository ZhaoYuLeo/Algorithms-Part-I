package chapter2.section2;

import java.awt.Color;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;;

/**
 * Write a program to compute the exact value of the number of array accesses used by
 * top-down mergesort and by bottom-up mergesort. Use your program to plot the values
 * for N from 1 to 512, and to compare the exact values with the upper bound 6N lg N.
 */
public class Ex_06 {
    public static Double[] aux;

    public static int arrayAccesses;

    public static void topDownSort(Double[] a) {
        arrayAccesses = 0;
        aux = new Double[a.length];
        topDownSort(a, 0, a.length - 1);
    }

    public static void topDownSort(Double[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        topDownSort(a, lo, mid);
        topDownSort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void bottomUpSort(Double[] a) {
        arrayAccesses = 0;
        int N = a.length;
        aux = new Double[N];
        for (int sz = 1; sz < N; sz += sz) {                // sz: subarray size
            for (int lo = 0; lo < N - sz; lo += sz + sz) {  // lo: subarray index
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void merge(Double[] a, int lo, int mid, int hi) {
        int i = 0, j = mid + 1;

        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
            arrayAccesses += 2;
        }

        for (int k = lo; k <= hi; k += 1) {
            arrayAccesses += 2;

            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(a[j], a[i])) {
                arrayAccesses += 2;

                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private static boolean less(Double v, Double w) {
        return v.compareTo(w) < 0;
    }


    private static void show(Double[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.println(a[i] + " ");
        }
        StdOut.println();
    }


    public static Double[] randomInput(int N) {
        Double[] a = new Double[N];
        for(int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }


    public static void main(String[] args) {
        int N = 512;
        Double[] x = new Double[N];

        StdDraw.setXscale(0, N + 1);
        StdDraw.setYscale(0, N * 50);
        StdDraw.setPenRadius(.01);
        for (int i = 1; i <= 512; i += 1) {
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            Double[] a = randomInput(i);
            topDownSort(a);
            StdDraw.point(i, arrayAccesses);


            StdDraw.setPenColor(StdDraw.BLUE);
            Double[] b = randomInput(i);
            bottomUpSort(b);
            StdDraw.point(i, arrayAccesses);

            StdDraw.setPenColor(StdDraw.BOOK_RED);
            double c = 6 * i * (Math.log(i) / Math.log(2));
            StdDraw.point(i, c);
        }
    }
}


