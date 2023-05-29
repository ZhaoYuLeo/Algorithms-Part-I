package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_27_BinomialDistribution {
    private static long count = 0;
    private static long count2 = 0;


    public static double binomial(int N, int k, double p) {
        // too big to count
        count += 1;
        if ((N == 0) && (k == 0)) return 1.0;
        if ((N < 0) || (k < 0)) return 0.0;
        return (1.0 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
    }

    // cache the previous results using (N+1)*(k+1) array.
    public static double binomial2(int N, int k, double p) {
        double [][] cache = new double[N + 1][k + 1];
        for (int i = 0; i < N + 1; i += 1) {
            for (int j = 0; j < k + 1; j += 1) {
                cache[i][j] = -1;
            }
        }
        return binomia2_helper(N, k, p, cache);
    }

    //Maybe you can also write it from the bottom to the top.
    public static double binomia2_helper(int N, int k, double p, double[][] cache) {
        count2 += 1;
        if ((N == 0) || (k < 0)) return 1.0;
        if ((N < 0) || (k < 0)) return 0.0;
        // We store most of them in the cache when the first time hit the bottom.
        if (cache[N][k] < 0) {
            double first = binomia2_helper(N - 1, k, p, cache);
            double second = binomia2_helper(N - 1, k - 1, p, cache);
            cache[N][k] = (1.0 - p) * first + p * second;
        }
        return cache[N][k];
    }

    public static void main(String[] args) {
        Double bino2 = binomial2(100, 50, 0.25);
        StdOut.println("The result for binomial2(100, 50, 0.25) is " + bino2 + ".\nCount: " + count2);
        //Double bino = binomial(100, 50, 0.25);
        //StdOut.println("The result for binomial(100, 50, 0.25) is " + bino + ".\nCount: " + count);
    }
}
