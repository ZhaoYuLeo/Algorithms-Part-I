package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

public class Ex_05 {
    public static void main(String[] args) {
        String s = "Hello World";
        // String is immutable. The objects returned has been ignored.
        s.toUpperCase();
        s.substring(6, 11);
        StdOut.println(s);
    }
}
