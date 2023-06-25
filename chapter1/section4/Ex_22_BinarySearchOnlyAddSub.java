package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * given an array of N distinct int values in ascending order, determines whether a given integer
 * is in the array. You may use only additions and subtractions and a constant amount of extra memory.
 * log N in the worst case.
 * https://people.csail.mit.edu/mip/probs.html
 */
public class Ex_22_BinarySearchOnlyAddSub {
    public static int binarySearchOnlyAddSub(int key, int[] a) {
        Arrays.sort(a);

        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int split = splitPoint(hi - lo);
            int indice = lo + split;

            if (key == a[indice]) {
                return indice;
            } else if (key > a[indice]) {
                lo = indice + 1;
            } else {
                hi = indice - 1;
            }

        }
        return -1;
    }

    public static int splitPoint(int value)  {
        int prev = 0;
        int cur = 1;
        while (cur <= value) {
            int temp = prev;
            prev = cur;
            cur = temp + cur;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10};
        int key = Integer.parseInt(args[0]);
        StdOut.println(binarySearchOnlyAddSub(key, a));
    }
}
