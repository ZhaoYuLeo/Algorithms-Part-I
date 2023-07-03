package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Given two sorted arrays of N int values, prints all elements that appear in both arrays,
 * in sorted order. The running time of your program should be proportional to N in the worst
 * case.
 */
public class Ex_12 {
    public static void printOverlap(int[] a, int[] b) {
        // Assume a and b are both sorted arrays
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i += 1;
            } else if (a[i] > b[j]) {
                j += 1;
            } else {
                // if an element appears in any array multiply time, we only print out once.
                StdOut.print(a[i] + " ");
                i += 1;
                j += 1;
            }
        }
        StdOut.println();
    } 

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 5, 5, 6, 9};
        int[] b = {3, 4, 5, 6, 6, 7, 8, 9, 9};
        printOverlap(a, b);
    }
}
