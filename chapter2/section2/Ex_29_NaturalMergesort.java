package chapter2.section2;

import java.util.Random;

import edu.princeton.cs.algs4.StdOut;

/**
 * Determine empirically the number of passes needed in a natural mergesort(see EXERCISE 2.2.16) for
 * random Long keys with N = 10^3, 10^6, and 10^9. Hint: You do not need to implement a sort(or even
 * generate full 64-bit keys) to complete this exercise.
 * determined by and only by the input array
 */
public class Ex_29_NaturalMergesort {

    private static int passes; 

    public static void sort(Comparable[] a) {
        passes = 0;
        int N = a.length;

        Comparable[] aux = new Comparable[N];

        int lo = 0, hi;
        int mid = findPeak(a, lo, N - 1);
        while ((hi = findPeak(a, mid + 1, N - 1)) > 0) {
            merge(a, aux, 0, mid, hi);
            mid = hi;
            lo = mid;
            passes += 1;
        }
    }

    public static int findPeak(Comparable[] a, int lo, int hi) {
        if (hi < lo) {
            return -1;
        }

        int i;
        for (i = lo; i < hi; i += 1) {
            if (less(a[i + 1], a[i])) {
                break;
            }
        }
        return i;
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

    public static Long[] generateRandomArray(int N) {
        Long[] a = new Long[N];
        Random random = new Random();

        for (int i = 0; i < N; i += 1) {
            a[i] = random.nextLong();
        }
        return a;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < a.length; i += 1) {
            a[i] = random.nextInt(100);
        }
        show(a);
        StdOut.println(findPeak(a, 1, a.length - 1));
        sort(a);
        show(a);

        for (int i = 1; i < 4; i += 1) {
            int N = (int)Math.pow(10, i * 3);
            Long[] la = generateRandomArray(N);
            sort(la);
            StdOut.printf("for %d Random Long Array\n the number of passes is %d\n", N, passes);
        }

        // 10^3 500
    }
}
