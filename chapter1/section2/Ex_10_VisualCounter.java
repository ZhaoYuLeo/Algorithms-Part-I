package chapter1.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_10_VisualCounter {
    private int maxOperation;
    private int maxCount;
    private int count;
    private int operation;

    public Ex_10_VisualCounter(int N, int max) {
        maxOperation = N;
        maxCount = max;
        StdDraw.setPenRadius(0.025);
        StdDraw.setXscale(0, N + 5);
        StdDraw.setYscale(-1 - max, max + 5);
    }

    public void increment() {
        if (operation < maxOperation && count < maxCount) {
            count += 1;
            operation += 1;

            draw();
        }
    }

    public void decrement() {
        if (operation < maxOperation && Math.abs(count) < maxCount) {
            count -= 1;
            operation += 1;

            draw();
        }
    }

    private void draw() {
        StdDraw.point(operation, count);
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + "";
    }

    public static void main(String[] args) {
        int N = 8;
        int max = 6;
        if (args.length == 2) {
            N = Integer.parseInt(args[0]);
            max = Integer.parseInt(args[1]);
        }
        Ex_10_VisualCounter counter = new Ex_10_VisualCounter(N, max);
        counter.increment();
        counter.decrement();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.decrement();
        counter.increment();
        // Nothing changed.
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.increment();
    }
}
