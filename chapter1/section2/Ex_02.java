package chapter1.section2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 */
public class Ex_02 {

    public static void main(String[] args) {
        if (args.length < 1) {
            StdOut.println("Please provide an integer as the number of intervals and a sequence of doubles.");
            return;
        }
        int N = Integer.parseInt(args[0]);
        if (N < 2) {
            StdOut.println("Only one interval exists.");
        }
        Interval1D[] ins = new Interval1D[N];
        int index = 0;
        while(!StdIn.isEmpty() && (index < N)) {
            double lo = StdIn.readDouble();
            double hi = StdIn.readDouble();
            ins[index] = new Interval1D(lo, hi);
            index += 1;
        }
        for (int i = 0; i < N - 1; i += 1) {
            for (int j = 1; j < N; j += 1) {
                if (ins[i].intersects(ins[j])) {
                    StdOut.println(ins[i] + ", " + ins[j]);
                }
            }
        } 
    }
}
