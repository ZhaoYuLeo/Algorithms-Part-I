package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Implement the three improvements to mergesort that are described in the text on page 275:
 * Add a cutoff for small subarrays, test whether the array is already in order, and avoid
 * the copy by switching arguments in the recursive code.
 */
public class Ex_11_Improvements {

    private static final int CUTOFF = 15;
    
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(a, 0, a.length - 1, aux);
    }

    public static void sort(Comparable[] a, int lo, int hi, Comparable[] aux) {

        // cutoff
        if ((hi - lo) <= CUTOFF) {
            insertionSort(aux, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(aux, lo, mid, a); 
        sort(aux, mid + 1, hi, a); 

        // already in order
        if (less(a[mid], a[mid + 1])) {
            System.arraycopy(a, lo, aux, lo, hi - lo + 1);
            return;
        }

        merge(a, lo, mid, hi, aux);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        int i = lo, j = mid + 1; 

        for (int k = lo; k <= hi; k += 1) {
            if (j > hi) {
                aux[k] = a[i++];
            } else if (i > mid) {
                aux[k] = a[j++];
            } else if (less(a[j], a[i])) {
                aux[k] = a[j++];
            } else {
                aux[k] = a[i++];
            }
        }
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i += 1) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j -= 1)  {
                exch(a, j, j - 1);
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; 
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        
        Integer[] a = new Integer[N];
        for (int i = 0; i < a.length; i += 1) {
            a[i] = StdRandom.uniform(N);
        }
        // insertionSort(a, 1, 4);
        sort(a);

        show(a);
    }
}

