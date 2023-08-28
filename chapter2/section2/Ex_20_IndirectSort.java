package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Develop and implement a version of mergesort that does not rearrange the array, but returns an int[]
 * array perm such that perm[i] is the index of the ith smallest entry in the array.
 */
public class Ex_20_IndirectSort {

    public static int[] sort(Comparable[] a) {
        int N = a.length;

        int[] index = new int[N];
        for (int i = 0; i < N; i += 1) {
            index[i] = i;
        }
        int[] aux = new int[N];

        sort(a, index, aux, 0, N - 1);
        return index;
    }

    public static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi); 
        merge(a, index, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k += 1) {
            aux[k] = index[k];
        }

        // same process with mergesort but reordering index instead of reordering elements
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                index[k] = aux[j++];
            } else if (j > hi) {
                index[k] = aux[i++];
            } else if (less(a[aux[j]], a[aux[i]])) {
                index[k] = aux[j++];
            } else {
                index[k] = aux[i++];
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


    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform(N);
        }

        show(a);

        int[] index = sort(a);
        for (int i = 0; i < index.length; i += 1) {
            StdOut.print(index[i] + " ");
        }
        StdOut.println();
    }
}
