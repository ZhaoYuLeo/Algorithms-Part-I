package chapter2.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Queue;

import util.Cases;
import util.DrawFrame;
import static util.Constants.*;

/**
 * Write a clieant that enters into an infinite loop running sort() on
 * arrays of the size given as the third command-line argument, measures
 * the time taken for each run, and uses StdDraw to plot the average
 * running times. A picture of the distribution of the running times
 * should emerge.
 */
public class Ex_33_Distribution {

    public static double trial(int N, Sort sort) {
        // random inputs and various values of the array size
        Integer[] a = Cases.randomArray(N, -N, N);
        // record running times of sort
        Stopwatch timer = new Stopwatch();
        sort.sort(Cases.randomArray(N));
        return timer.elapsedTime() * 1000;
    }

    public static void experiment(int N, Sort sort) {
        Queue<Double> q = new Queue<>();
        StdDraw.enableDoubleBuffering();

        int counter = 100;
        int times = 0;
        double max = trial(N, sort);
        while (true) {
            double val = trial(N, sort);
            times += 1;
            if (max < val) {
                max = val;
            }
            q.enqueue(val);
            if (times == counter) {
                counter += 100;
                StdDraw.clear();
                DrawFrame.chart(0, counter, 0, max);
                double total = 0;
                double i = 0;
                for (Double e : q) {
                    total += e;
                    i += 1;
                    StdDraw.setPenColor(StdDraw.DARK_GRAY);
                    StdDraw.point(i, e);
                    StdDraw.setPenColor(RED);
                    StdDraw.point(i, total/i);
                    StdDraw.setPenRadius(PEN1);
                    if (i * 10 % counter == 0) {
                        StdDraw.setPenColor(BLUE);
                        StdDraw.textLeft(i, e * 1.1, String.format("%.3f",e));
                    }
                }
                DrawFrame.drawYDiv(0, total/i, String.format("%.4f", total/i));
                StdDraw.show();
            }
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        experiment(N, new Shellsort());
    }
}
