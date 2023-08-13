package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Develop an implementation of insertion sort that moves larger elements to the
 * right one position with one array access per entry, rather than using exch().
 * Use SortCompare to evaluate the effectiveness of doing so.
 */
public class Ex_25_InsertionWithoutExchanges extends Sort {
    public static <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;

        for (int i = 1; i < N; i += 1) {
            T temp = a[i];
            
            int j = i;

            while(j > 0 && less(temp, a[j - 1])) {
                a[j] = a[j - 1];
                j -= 1;
            }
            a[j] = temp;
        }
    }
    

    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        int N = a.length;

        for (int i = 1; i < N; i += 1) {

            for (int j = i; j > 0 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }
        }
    }

    public static <T extends Comparable<T>> double time(String alg, T[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) {
            insertionSort(a);
        }
        if (alg.equals("InsertionWithoutExchanges")) {
            sort(a);
        }
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        // Use alg to sort T random arrays of length N.
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t += 1) {
            // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        insertionSort(a);
        show(a);

        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T); // total for alg1
        double t2 = timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);

        // For 1000 random Doubles
        // Insertion is 0.6 times faster than InsertionWithoutExchanges
    }
}
