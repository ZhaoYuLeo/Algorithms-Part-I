package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Modify binary search so that it always returns the element with the smallest index
 * that matches the search element (and still guarantees logarithmic running time).
 */
public class Ex_10 {
    public static int binarySearchSmallestIndex(int key, int[] a) {
        Arrays.sort(a);
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo < a.length && a[lo] == key) {
            return lo;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 4, 4, 7, 9};
        int result = binarySearchSmallestIndex(Integer.parseInt(args[0]), a);
        StdOut.println(result);
    }
}
