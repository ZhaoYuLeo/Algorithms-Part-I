package chapter2.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import util.Cases;
import util.VisualAccumulator;

/** Write a client that uses StdDraw to plot the average running
 * times of the algorithm for random inputs and various values of
 * the array size. You may add one or two more command-line
 * arguments. Strive to design a useful tool.
 */
public class Ex_32_PlotRunningTimes {

    public static double trial(int N, Sort sort) {
        // random inputs and various values of the array size
        Integer[] a = Cases.randomArray(N, -N, N);
        // record running times of sort
        Stopwatch timer = new Stopwatch();
        sort.sort(Cases.randomArray(N));
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // Initialize arguments
        int times = Integer.parseInt(args[0]);      // the number of times the trial run
        int N     = Integer.parseInt(args[1]);      // the number of times the trial run
        Sort sort = new Shellsort();

        // Run trials
        double max = trial(N, sort);    // get a simple data
        String xLabel =  "number of sort() operations(size: " + String.format("%,8d%n", N) + ")";
        String yLabel =  "running times(s)";
        VisualAccumulator va = new VisualAccumulator(times, max, xLabel, yLabel);
        for (int i = 0; i < times; i += 1) {
            va.addDateValue(trial(N, sort));
        }
        va.stop();
        StdOut.println(va);
    }
}
