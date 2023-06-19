package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Almost same with the TwoSumFaster except `h + t == a[i]` instead of `h + t == 0`.
 * quadratic algorithm.
 */
public class Ex_15_ThreeSumFaster {
    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        if (a[0] > 0 || a[N - 1] < 0 || N == 0) {
            return count;
        }
        for (int i = 0; i < N; i += 1) {
            int lo = i;
            int hi = N - 1;
            while(lo < hi) {
                int result = a[lo] + a[hi] + a[i];
                if (result == 0) {
                    // StdOut.println(a[lo] + ", " + a[hi] + ", " + a[i]);
                    // We find one! but not the only one. a[i] has various combinations. of course like 0 has.
                    count += 1;
                    lo += 1;
                    hi -= 1;
                } else if (result < 0) {
                    lo += 1;
                } else {
                    hi -= 1;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {-9, -8, -6, -2, 0, 1, 2, 4, 6, 8, 10};
        StdOut.println(count(a));
    }
}
