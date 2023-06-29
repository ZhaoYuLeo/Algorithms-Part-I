package chapter1.section4;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Computes the ratio of the running times of this program and ThreeSum.
 */
public class Ex_38_Naive3Sum {

    public static int naive3Sum(int[] a) {
        // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0) cnt++;
        return cnt;
    }

    public static int threeSum(int[] a) {
        // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int k = j+1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0) cnt++;
        return cnt;
    }

    public static double timeTrial(int N, int flags) {
        // Time ThreeSum for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-MAX, MAX);
        Stopwatch timer = new Stopwatch();
        if (flags == 0) {
            int cnt = naive3Sum(a);
        } else {
            int cnt = threeSum(a);
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // Print table of running times.
        for (int N = 250; true; N += N) {
            double timeNaive = timeTrial(N, 0);
            double time = timeTrial(N, 1);
            StdOut.printf("%7d %5.1f %5.1f", N, timeNaive, time);
            StdOut.printf("%5.1f\n", timeNaive/time);
        }

        // I thought it would be six.

        // Output:
        //    250   0.0   0.0  3.7
        //    500   0.2   0.0  6.9
        //   1000   0.4   0.2  2.3
        //   2000   3.3   1.4  2.3
        //   4000  26.2  11.1  2.3
        //   8000 208.6  88.7  2.4
    }
}
