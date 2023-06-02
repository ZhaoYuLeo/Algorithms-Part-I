package chapter1.section2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/*
 */
public class Ex_03 {

    public static double[] createLoHi(double min, double max) {
        double[] loHi = new double[2];
        double d1 = StdRandom.uniform(min, max);
        double d2 = StdRandom.uniform(min, max);
        loHi[0]  = d1;
        loHi[1] = d2;
        if (d1 > d2) {
            loHi[0] = d2;
            loHi[1] = d1;
        }
        return loHi;
    }

    public static void draw2D(int N, double min, double max) {
        StdDraw.setPenRadius(0.002);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        Interval2D[] boxs = new Interval2D[N];
        // to determine whether this interval2D is contained in another.
        Point2D[][] diagon = new Point2D[N][2];
        for (int i = 0; i < N; i += 1) {
            // // TODO: Evenly distributed?
            // double hi = StdRandom.uniform(min, max);
            // double lo = StdRandom.uniform(min, hi);
            double[] i1 = createLoHi(min, max);
            double[] i2 = createLoHi(min, max);
            Interval1D x = new Interval1D(i1[0], i1[1]);
            Interval1D y = new Interval1D(i2[0], i2[1]);
            diagon[i][0] = new Point2D(i1[0], i2[0]); // left-bottom points
            diagon[i][1] = new Point2D(i1[1], i2[1]); // right-top points
            boxs[i] = new Interval2D(x, y);
            boxs[i].draw();
        }

        int contains = 0;
        int intersects = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (j != i && boxs[i].contains(diagon[j][0]) && boxs[i].contains(diagon[j][1])) {
                    // a contains b. b doesn't contain a.
                    contains += 1;
                    StdDraw.setPenColor(StdDraw.BLUE);
                    boxs[j].draw();
                }
                if (j > i && boxs[i].intersects(boxs[j])) {
                    // a intersects b. vice versa.
                    intersects += 1;
                }
            }
        }
        StdOut.println("Contains: " + contains);
        StdOut.println("Intersects: " + intersects);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            // provide some informations about input.
            StdOut.println("Please provide an integer as the number of intervals and a sequence of doubles.");
            return;
        }
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        draw2D(N, min, max);
    }
}
