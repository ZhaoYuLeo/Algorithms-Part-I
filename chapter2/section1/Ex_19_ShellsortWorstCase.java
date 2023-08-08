package chapter2.section1;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Construct an array of 100 elements containing the numbers 1 through 100 for which
 * shellsort, with the increments 1 4 13 40, uses as large a number of compares as
 * you can find.
 */

public class Ex_19_ShellsortWorstCase {
    private static int compares;

    public static void shell(int[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        compares = 0;


        while (h >= 1) {
            for (int i = h; i < N; i += 1) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static boolean less(int a, int b) {
        compares += 1;
        return a < b;
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(int[] a) {
        for (int i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            int r = i + StdRandom.uniform(N - i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {
        int N = 100;
        int[] a = new int[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = i;
        }

        int max = 0;
        int[] worst = new int[N];

        int times = 0;
        //while (times < 10000000) {
        while (true) {
            shuffle(a);
            int[] copy = Arrays.copyOf(a, a.length);
            shell(a);
            if (compares > 998) {
                worst = copy;
                max = compares;
                break;
            } 
            times += 1;
        }

        StdOut.println(max);
        show(worst);

    }
}
