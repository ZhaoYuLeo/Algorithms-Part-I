package chapter2.section1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Run experiments to determine a value of t that leads to the lowest running
 * time of shellsort for random arrays for the increment sequence l, ⌊t⌋, ⌊t^2⌋,
 * ⌊t^3⌋, ⌊t^4⌋,... for N = 10^6. Give the values of t and the increment sequences
 * for the best three values that you find.
 */
public class Ex_30_GeometricIncrements {
    private final String name;

    public Ex_30_GeometricIncrements() {
        this.name = "Geometric Increments Shellsort";
    }

    public static <T extends Comparable<T>> void sort(T[] a, int t) {
        int N = a.length;
        // By definition, I should generate a series of "Sort" for different values of t
        // For each value of t, if there is a need to create a new concrete "Sort" class,
        // it would be impossible to run the experiments. And I don't want to modify the 
        // interface only for this class so I have to copy some codes.
        int[] hs = generateSequence(N, t);

        for (int k = hs.length - 1; k > -1; k -= 1) {
            int h = hs[k];

            // h-sort the array
            for (int i = h; i < N; i += 1) {
                for (int j = i; j > h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static int[] generateSequence(int N, int t) {
        int size = (int) Math.round(Math.log(N) / Math.log(t)); // number of t-increment
        int[] s = new int[size];
        for (int i = 0; i < size; i += 1) {
            s[i] = (int)Math.pow(t, i);
        }
        return s;
    }

    public static <T extends Comparable<T>> double time(T[] a, int ht) {
        Stopwatch timer = new Stopwatch();
        sort(a, ht);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(int ht, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t += 1) {
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform();
            }
            total += time(a, ht);
        }
        return total;
    }

    public static <T> void show(Comparable<T>[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }


    public static void show(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        int N = (int)Math.pow(10, 6);
        StdOut.println(N);
        int[] s = generateSequence(N, 3);
        show(s);
        // for (int i = 2; i < 10; i += 1) {
        //    double t = timeRandomInput(i, N, T); // times for 3
        //    StdOut.printf("%3d %.1f times\n", i, t);
        // }
        // output:
        // 2 6.3 times
        // 3 8.9 times
        // 4 10.0 times
        // 5 11.5 times
        // 6 13.1 times
        // 7 12.3 times
        // 8 13.8 times
        // 9 12.6 times
    }
}
