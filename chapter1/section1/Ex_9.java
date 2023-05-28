package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_9 {
    public static int convert(int N) {
        int result = 0;
        int i = 1;
        for (int n = N; n > 0; n = n >> 1) {
            result += (n & 1) * i;
            i = 10 * i;
        }
        return result;
    }

    /**
     * Puts the binary representation of a positive integer N into a String s.
     */
    public static void main(String[] args) {
        // Integer.toBinaryString(N)
        while (!StdIn.isEmpty()) {
            String s = "";
            int N = StdIn.readInt();
            /*
            for (int n = N; n > 0; n /= 2) {
                s = (n % 2) + s;
            }
            */
            StdOut.println("The binary representation for " + N + " is " + convert(N));
        }
    }
}
