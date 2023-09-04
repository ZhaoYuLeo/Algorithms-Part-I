package chapter2.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Use SortCompare to get a rough idea of the effect on performance on
 * your machine of creating aux[] in merge() rather than in sort().
 */
public class Ex_26_ArrayCreation {

    public static double time(String alg, int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        
        Stopwatch timer = new Stopwatch();

        if (alg == "top-down") {
            MergeSort.mergeSort(a);
        } else {
            MergeBU.mergeBU(a);
        }
        return timer.elapsedTime();
    }

    public static void sortCompare(int N) {
        String alg1 = "top-down";
        String alg2 = "bottom-up";

        double t1 = time(alg1, N);
        double t2 = time(alg2, N);

        StdOut.printf("For %d random Doubles\n %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2);
    }

    public static void main(String[] args) {
        for (int N = 1000; N <= 1000000; N *= 10) {
            sortCompare(N);
        }
    }
}
