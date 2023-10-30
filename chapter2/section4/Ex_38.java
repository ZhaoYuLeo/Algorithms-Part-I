package chapter2.section4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Write an exercise driver client program that uses the methods in our priority-queue interface of Algorithm 2.6
 * on difficult or pathological cases that might turn up in practical applications. Simple examples include keys
 * that are already in order, keys in reverse order, all keys the same, and sequences of keys having only two 
 * distinct values.
 */
public class Ex_38 {
    /**
     * Print out the average time of insert and remove operations for four different cases mentioned above
     */
    public static void timeTrial(int capacity, int times) {
        StdOut.printf("%18s | %7s | %18s | %18s\n", "Type", "Capacity", "Average Insert times", "Average DelMax times");
        String[] msg = {"in order", "in reverse order", "all keys the same", "two distinct value"};
        for (int type = 0; type < 4; type += 1) {
            double total = 0;
            double total2 = 0;
            for (int t = 0; t < times; t += 1) {
                Stopwatch timer = new Stopwatch();
                HeapMaxPQ<Double> pq = testInsert(type, capacity);
                total += timer.elapsedTime();

                timer = new Stopwatch();
                testDelMax(pq);
                total2 += timer.elapsedTime();
            }

            StdOut.printf("%18s %7d %18.5f %18.5f\n", msg[type], capacity, total / times, total2 / times);
        }
    }

    /**
     * Insert items into a priority queue with size range from 0 to N
     */
    public static HeapMaxPQ<Double> testInsert(int type, int N) {
        HeapMaxPQ<Double> pq = new HeapMaxPQ<>();
        if (type == 0) {            // in order
            for (int i = 0; i < N; i += 1) {
                pq.insert(i + StdRandom.random());
            } 
        } else if (type == 1) {     // in reverse order
            for (int i = N; i > 0; i -= 1) {
                pq.insert(i + StdRandom.random());
            }
        } else if (type == 2) {     // all the same
            double key = StdRandom.uniform();
            for (int i = 0; i < N; i += 1) {
                pq.insert(key);
            }
        } else {                    // sequences of keys having only two distinct value
            double k1 = StdRandom.uniform();
            double k2 = StdRandom.uniform();
            while (k1 == k2) {
                k2 = StdRandom.uniform();
            }
            for (int i = 0; i < N; i += 1) {
                double p = StdRandom.random();
                if (p < 0.5) {
                    pq.insert(k1);
                } else {
                    pq.insert(k2);
                }
            }
        }
        return pq;
    }

    /**
     * Delete the maximum item from a priority queue with size range from N to 0
     */
    public static void testDelMax(HeapMaxPQ<Double> pq) {
        while (!pq.isEmpty()) {
            pq.delMax();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 8; i += 1) {
            timeTrial((int)Math.pow(10, i), 10);
        }

        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order      10            0.00020            0.00000
        //   in reverse order      10            0.00010            0.00000
        //  all keys the same      10            0.00000            0.00000
        // two distinct value      10            0.00000            0.00000
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order     100            0.00050            0.00010
        //   in reverse order     100            0.00010            0.00000
        //  all keys the same     100            0.00020            0.00010
        // two distinct value     100            0.00000            0.00000
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order    1000            0.00030            0.00030
        //   in reverse order    1000            0.00000            0.00030
        //  all keys the same    1000            0.00020            0.00000
        // two distinct value    1000            0.00030            0.00000
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order   10000            0.00140            0.00150
        //   in reverse order   10000            0.00040            0.00140
        //  all keys the same   10000            0.00020            0.00010
        // two distinct value   10000            0.00020            0.00090
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order  100000            0.01400            0.01870
        //   in reverse order  100000            0.00370            0.01970
        //  all keys the same  100000            0.00280            0.00150
        // two distinct value  100000            0.00530            0.01070
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order 1000000            0.29420            0.25720
        //   in reverse order 1000000            0.05350            0.27740
        //  all keys the same 1000000            0.03850            0.02070
        // two distinct value 1000000            0.05940            0.13540
        //               Type | Capacity | Average Insert times | Average DelMax times
        //           in order 10000000            3.48630            3.11870
        //   in reverse order 10000000            0.46720            3.48720
        //  all keys the same 10000000            0.36160            0.26210
        // two distinct value 10000000            0.64500            1.66550
    }

}

