package util;

import java.awt.Font;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Draw axis, title and labels
 */
public class DrawFrame {
    private final static double pen0 = .002;
    private final static double pen1 = .004;
    private final static double pen2 = .009;

    private static double xMargin  = 0.5;
    private static double yMargin  = 0.5;
    private static double xPadding = 1;
    private static double yPadding = 1;
    private static double xDiv     = 0.1;
    private static double yDiv     = 0.1;
    private static double xScale = 1;
    private static double yScale = 1;
    private static double X0     = 0;
    private static double X1     = 1;
    private static double Y0     = 0;
    private static double Y1     = 1;

    // By default, all drawing takes places in a 512-by-512 canvas.

    /**
     * Set up the coordinate system
     *
     * @param x0 the coordinates of the minimum x-coordinates that will appear in the canvas
     * @param x1 the coordinates of the maximum x-coordinates that will appear in the canvas
     * @param y0 the coordinates of the minimum y-coordinates that will appear in the canvas
     * @param y1 the coordinates of the maximum y-coordinates that will appear in the canvas
     */
    public static void setup(double x0, double x1, double y0, double y1) {
        xScale = x1 - x0;
        yScale = y1 - y0;
        xMargin  = xScale / 10;
        yMargin  = yScale / 10;
        xPadding = xScale / 10;
        yPadding = yScale / 10;
        xDiv = xScale / 40;
        yDiv = yScale / 40;
        X0 = x0 - xMargin;
        X1 = x1 + xMargin;
        Y0 = y0 - yMargin;
        Y1 = y1 + yMargin;
        StdDraw.setXscale(X0, X1);
        StdDraw.setYscale(Y0, Y1);
        StdDraw.setPenRadius(pen1);
    }

    public static void setup(double x0, double x1, double y0, double y1, String title) {
        setup(x0, x1, y0, y1);
        StdDraw.setYscale(Y0 - .2 * yMargin, Y1);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
        StdDraw.text((X1 + X0) / 2, Y0, title);
    }


    public static void drawAxis(int x0, int x1, int y0, int y1, String xLabel, String yLabel) {
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 12));
        StdDraw.setXscale(X0 - xPadding, X1);
        StdDraw.setYscale(Y0 - yPadding, Y1);
        int xBase = 0;
        int yBase = 0;
        StdDraw.text((x1 - x0) / 2, yBase - 5 * yDiv, xLabel);
        StdDraw.text(xBase - 5 * xDiv, (y1 - y0) / 2, yLabel, 90);
        drawAxis(x0, x1, y0, y1);
    }

    public static void drawAxis(int x0, int x1, int y0, int y1) {
        int xBase = 0;
        int yBase = 0;
        // x-axis
        StdDraw.line(x0, yBase, x1, yBase);
        // y-axis
        StdDraw.line(xBase, y0, xBase, y1);
        // extra info
        StdDraw.setPenRadius(pen0);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 10));
        // divide lines in x-axis
        drawXDiv(x0, yBase, Integer.toString(x0));
        for (int i = x0 == 0 ? 1 : x0; i <= x1; i += i) {
            if (i > (x1 - x0) / 10) {
                drawXDiv(i, yBase, Integer.toString(i));
            }
        }
        drawXDiv(x1, yBase, Integer.toString(x1));
        // divide lines in y-axis
        drawYDiv(xBase, y0, Integer.toString(y0));
        for (int i = y0 == 0 ? 1 : y0; i <= y1; i += i) {
            if (i > (y1 - y0) / 10) {
                drawYDiv(xBase, i, Integer.toString(i));
            }
        }
        drawYDiv(xBase, y1, Integer.toString(y1));
    }

    private static void drawXDiv(double x, double y, String text) {
        StdDraw.line(x, y, x, y - yDiv);
        StdDraw.text(x, y - 2.5 * yDiv, text);
    }

    private static void drawYDiv(double x, double y, String text) {
        StdDraw.line(x, y, x- xDiv, y);
        StdDraw.text(x - 2.5 * xDiv, y, text);
    }

    public static void chart(double x0, double y0, double x1, double y1, String xLabel, String yLabel) {
        setup(x0, y0, x1, y1);
        drawAxis((int)x0, (int)y0, (int)x1, (int)y1, xLabel, yLabel);
    }

    public static void chart(double x0, double y0, double x1, double y1) {
        setup(x0, y0, x1, y1);
        drawAxis((int)x0, (int)y0, (int)x1, (int)y1);
    }

    public static void test() {
        StdDraw.enableDoubleBuffering();
        while (true) {
            StdDraw.clear();
            setup(0, 150, 0, 150, "Effect of cutoff");
            drawAxis(12, 150, 6, 150, "cutoff value", "running time");
            StdDraw.show();
            StdDraw.pause(2000);
            StdDraw.clear();
            setup(0, 150, 0, 150);
            drawAxis(0, 150, 0, 150);
            StdDraw.show();
            StdDraw.pause(2000);
        }
    }

    public static void main(String[] args) {
        // test();
        chart(0, 150, 0, 150, "cutoff value", "runnint");
    }
}
