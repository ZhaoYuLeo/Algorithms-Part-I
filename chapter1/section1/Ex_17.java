package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_17 {

    public static String exR2(int n) {
        //We won't reach the base case. The right way to do it is in Ex_16.
        String s = exR2(n-3) + n + exR2(n-2) + n;
        if (n <= 0) return "";
        return s;
    }

    public static void main(String[] args) {
        StdOut.println(exR2(6));
    }
}
