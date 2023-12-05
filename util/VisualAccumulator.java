package util;

import java.awt.Font;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import static util.Constants.*;

/**
 * Add more information to VisualAccumulator
 */
public class VisualAccumulator {
    private double total;
    private int N;
    private Node peak;
    private Node last;

    private class Node {
        public double x;
        public double y;

        public Node() {
            this.x = 0.0;
            this.y = 0.0;
        }

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void update(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Node n) {
            if (this.y > n.y) {
                return 1;
            } else if (this.y < n.y) {
                return -1;
            } else {
                return 0;
            }
        }

        public String toString() {
            return "x: " + x + "y: " + y;
        }
    }

    public VisualAccumulator(int trials, double max) {
        DrawFrame.chart(0, trials, 0, max);
        StdDraw.setPenRadius(PEN1);
        this.peak = new Node();
        this.last = new Node();
    }

    public VisualAccumulator(int trials, double max, String xLabel, String yLabel) {
        DrawFrame.chart(0, trials, 0, max, xLabel, yLabel);
        StdDraw.setPenRadius(PEN1);
        this.peak = new Node();
        this.last = new Node();
    }

    public void addDateValue(double val) {
        N += 1;
        last.update(N, val);
        if (peak.compareTo(last) < 0) {
            peak.update(N, val);
        }
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(RED);
        StdDraw.point(N, total/N);
    }

    public void stop() {
        StdDraw.setPenRadius(PEN2);
        StdDraw.setFont(LABEL_FONT);

        // Show Max
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(peak.x, peak.y);
        StdDraw.setPenColor(RED);
        StdDraw.textLeft(peak.x, peak.y * 1.2, String.valueOf(peak.y));

        // Show Last
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(last.x, last.y);
        StdDraw.setPenColor(RED);
        StdDraw.textLeft(last.x, last.y * 1.2, String.valueOf(last.y));

        // Show Mean
        DrawFrame.drawYDiv(0, mean(), String.format("%.2f", mean()));
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "n = " + N + ", mean = " + mean();
    }

    public static void main(String[] args) {
    }
}
