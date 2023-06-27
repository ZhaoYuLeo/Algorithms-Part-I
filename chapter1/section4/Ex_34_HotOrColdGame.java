package chapter1.section4;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Your goal is to guess a secret integer between 1 and N. You repeatedlly guess integers between
 * 1 and N. After each guess you learn if the guess is hotter (closer to) or colder (farther from)
 * the secret number than your previous guess.
 * finds the secret number in at most ~2 lg N guesses.
 * reduce to at most ~ 1 lg N guesses.
 */
public class Ex_34_HotOrColdGame {
    private enum Result {
        EQUAL,
        HOTTER,
        COLDER,
        SAME;
    }
    private int N;
    private int secret;

    public Ex_34_HotOrColdGame(int N) {
        this.N = N;
    }

    public void setSecret(int secret) {
        // given secret may not exist between 1 and N.
        this.secret = secret;
    }

    public int findSecret() {
        return findSecret(1, N);
    }

    private int findSecret(int preGuess, int curGuess) {
        // we will only check the right border.
        Result r = check(preGuess, curGuess);
        if (r == Result.EQUAL) {
            return curGuess;
        }
        if (r == Result.HOTTER) {
            return findSecret((preGuess + curGuess) / 2 + 1, curGuess);
        }
        if (r == Result.COLDER) {
            return findSecret(preGuess, (preGuess + curGuess) / 2);
        }
        if (r == Result.SAME && preGuess != curGuess) {
            return (preGuess + curGuess) / 2;
        }
        return -1;
    }

    private Result check(int prevGuess, int curGuess) {
        if (curGuess == this.secret) {
            return Result.EQUAL;
        }
        int gap = Math.abs(this.secret - prevGuess) - Math.abs(this.secret - curGuess);
        if (gap == 0) {
            return Result.SAME;
        }
        if (gap > 0) {
            return Result.HOTTER;
        }
        return Result.COLDER;
    }

    public double timeTrial() {
        // Time findSecret()
        Stopwatch timer = new Stopwatch();
        int result = findSecret();
        int theory = (this.secret > N || this.secret < 1) ? -1 : this.secret;
        if (result != theory) {
            StdOut.println("error occurred at N " + this.N + " secret " + this.secret + " found " + result);
        }
        StdOut.println("N " + this.N + " secret " + this.secret + " found " + result);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        // set scale
        int MAXSCALE = 400000000;
        int INTERVAL = 400000000;
        int COUNT = 100;
        // run test
        double[] results = new double[MAXSCALE / INTERVAL];
        for (int N = INTERVAL; N <= MAXSCALE; N += N) {
            Ex_34_HotOrColdGame g = new Ex_34_HotOrColdGame(N);
            double totalTime = 0;
            for (int i = 0; i < COUNT ; i += 1) {
                g.setSecret(StdRandom.uniform(1, N + 1));
                totalTime += g.timeTrial();
            }
            double averageTime = totalTime / COUNT;
            results[N / INTERVAL - 1] = averageTime;
        }
        // show result
        for (double r : results) {
            StdOut.println(r);
        }
    }
}
