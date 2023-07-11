package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Estimate the amount of time it would take to run TwoSumFast, TwoSum, ThreeSumFast and
 * ThreeSum on your computer to solve the problems for a file of 1 million numbers.
 * Use DoublingRatio to do so.
 */
public class Ex_41_RunningTimes {
    public static int twoSum(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                if (a[i] + a[j] == 0) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }

    public static int twoSumFast(int[] a) {
        // Count pairs that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            if (BinarySearch.rank(-a[i], a) > i)
                cnt++;
        }
        return cnt;
    }

    public static int threeSum(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                for (int k = j + 1; k < N; k += 1) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static int threeSumFast(int[] a) {
        // Count triples that sum to 0.
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                if (BinarySearch.rank(-a[i]-a[j], a) > j) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static double timeTrial(int N, int flags) {
        int MAX = 1000000; // a million
        int[] a = new int[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = 0;
        if (flags == 0) {
            cnt = twoSum(a);
        }
        if (flags == 1) {
            cnt = twoSumFast(a);
        }
        if (flags == 2) {
            cnt = threeSum(a);
        }
        if (flags == 3) {
            cnt = threeSumFast(a);
        }
        return timer.elapsedTime();
    }

    public static void runTrials(int maxScale, int flags) {
        double prev = timeTrial(125, flags);
        for (int N = 250; N <= maxScale; N += N) {
            double time = timeTrial(N, flags);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time/prev);
            prev = time;
        }
    }

    public static void main(String[] args) {
        int maxScale = Integer.parseInt(args[0]);
        int flags = Integer.parseInt(args[1]);
        runTrials(maxScale, flags);

        /**
         * OutPut:
         *
         *  TwoSum(N^2):
         *    500     0.0   2.0
         *   1000     0.0   2.0
         *   2000     0.0   1.0
         *   4000     0.0   1.8
         *   8000     0.0   3.9
         *  16000     0.1   4.0
         *  32000     0.4   4.0
         *  64000     1.7   4.0
         * 128000     6.9   4.0
         * 256000    27.6   4.0
         * 512000   110.2   4.0
         *1024000   443.5   4.0
         *
         *  TwoSumFast(NlogN):
         *    250     0.0   0.0
         *    500     0.0 Infinity
         *   1000     0.0   1.0
         *   2000     0.0   1.0
         *   4000     0.0   5.0
         *   8000     0.0   0.4
         *  16000     0.0   1.5
         *  32000     0.0   1.7
         *  64000     0.0   2.0
         * 128000     0.0   2.0
         * 256000     0.0   2.3
         * 512000     0.1   1.9
         *1024000     0.1   1.4
         *
         *  ThreeSum(N^3):
         *    250     0.0   0.8
         *    500     0.0   6.0
         *   1000     0.2   7.5
         *   2000     1.4   7.9
         *   4000    11.3   8.0
         *   8000    89.5   7.9
         *
         *  ThreeSumFast(N^2logN):
         *    250     0.0   1.7
         *    500     0.0   3.2
         *   1000     0.0   0.9
         *   2000     0.1   4.9
         *   4000     0.3   4.5
         *   8000     1.3   4.3
         *  16000     5.7   4.3
         *  32000    24.3   4.3
         *  64000   102.7   4.2
         * 128000   431.7   4.2
         */
    }
}
