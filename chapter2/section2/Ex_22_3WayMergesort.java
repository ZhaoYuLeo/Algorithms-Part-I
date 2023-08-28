package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Suppose instead of dividing in half at each step, you divide into thirds, sort
 * each third, and combine using a 3-way merge. What is the order of growth of the
 * overall running time of this algorithm?
 * same but base 3
 */
public class Ex_22_3WayMergesort {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int left = lo + (hi - lo) / 3;
        int right = hi - (hi - lo) / 3;

        sort(a, aux, lo, left);
        sort(a, aux, left + 1, right);
        sort(a, aux, right + 1, hi);

        merge3(a, aux, lo, left, right, hi);
    }

    public static void merge3(Comparable[] a, Comparable[] aux, int lo, int left, int right, int hi) {
        int i = lo, j = left + 1, k = right + 1;

        for (int p = lo; p <= hi; p += 1) {
            aux[p] = a[p];
        }

        for (int p = lo; p <= hi; p += 1) {
            if (i > left) {
                merge2(a, aux, p, hi, j, right, k, hi);
                return;
            } else if (j > right) {
                merge2(a, aux, p, hi, i, left, k, hi);
                return;
            } else if (k > hi) {
                merge2(a, aux, p, hi, i, left, j, right);
                return;
            } else {
                int ji = aux[j].compareTo(aux[i]);
                int kj = aux[k].compareTo(aux[j]);
                int ki = aux[k].compareTo(aux[i]);

                if (ki < 0 && kj < 0) {
                    a[p] = aux[k++];
                } else if (kj >= 0 && ji < 0) {
                    a[p] = aux[j++];
                } else {
                    a[p] = aux[i++];
                }
            }
        }
    }

    private static void merge2(Comparable[] a, Comparable[] aux, int p, int hi, int j, int jBound, int k, int kBound) {
        for (; p <= hi; p += 1) {
            if (j > jBound) {
                a[p] = aux[k++];
            } else if (k > kBound) {
                a[p] = aux[j++];
            } else if (less(aux[k], aux[j])) {
                a[p] = aux[k++];
            } else {
                a[p] = aux[j++];
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
        sort(a);
        show(a);
    }
}
