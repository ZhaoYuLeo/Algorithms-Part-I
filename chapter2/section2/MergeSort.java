package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

public class MergeSort<T extends Comparable<T>> {

    private T[] aux; 

    private MergeSort(int N) {
        aux = (T[]) new Comparable[N]; 
    }

    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        MergeSort<T> ms = new MergeSort<>(a.length);
        ms.sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<T>> void show(T[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private void merge(T[] a, int lo, int mid, int hi) {
        // Merge a[lo..mid] with a[mid + 1..hi].
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

    private boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }
    
    public static void main(String[] args) {
        // String[] a = {"M", "E", "R", "G", "E", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        // String[] a = {"A" ,"E" ,"Q" ,"S" ,"U" ,"Y" ,"E" ,"I" ,"N" ,"O" ,"S" ,"T"};
        Integer[] a = new Integer[39];
        int k = 100;
        for (Integer i = 0; i < a.length; i += 1) {
            a[i] = k; 
            k -= 1;
        }
        mergeSort(a);
        show(a);
    }
}
