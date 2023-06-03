package chapter1.section2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Computes both the mean and variance of the numbers presented as arguments to addDataValue()
 */
public class Ex_18_Accumulator {
    private double m;
    private double s;
    private int N;

    public void addDataValue(double x) {
        N++;
        // s += x;
        // m = s / N;

        // This implementation is less susceptible to roundoff error than the straightforward
        // implementation based on saving the sum of the squares of the numbers.
        s = s + 1.0 * (N-1) / N * (x - m) * (x - m);
        m = m + (x - m) / N;
    }

    public double mean() {
        return m;
    }

    public double var() {
        return s/(N - 1);
    }

    public double stddev() {
        return Math.sqrt(this.var());
    }

    public String toString() {
        return String.format("(%d values)\n Mean : %9.5f\n Var : %10.5f\n StdDev : %7.5f\n", N, m, var(), stddev());
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Ex_18_Accumulator a = new Ex_18_Accumulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.uniform());
        StdOut.println(a);
    }
}
