package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_15 {
    /*
     * returns an array of length M whose ith entry is the number of times the integer i appeared in the argument array.
     */
    public static int[] histogram(int[] a, int M) {
        //Assume all integers in a are smaller than M.
        int[] times = new int[M];
        for (int i = 0; i < a.length; i += 1) {
            int ith = a[i];
            if (ith < M) {
                times[ith] += 1;
            }
        }
        return times;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 0, 2, 2, 2, 3, 3, 3, 3, 5};
        for (int i : a) {
            StdOut.print(i);
        }
        StdOut.println();
        int[] b = histogram(a, 6);
        for (int i : b) {
            StdOut.print(i);
        }
        StdOut.println();
    }
}
