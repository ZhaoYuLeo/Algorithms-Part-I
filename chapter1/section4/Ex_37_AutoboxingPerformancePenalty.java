package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Determine the performance penalty for using autoboxing and auto-unboxing.
 * 
 */
public class Ex_37_AutoboxingPerformancePenalty {
    public static class FixedCapacityStackOfInts {
        private int[] a;    // stack entries
        private int N;      // size

        public FixedCapacityStackOfInts(int cap) {
            a = new int[cap];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(int item) {
            a[N++] = item;
        }

        public int pop() {
            return a[--N];
        }
    }

    public static class FixedCapacityStack<Item> {
        private Item[] a;   // stack entries
        private int N;      // size

        public FixedCapacityStack(int cap) {
            a = (Item[]) new Object[cap];
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public void push(Item item) {
            a[N++] = item;
        }

        public Item pop() {
            return a[--N];
        }  
    }

    // a large number of push() and pop() operations
    public static double timeTrial(int N, int flags) {
        Stopwatch timer = new Stopwatch();
        if (flags == 0) {
            FixedCapacityStackOfInts s = new FixedCapacityStackOfInts(N);
            for (int i = 0; i < N; i += 1) {
                s.push(i);
                s.pop();
            }
        } else if (flags == 1) {
            FixedCapacityStackOfInts s = new FixedCapacityStackOfInts(N);
            for (Integer i = 0; i < N; i += 1) {
                s.push(i);
                s.pop();
            }
        } else if (flags == 2) {
            FixedCapacityStack<Integer> s = new FixedCapacityStack<>(N);
            for (int i = 0; i < N; i += 1) {
                s.push(i);
                s.pop();
            }
        }
        return timer.elapsedTime();
    }

    public static void runTrials(int maxScale, int flags) {
        double prev = timeTrial(125, flags);
        for (int N = 250; N < maxScale; N += N) {
            double time = timeTrial(N, flags);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time/prev);
            prev = time;
        }
    }


    public static void main(String[] args) {
        int maxScale = 100000000;
        StdOut.println("no");
        runTrials(maxScale, 0);
        StdOut.println("auto-unboxing");
        runTrials(maxScale, 1);
        StdOut.println("autoboxing");
        runTrials(maxScale, 2);

        // Output:
        // no
        // 16384000     0.0   1.2
        // 32768000     0.0   1.9
        // 65536000     0.0   1.3
        // 
        // auto-unboxing
        // 16384000     0.1   2.0
        // 32768000     0.2   1.9
        // 65536000     0.5   2.0
        // 
        // auto-boxing
        // 16384000     0.2   1.9
        // 32768000     0.4   2.1
        // 65536000     0.7   1.9
    }
}
