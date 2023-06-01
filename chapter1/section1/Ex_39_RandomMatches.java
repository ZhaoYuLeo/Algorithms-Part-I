package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * Run T trials of experiment for N = 10^3, 10^4, 10^5, and 10^6:
 * generate two arrays of N randomly generated positive six-digit int values,
 * and ﬁnd the number of values that appear in both arrays.
 * Print a table giving the average value of this quantity over the T trials
 * for each value of N.
 */
public class Ex_39_RandomMatches {

    public static int binarySearch(int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static int experiment(int N) {
        // I guess it's decimal digit.
        int L = (int)Math.pow(10, 5);
        int H = (int)Math.pow(10, 6);
        int[] a1 = new int[N];
        int[] a2 = new int[N];
        for (int i = 0; i < N; i += 1) {
            a1[i] = StdRandom.uniform(L, H);
            a2[i] = StdRandom.uniform(L, H);
        }
        int same = 0;
        for (int i = 0; i < N; i += 1) {
            if (binarySearch(a1[i], a2) != -1) {
                same += 1;
            }
        }
        return same;
    }

    public static double trails(int T, int N) {
        double result = 0;
        for (int i = 0; i < T; i += 1) {
            result += experiment(N);
        }
        return result / T;
    }


    public static void main(String[] args) {

        int T = Integer.parseInt(args[0]);
        int[] NS = {(int)Math.pow(10, 3), (int)Math.pow(10, 4), (int)Math.pow(10, 5), (int)Math.pow(10, 6)};

        StdOut.println(T + " Trails:");
        StdOut.printf(" Array Size    Random Matches(avg)\n");
        for (int i = 0; i < NS.length; i += 1) {
            StdOut.printf("  10^%d%15.2f\n", i+3, trails(T, NS[i]));
        }
    }
}
