package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a version of  merge() that copies the second half of a[] to aux[] in decreasing order and
 * then does the merge back to a[]. This change allows you to remove the code the test that each of the
 * halves has been exhausted from the inner loop. Note: The resulting sort is not stable.
 * Stable means it preserves the relative order of equal keys in the array.
 */
public class Ex_10_FasterMerge {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, 0, a.length - 1, aux);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux);
        sort(a, mid + 1, hi, aux);
        merge(a, lo, mid, hi, aux);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        for (int k = lo ; k <= mid; k += 1) {
            aux[k] = a[k];
        }

        for (int k = mid + 1; k <= hi; k += 1) {
            aux[hi + mid - k + 1] = a[k];
        }

        int i = lo, j = hi;
        for (int k = lo; k <= hi; k += 1) {
            // If left half array has been exhausted, aux[i] indicates the last elements in a and 
            // have the same value with aux[j], the relative order of equal keys won't keep same.
            if (less(aux[j], aux[i])) {
                a[k] = aux[j--];
            } else {
                a[k] = aux[i++];
            }
        }
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

    public static void main(String[] args) {
        Integer[] a = {4, 3, 5, 2, 9, 0, 8, 6, 1, 7};
        sort(a);
        show(a);
    }
}
