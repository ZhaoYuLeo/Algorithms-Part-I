package chapter2.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Explain what happens when Quick.sort() is run on an array having items with
 * just two distinct keys, and then explain what happens when it is run on an
 * array having just three distinct keys.
 */
public class Ex_09 {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while(true) {
            StdOut.println("show index status " + i + ", " + j);
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (j <= i) {
                break;
            }
            StdOut.println("Before exchange:");
            display(a);
            StdOut.println("exchange: " + i + " " + j);
            exch(a, i, j);
            display(a);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.printf(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = {1, 9, 9, 1, 9, 1};
        sort(a);
    }
}
