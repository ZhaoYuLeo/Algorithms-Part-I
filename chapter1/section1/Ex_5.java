package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_5 {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            //not equal, strictly between.
            double precision = 0.0000001;
            StdOut.println(x > 0 && y > 0 && x < 1 && y < 1);
        }
    }
}
