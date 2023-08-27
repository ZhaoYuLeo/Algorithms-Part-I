package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Write a version of bottom-up mergesort that takes advantage of order in the array by proceeding as
 * follows each time it needs to find two arrays to merge: find a sorted subarray (by incrementing a
 * pointer until finding an entry that is smaller than its predecessor in the array), then find the next,
 * then merge them. Analyze the running time of this algorithm in terms of the array size and the number
 * of maximal increasing sequences in the array.
 */
public class Ex_16_NaturalMergesort {
    public static void bottomUp(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];


        int lo = 0, mid = 0, hi = 0;
        while (true) {
            // TODO: better advance lo for faster finding
            mid = findSortedSubArray(a, lo);
            if (mid == N - 1) {
                merge(a, lo, mid - 1, N - 1, aux);
                return;
            }
            hi = findSortedSubArray(a, mid);

            merge(a, lo, mid - 1, hi - 1, aux);
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
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

    /**
     * Return index i, between lo and i, the array is ordered.
     */
    public static int findSortedSubArray(Comparable[] a, int lo) {
        int i = lo + 1;
        while (i < a.length) {
            if (less(a[i], a[i - 1])) {
                return i;
            }
            i += 1;
        } 
        return i - 1;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (Comparable e : a) {
            StdOut.print(e + " ");
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

        bottomUp(a);

        show(a);
    }
}
