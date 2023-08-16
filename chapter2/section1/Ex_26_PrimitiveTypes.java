package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Develop a version of insertion sort that sorts arrays of int values and compare
 * its performance with the implementation given in the text (which sorts Integer
 * values and implicitly uses autoboxing and auto-unboxing to convert).
 */
public class Ex_26_PrimitiveTypes {
    public static void sort(int[] a) {
        int N = a.length;

        for (int i = 1; i < N; i += 1) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sort(Integer[] a) {
        int N = a.length;

        for (int i = 1; i < N; i += 1) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }   
        }
    }

    public static boolean less(int a, int b) {
        return a < b;
    }

    public static boolean less(Integer a, Integer b) {
        return a.compareTo(b) < 0;
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void exch(Integer[] a, Integer i, Integer j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void show(Integer[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static double intTime(int N, int T) {
        double total = 0.0;
        int[] a = new int[N];
        for (int t = 0; t < T; t += 1) {
            // generate random input
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform(N);
            }
            // time sort
            Stopwatch timer = new Stopwatch();
            sort(a);
            total += timer.elapsedTime();
        }
        return total;
    }

    public static double integerTime(int N, int T) {
        double total = 0.0;
        Integer[] a = new Integer[N];
        for (int t = 0; t < T; t += 1) {
            // generate random input
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform(N);
            }
            // time sort
            Stopwatch timer = new Stopwatch();
            sort(a);
            total += timer.elapsedTime();
        }
        return total;
    }

    public static void main(String[] args) {
        int[] a = {4, 6, 8, 9, 1, 7, 3, 0};
        Integer[] b = {4, 9, 3, 2, 0, 8, 5, 1};
        sort(a);
        show(a);
        sort(b);
        show(b);

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double t1 = intTime(N, T);
        double t2 = integerTime(N, T);
        StdOut.printf("For %d random integer\n %s is", N, "int");
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, "Integer");

        // 0 1 3 4 6 7 8 9
        // 0 1 2 3 4 5 8 9
        // For 3000 random integer
        //  int is 32.8 times faster than Integer
    }
}
