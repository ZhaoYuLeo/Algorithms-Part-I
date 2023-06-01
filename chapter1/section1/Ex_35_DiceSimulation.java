package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex_35_DiceSimulation {

    /*
     * The exact probability distribution of the sum of two dice.
     * The value dist[k] is the probability that the dice sum to k.
     */
    public static double[] theory() {
        int SIDES = 6;
        double[] dist = new double[2*SIDES+1];
        // throw two dices and record the occurrence of each sum.
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++)
                dist[i+j] += 1.0;
        for (int k = 2; k <= 2*SIDES; k++)
            dist[k] /= 36.0;
        return dist;
    }

    /*
     * Simulate N dice throws.
     */
    public static double[] experiment(long N) {
        int SIDES = 6;
        int dice1;
        int dice2;
        double[] rolls = new double[2 * SIDES + 1];
        for (int t = 0; t < N; t += 1) {
            dice1 = StdRandom.uniform(1, SIDES + 1);
            dice2 = StdRandom.uniform(1, SIDES + 1);
            rolls[dice1 + dice2] += 1;
        }
        for (int k = 2; k <= 2 * SIDES; k += 1) {
            rolls[k] /= N; 
        }
        return rolls;
    } 
    public static void main(String[] args) {
        double[] theory = theory();
        for (double t : theory) {
            StdOut.printf(" %5.3f", t);
        }
        StdOut.println();
        long N = 100000;
        double[] experiment = experiment(N);
        for (double e : experiment) {
            StdOut.printf(" %5.3f", e);
        }
        StdOut.println();
        double precision = 0.001;
        while(true) {
            int equal = 0;
            double[] e = experiment(N);
            for (int i = 0; i < theory.length; i += 1) {
                if ((theory[i] - e[i]) < precision) {
                    equal += 1;
                }
            }
            if (equal == theory.length) {
                break;
            }
            StdOut.println(N + " experiments  with " + equal + " equal. total " + theory.length);
            N += 1;
        }
        StdOut.println("N is " + N);
    }
}
