package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_14 {
    /*
     * Returns the largest int not larger than the base-2 logarithm of N.
     */
    public static int lg(int N) {
        int i = 0;
        for (int n = N; n > 1; n /= 2) {
            i += 1;
        }
        return i;
    }

    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            int N = StdIn.readInt();
            StdOut.println(lg(N));
        }
    }
}
