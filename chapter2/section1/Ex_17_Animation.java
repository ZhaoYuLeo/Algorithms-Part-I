package chapter2.section1;

import java.awt.Font;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Add code to Insertion and Selection to make them draw the array contents as vertical
 * bars like the visual traces in this section, redrawing the bars after each pass, to
 * produce an animated affect, ending in a "sorted" picture where the bars appear in order
 * of their height. Hint: Use a client like the one in the text that generates random
 * Double values, insert calls to show() as appropriate in the sort code, and implement
 * a show() method that clears the canvas and draws the bars.
 * 
 */
public class Ex_17_Animation {
    public static void selectSort(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            int min = i;
            for (int j = i + 1; j < N; j += 1) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            selectShow(a, i, min);
            exch(a, i, min);
        }
    }

    public static void insertSort(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            int j = i;
            while (j > 0 && less(a[j], a[j - 1])) {
                exch(a, j, j - 1);
                j -= 1;
            }
            insertShow(a, i, j);
        }
    }

    public static void shellSort(double[] a) {
        int N = a.length;
        int h = 1;
        int k = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        shellShow(a, 0, k);

        while (h >= 1) {
            for (int i = h; i < N; i += 1) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            shellShow(a, h, ++k);
            h /= 3;
        }
    }

    public static boolean less(double v, double w) {
        return v < w;
    }

    public static void exch(double[] a, int i, int j) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shellShow(double[] a, int h, int i) {
        StdOut.println(i);
        StdDraw.setXscale(-6, a.length + 1);
        StdDraw.setYscale(-a.length + i + 0.8, i + 0.8);
        double rw = 0.25;
        double scale = 0.3;
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = 0; k < a.length; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        String message = h + "-sorted";
        if (h == 0) {
            message = "input";
        }
        if (h == 1) {
            message = "result";
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.text(-2, i * scale - 0.3, message);
    }

    public static void insertShow(double[] a, int i, int j) {
        StdDraw.setYscale(-a.length + i + 0.8, i + 0.8);
        double rw = 0.25;
        double scale = 0.3;
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < j; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        for (int k = i + 1; k < a.length; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j + 1; k <= i; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(j, a[j] * scale, rw, a[j] * scale);
    }

    public static void selectShow(double[] a, int i, int min) {
        StdDraw.setYscale(-a.length + i + 0.8, i + 0.8);
        double rw = 0.25;
        double scale = 0.3;
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k += 1) {
            StdDraw.filledRectangle(k, a[k] * scale, rw, a[k] * scale);
        }
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(min, a[min] * scale, rw, a[min] * scale);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n + 1);
        //StdDraw.setYscale(-1, 2);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 13));
        StdDraw.setPenRadius(0.005);
        double[] a = new double[n];
        for (int i = 0; i < n; i += 1) {
            a[i] = StdRandom.random();
        }
        // insertSort(a);
        shellSort(a);
    }
}
