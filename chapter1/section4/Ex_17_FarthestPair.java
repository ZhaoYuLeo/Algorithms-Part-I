package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * <--|--------------------|-->
 * Farthest pair (in one dimension). Two values whose difference is no
 * smaller than the the difference of any other pair (in absolute value).
 * linear in the worst case.
 */
public class Ex_17_FarthestPair {
    public static double[] farthestPair(double[] a) {
        double[] pair = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        for (int i = 0; i < a.length; i += 1) {
            if (a[i] < pair[0]) {
                pair[0] = a[i];
            }
            if (a[i] > pair[1]) {
                pair[1] = a[i];
            }
        }
        return pair;
    }

    public static void main(String[] args) {
        double[] a = {-0.54, -1.58, 8.49, 7.45, 5.32, 7.58, 8.84, 5.845};
        for (double e : farthestPair(a)) {
            StdOut.println(e);
        }
    }
}
