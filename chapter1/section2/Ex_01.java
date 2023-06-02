package chapter1.section2;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * Point2D client takes an integer value N from the command line,
 * generates N random points in the unit square, and computes the
 * distance separpating the closest pair of points.
 */
public class Ex_01 {
    public static Point2D[] creatPoints(int N) {
        Point2D[] ps = new Point2D[N];
        for (int i = 0; i < N; i += 1) {
            ps[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
        }
        return ps;
    }

    public static Point2D[] closestPair(Point2D[] ps) {
        if (ps.length < 2) {
            throw new IllegalArgumentException("No distance to compare for one points."); 
        }
        double shortest = ps[0].distanceTo(ps[1]);
        Point2D[] closestPair = {ps[0], ps[1]}; 
        for (int i = 0; i < ps.length - 1; i += 1) {
            for (int j = i + 1; j < ps.length; j += 1) {
                double d = ps[i].distanceTo(ps[j]);
                if (d < shortest) {
                    shortest = d;
                    closestPair[0] = ps[i];
                    closestPair[1] = ps[j];
                }
            }
        }
        return closestPair;
    }

    public static void drawPoints(Point2D[] ps) {
        StdDraw.setPenRadius(.025);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);
        for (int i = 0; i < ps.length; i += 1) {
            ps[i].draw();
        }
        Point2D[] closest = closestPair(ps);
        double x0 = closest[0].x();
        double y0 = closest[0].y();
        double x1 = closest[1].x();
        double y1 = closest[1].y();
        StdDraw.line(x0, y0, x1, y1);
        double tx = (x0 + x1) / 2.0 + 0.05;
        double ty = (y0 + y1) / 2.0 - 0.05;
        double ds = Math.round(closest[0].distanceTo(closest[1]) * 100) / 100.0;
        StdDraw.text(tx, ty, "" + ds);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Please provide an integer as the number of random points.");
            return;
        }
        int N = Integer.parseInt(args[0]);
        drawPoints(creatPoints(N));
    }
}
