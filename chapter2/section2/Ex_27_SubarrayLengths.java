package chapter2.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Run mergesort for large random arrays, and make an empirical determination
 * of the average length of the other subarray when the first subarray exhausts,
 * as a function of N (the sum of the two subarray sizes for a given merge).
 */
public class Ex_27_SubarrayLengths {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }

        int remainer = 0;
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                remainer += 1;
                a[k] = aux[j++];
            } else if (j > hi) {
                remainer += 1;
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

        StdDraw.point(hi - lo + 1, remainer);
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static Double[] generateArray(int N) {
        Double[] a = new Double[N]; 
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    public static void main(String[] args) {
        int N = 1000;
        StdDraw.setXscale(0, N + 1);
        StdDraw.setYscale(0, 15);
        StdDraw.setPenRadius(.01);

        for (int i = 0; i < 100; i += 1) {
            Double[] a = generateArray(N);
            sort(a);
        }
    }
}
