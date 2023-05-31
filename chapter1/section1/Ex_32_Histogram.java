package chapter1.section1;

import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_32_Histogram {
   /*
    * Plot a histogram of the count of the numbers that fall in each of the
    * N intervals deﬁned by dividing (l , r) into N equal-sized intervals.
    */
    public static void histogram(Integer N, Double l, Double r, Double[] numbers) {
        // Statistics. Include l but not r.
        Double intervals = (r - l) / N;
        int[] counts = new int[N];
        for (Double n : numbers) {
            int index = (int)((n - l) / intervals); 
            StdOut.println(n + ", " + index);
            if (index > -1 && index < N) {
                counts[index] += 1;
            }
        }
        int max = 0;
        int min = numbers.length;
        for (int c : counts) {
            if (c > max) max = c;
            if (c < min) min = c;
        }
        // Draw the histogram
        StdDraw.setPenRadius(.01);
        double leftMargin = -0.5;
        double bottomMargin = -0.5;
        double length = 1;
        StdDraw.setXscale(leftMargin, length * 1.5);
        StdDraw.setYscale(bottomMargin, max * 1.5);
        double rw = length / (3 * N);
        StdDraw.text(rw - length / N, bottomMargin / 2, l + "");
        for (int i = 0; i < N; i += 1) {
            double x = length * i / N;
            double y = counts[i] / 2.0;
            double rh = counts[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
            double value = Math.round((l + (i + 1) * intervals) * 100.0) / 100.0;
            StdDraw.text(x + rw, bottomMargin / 2, value + "");
        }
        for (int i = 0; i <= max; i += 1) {
            StdDraw.text(leftMargin / 2, i, " " + i);
        }
    }

    public static void main(String[] args) {
        // Read from arguments
        Integer N = Integer.parseInt(args[0]);
        Double l = Double.parseDouble(args[1]);
        Double r = Double.parseDouble(args[2]);
        // Read numbers from standard input
        List<Double> numbers = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        Double[] ns = numbers.toArray(new Double[numbers.size()]);
        histogram(N, l, r, ns);
    }
}
