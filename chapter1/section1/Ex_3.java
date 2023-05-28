package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_3 {
    public static void main(String[] args) {
        while (!StdIn.isEmpty()) {
            int first = StdIn.readInt();
            int second = StdIn.readInt();
            int third = StdIn.readInt();
            if (first == second && second == third) {
                StdOut.println("equal");
            } else {
                StdOut.println("not");
            }
        }
    }
}
