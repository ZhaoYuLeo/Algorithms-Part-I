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

    public Ex_25_InsertionWithoutExchanges() {
        super("Insertion Without Exchanges");
    }


    public <T extends Comparable<T>> void sort(T[] a) {
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
    

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new Ex_25_InsertionWithoutExchanges();
        instance.sort(a);
        show(a);

        Sort alg1 = instance;
        Sort alg2 = new InsertionSort();

        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double t1 = SortCompare.timeRandomInput(alg1, N, T); // total for alg1
        double t2 = SortCompare.timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1.getName());
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2.getName());

        // A E E E H L L L M O P R S S T X
        // For 1000 random Doubles
        //  Insertion Without Exchanges is 1.2 times faster than Insertion
    }
}
