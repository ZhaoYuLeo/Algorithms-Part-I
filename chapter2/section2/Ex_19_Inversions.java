package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Develop and implement a linearithmic algorithm for computing the number of inversion in a given
 * array (the number of exchanges that would be performed by insertion sort for that array -- see
 * SECTION 2.1). This quantity is related to the Kendall tau distance; see SECTION 2.5.
 */
public class Ex_19_Inversions {

    public static int count(Comparable[] a) {
        int N = a.length;

        Comparable[] aux = new Comparable[N];
        Comparable[] b = new Comparable[N];

        for (int i = 0; i < N; i += 1) {
            b[i] = a[i];
        }

        // count would will the order of the given array
        return count(b, a, 0, N - 1);
    }

    public static int count(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int inversion = 0;

        if (lo >= hi) {
            return inversion;
        }

        int mid = lo + (hi - lo) / 2;

        inversion += count(a, aux, lo, mid);
        inversion += count(a, aux, mid + 1, hi);

        inversion += merge(a, aux, lo, mid, hi);

        return inversion;
    }

    public static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int inversion = 0;

        // merge two sorted array into one sorted array
        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }

        // assert a[lo..mid] is sorted, and a[mid + 1..hi] is sorted and change a[lo..hi] into sorted array.
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                inversion += mid - i + 1; // [i..mid] with [j]
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }

        return inversion;
    }

    public static int brute(Comparable[] a) {
        int N = a.length;

        int inversion = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                if (less(a[j], a[i])) {
                    inversion += 1;
                }
            }
        }

        return inversion;
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

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        Integer[] a = new Integer[N];

        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform(N);
        }

        show(a);

        StdOut.println(brute(a));

        StdOut.println(count(a));
    }
}
