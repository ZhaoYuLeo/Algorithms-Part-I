package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_26 {

    public static void main(String[] args) {
        int t = 0;
        // reduce pairs in reverse order
        while (!StdIn.isEmpty()) {
            int a = StdIn.readInt();
            int b = StdIn.readInt();
            int c = StdIn.readInt();
            if (a > b) { t = a; a = b; b = t; }
            StdOut.printf(" %2d -> %2d -> %2d -> %2d\n", a, b, c, t);
            if (a > c) { t = a; a = c; c = t; }
            StdOut.printf(" %2d -> %2d -> %2d -> %2d\n", a, b, c, t);
            if (b > c) { t = b; b = c; c = t; }
            StdOut.printf(" %2d -> %2d -> %2d -> %2d\n", a, b, c, t);
        }
    }
}
