package chapter1.section4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex_02_ThreeSum {

    /**
     * Counts the number of triples in a file of N integers
     * that sum to 0;
     */
    public static int count(int[] a) {
        // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    sum = a[i] + a[j];
                    sum += a[k];
                    if (sum == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}
