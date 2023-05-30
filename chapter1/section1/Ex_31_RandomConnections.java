package chapter1.section1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ex_31_RandomConnections {
   /*
     * Plots N equally spaces dots of size .05 on the circumference of a circle,
     * and then, with probability p for each pair of points, draws a gray line
     * connecting them.
     */
    public static void draw(int N, double p) {
        // I don't know what size .05 means.
        // I draw a cirle with radius of 1. And try to set the diameters of 
        // these dots to 0.05.
        // 1.Setup
        StdDraw.setPenRadius(.025);
        double center_x = 1.5;
        double center_y = 1.5;
        int radius = 1;
        StdDraw.setXscale(0, 2 * center_x);
        StdDraw.setYscale(0, 2 * center_y);
        // 2.Draw points
        double[][] points = new double[N][N];
        for (int i = 0; i < N; i += 1) {
            double x = radius * Math.cos(2 * Math.PI * i / N) + center_x;
            double y = radius * Math.sin(2 * Math.PI * i / N) + center_y;
            StdDraw.point(x, y);
            points[i][0] = x;
            points[i][1] = y;
        }
        // 3.Connect these points.
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                if (StdRandom.bernoulli(p)) {
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
                }
            }
        }
        // 4.Draw the circle for better recognition. 
        StdDraw.setPenRadius(.001);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.circle(center_x, center_y, radius);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide two arguments: the number of points and the probability");
        }
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        // if (N < 0 && p < 0) {}
        draw(N, p);
    }
}
