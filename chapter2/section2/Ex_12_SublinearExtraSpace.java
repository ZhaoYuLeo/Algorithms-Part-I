package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a merge implementation that reduces the extra space requirement to max(M,N/M),
 * based on the following idea: Divide the array into N/M blocks of size M (for simplicity
 * in this description, assume that N is a multiple of M). Then, (i) considering the blocks
 * as items with their first key as the sort key, sort them using selection sort; and (ii)
 * run through the array merging the first block with the second, then the second block
 * with the third, and so forth.
 */
public class Ex_12_SublinearExtraSpace {
    private static final int BLOCKSIZE = 5;

    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int lo = 0; lo < N; lo += BLOCKSIZE) {
            selectionSort(a, lo, Math.min(lo + BLOCKSIZE - 1, N - 1));
        }

        int hi = BLOCKSIZE + BLOCKSIZE - 1;
        Comparable[] aux = new Comparable[BLOCKSIZE];


        while (hi < N) {
            merge(a, 0, hi - BLOCKSIZE + 1, hi, aux);
            hi += BLOCKSIZE;
        }

        hi -= BLOCKSIZE;
        if (hi < N - 1) {
            aux = new Comparable[N - 1 - hi];
            merge(a, 0, hi + 1, N - 1, aux);
        }

    }

    public static void merge(Comparable[] a, int lo, int mid, int hi, Comparable[] aux) {
        StdOut.println(lo + " " + mid + " " + hi);

        int auxIndex = 0;
        for (int k = mid; k <= hi; k += 1) {
            aux[auxIndex] = a[k];
            auxIndex += 1;
        }

        int i = mid - 1, j = aux.length - 1;
        for (int k = hi; k >= lo; k -= 1) {
            if (j < 0) {
            } else if (i < 0) {
                a[k] = aux[j--];
            } else if (less(aux[j], a[i])) {
                a[k] = a[i--];
            } else {
                a[k] = aux[j--];
            }
        }

        show(a);
    }

    public static void selectionSort(Comparable[] a, int lo, int hi) {
        int N = a.length;

        for (int i = lo; i <= hi; i += 1) {
            int min = i;
            for (int j = i + 1; j <= hi; j += 1) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
        for (Comparable e : a) {
            StdOut.print(e + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = {3, 6, 2, 4, 7, 9, 1, 8, 5};
        //selectionSort(a, 1, 5);
        sort(a);
        show(a);
    }
}
