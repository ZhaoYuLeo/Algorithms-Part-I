package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 *  Write a client that performs a doubling test for sort algorithms.
 *  Start at N equal to 1000, and print N, the predicted number of
 *  seconds, the actual number of seconds, and the actual number of
 *  seconds, and the ratio as N doubles. Use your program to validate
 *  that insertion sort and selection sort are quadratic for random
 *  inputs, and formulate and test a hypothesis for shellsort.
 */ 
public class Ex_31_DoublingTest {

    public static double timeTrial(int N, Sort sort) {
        double total = 0, time = 10;
        for (int t = 0; t < time; t += 1) {
            int MAX = 1000000; // random 6-digit ints
            Integer[] a = new Integer[N];
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform(-MAX, MAX); // not distinct keys
            }
            Stopwatch timer = new Stopwatch();
            sort.sort(a);
            total += timer.elapsedTime();
        }
        return total / time;
    }

    public static void experiment(int maxScale, Sort sort, Predicted p) {
        StdOut.printf("%5s | %12s | %9s | %5s\n","Size", "Predicted(s)", "Actual(s)", "Ratio");
        double prev = timeTrial(500, sort);
        for (int N = 1000; N < maxScale; N += N) {
            double time = timeTrial(N, sort);
            double predicted = p.time(prev);
            StdOut.printf("%5d | %10.2f | %7.2f | %3.2f\n", N, time, predicted, time * 1.0 / prev);
            prev = time;
        }
    }

    interface Predicted {
        double time(double p);
    }

    public static void main(String[] args) {
        int maxScale = 100000;
        experiment(maxScale, new InsertionSort(), p -> 4 * p);
        experiment(maxScale * 100, new Shellsort(), p -> Math.pow(2, 1.5) * p); // 2.8284
        experiment(maxScale, new SelectionSort(), p -> 4 * p);
        // Size | Predicted(s) | Actual(s) | Ratio
        // 1000 |       0.00 |    0.01 | 0.26
        // 2000 |       0.00 |    0.00 | 4.37
        // 4000 |       0.02 |    0.01 | 4.63
        // 8000 |       0.06 |    0.06 | 3.50
        //16000 |       0.23 |    0.23 | 4.10
        //32000 |       1.06 |    0.93 | 4.55
        //64000 |       5.46 |    4.23 | 5.16
        // Size | Predicted(s) | Actual(s) | Ratio
        // 1000 |       0.00 |    0.00 | 1.50
        // 2000 |       0.00 |    0.00 | 1.00
        // 4000 |       0.00 |    0.00 | 1.50
        // 8000 |       0.00 |    0.00 | 1.67
        //16000 |       0.00 |    0.00 | 1.87
        //32000 |       0.01 |    0.01 | 2.57
        //64000 |       0.02 |    0.02 | 2.69
        //128000 |       0.04 |    0.05 | 2.23
        //256000 |       0.14 |    0.12 | 3.35
        //512000 |       0.36 |    0.41 | 2.47
        //1024000 |       1.04 |    1.01 | 2.91
        //2048000 |       2.91 |    2.95 | 2.79
        //4096000 |       7.67 |    8.22 | 2.64
        //8192000 |      19.62 |   21.71 | 2.56
        // Size | Predicted(s) | Actual(s) | Ratio
        // 1000 |       0.00 |    0.01 | 1.10
        // 2000 |       0.00 |    0.01 | 1.45
        // 4000 |       0.01 |    0.01 | 3.69
        // 8000 |       0.05 |    0.05 | 3.88
        //16000 |       0.18 |    0.18 | 4.03
        //32000 |       0.75 |    0.74 | 4.06
        //64000 |       3.14 |    2.99 | 4.20
    }
}
