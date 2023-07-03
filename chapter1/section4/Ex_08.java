package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Determine the number pairs of values in an input file that are equal.
 */
public class Ex_08 {

    /**
     * Return the number of pairs (consist of different items in given array) of which
     * two items have same value.
     */
    public static int count(int[] values) {
        Arrays.sort(values);
        int count = 0;
        int total = 0;
        for (int i = 0; i < values.length - 1; i += 1) {
            if (values[i] == values[i + 1]) {
                count += 1;
            } else { 
                total += (count * (count + 1)) / 2;
                count = 0;
            }
        }
        if (count > 0) {
            total += (count * (count + 1)) / 2;
        }
        return total;
    }

    public static void main(String[] args) {
        int[] a = {2, 2, 2};
        // assume 3
        StdOut.println(count(a));
        int[] b = {0, 0, 0, 3, 5, 5, 6, 7, 7, 7, 8};
        // assume 7
        StdOut.println(count(b));
    }
}
