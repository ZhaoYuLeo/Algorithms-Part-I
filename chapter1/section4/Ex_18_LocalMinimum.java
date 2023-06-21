package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * <---|--I--l-I--|-----> curve, the relationship between three points or two.
 * Find a local minimum: an index i such that `a[i] < a[i-1]` and `a[i] < a[i+1].`
 * ~2lg N compares in the worst case.
 */
public class Ex_18_LocalMinimum {
    /**
     * Return the index i at which the item is a local minimun in given array.
     */
    public static int localMinimum(double[] a) {
        // you can't change the original order
        int lo = 0;
        int hi = a.length - 1;
        // 1 3 5 9 6 11; How to ensure that a local minimum can be found?
        // 2 2 2 2 2 2; Assume elements in a are distinct.
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // Thanks to @templatetypedef. What he explained in https://stackoverflow.com/questions/12238241/find-local-minima-in-an-array is surprisingly clear.
            if (mid == 0 || mid == a.length - 1) {
                if (a[lo] < a[hi]) {
                    return lo;
                } else {
                    return hi;
                }
            }

            if (a[mid] < a[mid + 1] && a[mid] < a[mid - 1]) {
                return mid;
            } else if (a[mid - 1] < a[mid + 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1; // no found
    }

    public static void main(String[] args) {
        double[][] as = {{0, 1},
                         {1, 3, 5, 9, 6, 11},
                         {1, 3, 5, 9, 4, 11, 12},
                         {8, 2, 9, 7, 6},
                         {16},
                         {1, 0}};

        for (double[] a : as) {
            StdOut.println(a[localMinimum(a)]);
        }
    }
}
