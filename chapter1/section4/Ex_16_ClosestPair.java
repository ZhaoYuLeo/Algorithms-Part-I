package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * <--|--|--|--|--|--|--|--|--|--|--|-->
 * Closest pair in one dimension: two values whose difference is no greater
 * than the difference if any other pair(in absolute value).
 * linearithmic in the worst case.
 */
public class Ex_16_ClosestPair {
    public static double[] closestPair(double[] a) {
        Arrays.sort(a);
        double min = Double.POSITIVE_INFINITY;
        int indice = -1;
        for (int i = 0; i < a.length - 1; i += 1) {
            double distance = a[i + 1] - a[i];
            if (distance < min) {
                min = distance;
                indice = i;
            }
        }
        return Arrays.copyOfRange(a, indice, indice + 2);
    }

    public static void main(String[] args) {
        double[] a = {-0.54, -1.58, 8.49, 7.45, 5.32, 7.58, 8.84, 5.845};
        for (double e : closestPair(a)) {
            StdOut.println(e);
        }
    }
}
