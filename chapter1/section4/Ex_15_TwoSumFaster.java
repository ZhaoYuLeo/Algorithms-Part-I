package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * linear algorithm to count the pairs that sum to zero after the array is sorted
 */
public class Ex_15_TwoSumFaster {
    public static int count(int[] a) {
        Arrays.sort(a);
        // -9 -7 -1 2 0 5 9 10
        // 10 9 5 0 2 -1 -7 -9
        int N = a.length;
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < N && j < N && i + j < N - 1) {
            // must advance one step
            int h = a[i];
            int t = a[N - j - 1];
            if (h * t <= 0) {
                int absH = Math.abs(h);
                int absT = Math.abs(t);
                if (absH == absT) {
                    count += 1;
                    i += 1;
                    j += 1;
                } else if (absH > absT) {
                    i += 1;
                } else {
                    j += 1;
                }
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {-9, -8, -6, -2, 0, 1, 2, 4, 6, 8, 10};
        int[] b = {-9, 0, 3};
        int[] c = {-3, 0, 0, 3};
        int[] d = {-9, -8, -5, 5, 6, 10};
        int[] e = {-9, -2, 1, 2, 8};
        StdOut.println(count(a));
        StdOut.println(count(b));
        StdOut.println(count(c));
        StdOut.println(count(d));
        StdOut.println(count(e));
    }
}
