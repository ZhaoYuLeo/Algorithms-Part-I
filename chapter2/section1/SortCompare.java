package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;


public class SortCompare {
    public static double time(Sort alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        alg.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(Sort alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t += 1) {
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {

        Sort alg1 = new Ex_25_InsertionWithoutExchanges();
        Sort alg2 = new InsertionSort();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double t1 = timeRandomInput(alg1, N, T); // total for alg1
        double t2 = timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1.getName());
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2.getName());
    }
}
