package util;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import static util.Constants.*;

/**
 * Draw axis, title and labels
 */
public class DrawFrame {
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
        StdDraw.setPenRadius(PEN1);
    }

    /**
     * Setup coordinates system and title the graph 
     *
     * @param x0 min x
     * @param x1 max x
     * @param y0 min y
     * @param y1 max y
     * @param title title of the graph
     */
    public static void setup(double x0, double x1, double y0, double y1, String title) {
        setup(x0, x1, y0, y1);
        StdDraw.setYscale(Y0 - .2 * yMargin, Y1); // add margin for title
        StdDraw.setFont(TITLE_FONT);
        StdDraw.text((X1 + X0) / 2, Y0, title);
    }


    /**
     * Draw the x-axis and y-axis and title both
     *
     * @param x0 min value in x-axis recorded
     * @param x1 max value in x-axis recorded
     * @param y0 min value in y-axis recorded
     * @param y1 max value in y-axis recorded
     * @param xLabel
     * @param yLabel
     */
    public static void drawAxis(double x0, double x1, double y0, double y1, String xLabel, String yLabel) {
        StdDraw.setFont(AXISTITLE_FONT);
        StdDraw.setXscale(X0 - xPadding, X1); // add padding for title
        StdDraw.setYscale(Y0 - yPadding, Y1);
        int xBase = 0; // assume both of them start at 0
        int yBase = 0;
        StdDraw.text((x1 + x0) / 2, yBase - 5 * yDiv, xLabel);
        StdDraw.text(xBase - 5 * xDiv, (y1 + y0) / 2, yLabel, 90);
        drawAxis(x0, x1, y0, y1);
    }

    /**
     * Draw the x-axis and y-axis 
     *
     * @param x0
     * @param x1
     * @param y0
     * @param y1
     */
    public static void drawAxis(double x0, double x1, double y0, double y1) {
        int xBase = 0;
        int yBase = 0;
        // x-axis
        StdDraw.line(x0, yBase, x1, yBase);
        // y-axis
        StdDraw.line(xBase, y0, xBase, y1);
        // extra info
        StdDraw.setFont(LABEL_FONT);
        // divide lines in x-axis
        drawXDiv((int)(x0), yBase, String.valueOf((int)(x0)));
        for (int i = (int)x0 == 0 ? 1 : (int)x0; i <= x1; i += i) {
            if (i > (x1 - x0) / 10) {
                drawXDiv(i, yBase, String.valueOf(i));
            }
        }
        drawXDiv((int)(x1), yBase, String.valueOf((int)(x1)));
        // divide lines in y-axis
        drawYDiv(xBase, y0, String.valueOf(y0));
        for (int i = (int)y0 == 0 ? 1 : (int)y0; i <= y1; i += i) {
            if (i > (y1 - y0) / 10) {
                drawYDiv(xBase, i, String.valueOf(i));
            }
        }
        drawYDiv(xBase, y1, String.valueOf(y1));
    }

    public static void drawXDiv(double x, double y, String text) {
        StdDraw.setPenRadius(PEN0);
        StdDraw.line(x, y, x, y - yDiv);
        StdDraw.text(x, y - 2.5 * yDiv, text);
    }

    public static void drawYDiv(double x, double y, String text) {
        StdDraw.setPenRadius(PEN0);
        StdDraw.line(x, y, x- xDiv, y);
        StdDraw.text(x - 2.5 * xDiv, y, text);
    }

    public static void chart(double x0, double y0, double x1, double y1, String xLabel, String yLabel) {
        // assume x1 > x0 && y1 > y0;
        setup(x0, y0, x1, y1);
        drawAxis(x0, y0, x1, y1, xLabel, yLabel);
    }

    public static void chart(double x0, double y0, double x1, double y1) {
        setup(x0, y0, x1, y1);
        drawAxis(x0, y0, x1, y1);
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
