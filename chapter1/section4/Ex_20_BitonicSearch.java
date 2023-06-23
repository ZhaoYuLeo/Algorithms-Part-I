package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers.
 * Given a bitonic array of N distinct int values, determine whether a given
 * integer is in the array.
 * ~3lgN compares in the worst case.
 */
public class Ex_20_BitonicSearch {
    public static int bitonicSearch(int[] a, int key) {
        int peak = peak(a);
        int result = binarySearch(key, a, 0, peak, false);
        if (result < 0) {
            result = binarySearch(key, a, peak, a.length - 1, true);
        }
        return result;
    }

    public static int binarySearch(int key, int[] a, int lo, int hi, boolean reverse) {
        // Array must be sorted.
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if ((!reverse && key < a[mid]) || reverse && key > a[mid]) {
                hi = mid - 1;
            } else if ((!reverse && key > a[mid]) || reverse && key < a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int peak(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid == 0 || mid == a.length - 1) {
                if (a[lo] < a[hi]) {
                    return hi;
                } else {
                    return lo;
                }
            }
            if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
                return mid;
            } else if (a[mid - 1] < a[mid + 1]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1; 
    } 


    public static void main(String[] args) {
        int[][] as = {{1, 3, 5, 7, 2, 0},
                      {1, 9, 3, 2, 1, -1},
                      {1, 4, 8, 6, 3, 2},
                      {1, 2},
                      {1, 2, 3, 4, 5},
                      {18}};
        int key = Integer.parseInt(args[0]);
        for (int[] a : as) {
            StdOut.println(bitonicSearch(a, key));
        }
    }
}
