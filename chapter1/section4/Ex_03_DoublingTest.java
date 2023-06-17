package chapter1.section4;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class Ex_03_DoublingTest {
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

    public static void paint(Queue<Integer> rawScale, Queue<Double> rawTime, int isLog) {
        // Better to autoscale. Just need to know the first and the last.
        StdDraw.setXscale(0, 2050);
        StdDraw.setYscale(-1, 3);
        StdDraw.setPenRadius(0.02);
        double x;
        double y;
        // Assume rawScale and rawTime have the same length
        while (!rawScale.isEmpty()) {
            x = rawScale.dequeue();
            y = rawTime.dequeue();
            if (isLog == 0) {
                y = Math.log(y);
                StdDraw.setYscale(-6, 1);
            }
            StdOut.println(y);
            StdDraw.point(x, y);
        }
    }


    public static void main(String[] args) {
        // Print table of running times.
        Queue<Integer> rawScale = new Queue<>(); 
        Queue<Double> rawTime = new Queue<>();
        int MaxScale = 4000; // pass it from the command-line
        for (int N = 250; N < MaxScale; N += N) {
            // Print time for problem size N.
            double time = timeTrial(N);
            rawScale.enqueue(N);
            rawTime.enqueue(time);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
        paint(rawScale, rawTime, Integer.parseInt(args[0]));
    }
}
