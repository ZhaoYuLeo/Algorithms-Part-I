package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Write a performance driver client program that uses insert to fill a priority queue, then uses remove the
 * maximum to remove half the keys, then uses insert to fill it up again, then uses remove the maximum to
 * remove all the keys, doing so multiple times on random sequences of keys of various lengths ranging from 
 * small to large; measures the time taken for each run; and prints out or plots the average running times.
 */
public class Ex_36 {
    public static void timeTrial(int[] capacity, int times) {
        for (int i = 0; i < capacity.length; i += 1) {
            double total = 0;
            for (int t = 0; t < times; t += 1) {
                Stopwatch timer = new Stopwatch();
                driver(StdRandom.uniform(capacity[i]));
                total += timer.elapsedTime();
            }
            StdOut.printf("%7d %8.5f\n", capacity[i], total / times);
        }
    }

    public static void driver(int N) {
        HeapMaxPQ<Double> pq = new HeapMaxPQ<>();
        for (int i = 0; i < N; i += 1) {
            pq.insert(StdRandom.uniform());
        } 
        for (int i = 0; i < N / 2; i += 1) {
            pq.delMax();
        }
        for (int i = N / 2; i < N; i += 1) {
            pq.insert(StdRandom.uniform());
        }
        while (!pq.isEmpty()) {
            pq.delMax();
        }
    }

    public static void main(String[] args) {
        int[] capacity = new int[8];
        for (int i = 1; i < 9; i += 1) {
            capacity[i - 1] = (int)Math.pow(10, i);
        }
        timeTrial(capacity, 10);

        // Output:
        //       10  0.00040
        //      100  0.00090
        //     1000  0.00110
        //    10000  0.00300
        //   100000  0.02220
        //  1000000  0.34150
        // 10000000  2.51990
    }

}

