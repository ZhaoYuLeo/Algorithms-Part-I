package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Use SortCompare to get a rough idea of the effect on performance on your machine of
 * creating aux[] in merge() rather than in sort().
 */
public class Ex_26_ArrayCreation {

    public static void createInMerge(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        Comparable[] aux = new Comparable[a.length];

        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void createInSort(Comparable[] a) {

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

        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (Comparable i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static double time(int alg, int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }

        int T = 100;
        double total = 0;

        for (int t = 0; t < T; t += 1) {

            Stopwatch timer = new Stopwatch();

            if (alg == 0) {
                createInMerge(a);
            } else {
                createInSort(a);
            }

            total += timer.elapsedTime();
        }
        return total;
    }

    public static void sortCompare(int N) {
        String alg1 = "creating aux[] in merge()";
        String alg2 = "creating aux[] in sort()";

        double t1 = time(0, N);
        double t2 = time(1, N);

        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }

    public static void main(String[] args) {
        for (int N = 1000; N <= 100000; N *= 10) {
            sortCompare(N);
        }
    }

    // creating aux[] in merge() is 0.1 times faster than creating aux[] in sort()
}
