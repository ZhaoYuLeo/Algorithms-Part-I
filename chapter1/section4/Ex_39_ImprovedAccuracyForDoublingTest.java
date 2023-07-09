package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

/**
 * Modify DoublingRatio to take a second command-line argument that specifies the
 * number of calls to make to timeTrial() for each value of N. Run your program
 * for 10,000 and 1,000 trials and comment on the precision of the results.
 */
public class Ex_39_ImprovedAccuracyForDoublingTest {
    public static double timeTrial(int N) {
        // Time ThreeSum.count() for N random 6-digit ints.
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }

    public static double[][] runTrials(int maxScale, int times) {
        double prev = 0;
        for (int i = 0; i < times; i +=1) {
            prev += timeTrial(125);
        }
        prev = prev / times;
        int cap = (int)(Math.log(maxScale / 250) / Math.log(2)) + 1;
        double[][]  result = new double[cap][times];
        int curIndex = 0;
        for (int N = 250; N <= maxScale; N += N) {
            for (int i = 0; i < times; i +=1) {
                result[curIndex][i] = timeTrial(N);
            }
            double time = StdStats.mean(result[curIndex]);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time/prev);
            prev = time;
            curIndex += 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int maxScale = Integer.parseInt(args[0]);
        int times = Integer.parseInt(args[1]);
        double[][] result = runTrials(maxScale, times);
        for (double[] r : result) {
            StdOut.println(StdStats.stddev(r));
        }
       /* output:
            250      8.164965809277262E-4
            500      4.216370213557839E-4
            1000     4.8304589153964835E-4
            2000     0.003198958163736023
            4000     0.015485118304000397
       */
    }
}
