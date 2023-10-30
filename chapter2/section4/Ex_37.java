package chapter2.section4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Write a performance driver client program that uses insert to fill a priority queue, then does as many
 * remove the maximum and insert operations as it can do in 1 second, doing so multiple times on random
 * sequences of keys of various lengths ranging from small to large; and prints out or plots the average 
 * number of remove the maximum operations it was able to do.
 */
public class Ex_37 {
    /**
     * Print out the average number of remove the maximum operations done by a pq of various lengths
     */
    public static void timeTrial(int[] capacity, int times) {
        for (int i = 0; i < capacity.length; i += 1) {
            double total = 0;
            for (int t = 0; t < times; t += 1) {
                total += driver(capacity[i]);
            }
            StdOut.printf("%7d %8.5f\n", capacity[i], total / times);
        }
    }

    /**
     * Return the number of remove the maximum operations a priority queue with random keys and capacity N
     * was able to do in one second.
     */
    public static int driver(int N) {
        int count = 0;
        HeapMaxPQ<Double> pq = new HeapMaxPQ<>();
        for (int i = 0; i < N; i += 1) {
            pq.insert(StdRandom.uniform());
        } 
        Stopwatch timer = new Stopwatch();
        while (timer.elapsedTime() <= 1) { 
            pq.delMax();
            pq.insert(StdRandom.uniform());
            count += 1;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] capacity = new int[8];
        for (int i = 1; i < 9; i += 1) {
            capacity[i - 1] = (int)Math.pow(10, i);
        }
        timeTrial(capacity, 10);
        
        // Output:
        //       10 16335191.00000
        //      100 11114629.10000
        //     1000 8303826.00000
        //    10000 6359573.20000
        //   100000 2055797.10000
        //  1000000 1539928.10000
        // 10000000 930780.40000
    }

}

