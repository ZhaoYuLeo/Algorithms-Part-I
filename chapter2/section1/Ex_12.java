package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Instrument shellsort to print the number of compares divided by the array size for each increment.
 * Write a test client that tests the hypothesis that this number is a small constant, by sorting arrays
 * of random Double values, using array sizes that are increasing powers of 10, starting at 100.
 */
public class Ex_12 extends Sort {
    public static int compares;

    public Ex_12() {
        super("Shellsort Print the Number of Compares");
    }

    public <T extends Comparable<T>> void sort(T[] a) {

        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;

        StdOut.printf("\nThe random array size N: %6d \n", N);
        StdOut.printf("%8s %6s\n", "h", "compares // N");
        while (h >= 1) {
            compares = 0;
            for (int i = h; i < N; i += 1) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    compares += 1;

                    exch(a, j, j - h);
                }
                compares += 1;
            }
            StdOut.printf("%8d %6.2f\n", h, (1.0 * compares / N));
            h /= 3;
        }

    }

    public static Double[] randomArray(int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    public static void main(String[] args) {
        int maxScale = 10000000;
        Sort instance = new Ex_12();
        for (int i = 10; i < maxScale; i *= 10) {
            instance.sort(randomArray(i));
        }
    }
}
