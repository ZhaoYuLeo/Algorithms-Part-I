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
    private int countGuess;

    public Ex_34_HotOrColdGame(int N) {
        this.N = N;
    }

    public void setSecret(int secret) {
        // The given secret may not exist between 1 and N.
        this.secret = secret;
    }

    public int findSecret() {
        this.countGuess = 0;
        return findSecret(1, N);
    }

    private int findSecret(int preGuess, int curGuess) {
        // We will only check the right border.
        // This step should be considered as two guesses.
        // The last gusess can never be reused in worst case.
        // 1(4)------5(8)---7--3(6)-------------2
        Result r = check(preGuess, curGuess);
        if (r == Result.EQUAL) {
            return curGuess;
        }
        if (r == Result.HOTTER) {
            // (a + b) / 2 may cause overflow
            this.countGuess += 1;
            return findSecret(curGuess, preGuess + (curGuess - preGuess) / 2 + 1);
        }
        if (r == Result.COLDER) {
            this.countGuess += 2;
            return findSecret(preGuess, preGuess + (curGuess - preGuess) / 2);
        }
        if (r == Result.SAME && preGuess != curGuess) {
            return preGuess + (curGuess - preGuess) / 2;
        }
        return -1;
    }

    private int findSecretLgN() {
        this.countGuess = 0;
        return findSecretLgN(1, N, 1, N);
    }

    /**
     * Our purpose is to divide the area by two after each guess we made.
     * 3*******1|----------------|2**4
     *          |--------|
     *               |---|
     * ~lg N + 1
     */
    private int findSecretLgN(int lo, int hi, int preGuess, int curGuess) {
        int mid = lo + (hi - lo) / 2;
        Result r = check(preGuess, curGuess);
        if (r == Result.EQUAL) {
            return curGuess;
        }
        if (r == Result.SAME && preGuess != curGuess) {
            // the secret is the split point
            return preGuess + (curGuess - preGuess) / 2;
        }
        if (lo >= hi) {
            return -1;
        }
        if (r == Result.HOTTER && curGuess > mid || r == Result.COLDER && curGuess < mid) {
            this.countGuess += 1;
            return findSecretLgN(mid + 1, hi, curGuess, hi + mid + 1 - curGuess);
        }
        if (r == Result.COLDER && curGuess > mid || r == Result.HOTTER && curGuess < mid) {
            this.countGuess += 1;
            return findSecretLgN(lo, mid, curGuess, lo + mid - curGuess); 
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

    public void checkResult(int result) {
        int theory = (this.secret > N || this.secret < 1) ? -1 : this.secret;
        if (result != theory) {
            StdOut.println("error occurred at N " + this.N + " secret " + this.secret + " found " + result);
        }
    }

    public double countTrial() {
        // Count guesses
        checkResult(findSecret());
        return this.countGuess;
    }

    public double countTrialLgN() {
        checkResult(findSecretLgN());
        return this.countGuess;
    }

    public double timeTrial() {
        // Time findSecret()
        Stopwatch timer = new Stopwatch();
        checkResult(findSecret());
        return timer.elapsedTime();
    }

    public static void draw(double[] xs, double[] ys) {
        // Assume we get two sorted array.
        for (int i = 0; i < xs.length; i += 1) {
            // N -> lg N
            xs[i] = (Math.log(xs[i]) / Math.log(2));
        }
        StdDraw.setXscale(xs[0] - 1, xs[xs.length - 1] + 1);
        StdDraw.setYscale(ys[0] - 1, ys[ys.length - 1] + 1);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i< xs.length; i += 1) {
            StdDraw.point(xs[i], ys[i]);
        }
    }

     public static void draw(double[] xs, double[] ys, double[] ys2) {
        // Assume we get two sorted array.
        for (int i = 0; i < xs.length; i += 1) {
            // N -> lg N
            xs[i] = (Math.log(xs[i]) / Math.log(2));
        }
        StdDraw.setXscale(xs[0] - 1, xs[xs.length - 1] + 1);
        StdDraw.setYscale(Math.min(ys[0], ys2[0]) - 1, 
                          Math.max(ys[ys.length - 1], ys[ys2.length - 1]) + 1);
        StdDraw.setPenRadius(0.02);
        for (int i = 0; i< xs.length; i += 1) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(xs[i], ys[i]);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(xs[i], ys2[i]);
        }
    }

    public static void main(String[] args) {
        // set scale
        int MAXSCALE = 1000000;
        int INTERVAL = 250;
        int COUNT = 100000;
        // run test
        double[] results = new double[(int)(Math.log(MAXSCALE / INTERVAL) / Math.log(2)) + 1];
        double[] ns = new double[results.length];
        double[] results2 = new double[results.length];
        int i = 0;
        for (int N = INTERVAL; N <= MAXSCALE; N += N) {
            Ex_34_HotOrColdGame g = new Ex_34_HotOrColdGame(N);
            double totalGuess = 0;
            double totalGuess2 = 0;
            // double totalTime = 0;
            for (int c = 0; c < COUNT ; c += 1) {
                // Argument StdRandom.uniform can take is less than Integer.MAX_VALUE
                g.setSecret(StdRandom.uniform(1, N + 1));
                totalGuess += g.countTrial();
                totalGuess2 += g.countTrialLgN();
                // totalTime += g.timeTrial();
            }
            ns[i] = N;
            results[i] = totalGuess / COUNT;
            results2[i++] = totalGuess2 / COUNT;
            // results[i++] = totalTime;
        }
        // show result
        for (double r : results) {
            StdOut.println(r);
        }
        // draw plot
        Ex_34_HotOrColdGame.draw(ns, results, results2);

        // // draw plot(result of one experiment) 
        // int MAXSCALE = 1000000000;
        // int INTERVAL = 100000000;
        // int COUNT = 100000000;
        // double[] x = new double[10];
        // for (int N = INTERVAL; N <= MAXSCALE; N += INTERVAL) {
        //     x[N / INTERVAL - 1] = N;
        // }
        // double[] y = {15.288999999996966,
        //          15.799999999996682,
        //          16.393999999997053,
        //          16.517999999997205,
        //          16.745999999997483,
        //          17.040999999997844,
        //          17.03299999999783,
        //          17.529999999998438,
        //          17.540999999998455,
        //          17.60199999999853,
        //         };
        // Ex_34_HotOrColdGame.draw(x, y);
    }
}
