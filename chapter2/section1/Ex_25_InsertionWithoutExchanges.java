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

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        insertionSort(a);
        show(a);

        String alg1 = "insert";
        String alg2 = "noexchinsert";

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double t1 = SortCompare.timeRandomInput(alg1, N, T); // total for alg1
        double t2 = SortCompare.timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);

        // For 1000 random Doubles
        // Insertion is 0.6 times faster than InsertionWithoutExchanges
    }
}
