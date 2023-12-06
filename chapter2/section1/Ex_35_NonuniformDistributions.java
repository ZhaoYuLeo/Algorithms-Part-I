package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Write a client that generates test data by randomly ordering objects using
 * other distributions than uniform, including the following:
 * Gaussian
 * Poissn
 * Geometric
 * Discrete(see EXERCISE 2.1.28 for a special case)
 * Develop and test hypotheses about the effect of such input on the performance
 * of the algorithms in this section.
 */
public class Ex_35_NonuniformDistributions {

    public static Double[] gaussian(int N) {
        Double[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            // return random real number from a standard Gaussian distribution.
            a[i] = StdRandom.gaussian();
        }
        return a;
    } 

    public static Integer[] poissn(int N, int lambda) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            // Returns a random integer from a Poisson distribution with mean λ.
            a[i] = StdRandom.poisson(lambda);
        }
        return a;
    } 

    public static Integer[] geometric(int N, double p) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            // Returns a random integer from a geometric distribution with success probability p.
            a[i] = StdRandom.geometric(p);
        }
        return a;
    } 

    public static Integer[] gaussian(int N, double[] probabilities) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            // Returns a random integer from the specified discrete distribution.
            a[i] = StdRandom.gaussian(probabilities);
        }
        return a;
    } 

    public static void main(String[] args) {

    }
}
