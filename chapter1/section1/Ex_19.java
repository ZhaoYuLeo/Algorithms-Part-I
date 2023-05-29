package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_19 {
    public static long f(int N) {
        return helper(N, 0, 1);
    }

    // Or you can put the result in an array or something else
    private static long helper(int N, int prev, int next) {
        if (N <= 0) {
            return prev;
        }
        return helper(N - 1, next, prev + next);
    }

    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }

    public static void main(String[] args) {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + f(N));
    }
}
