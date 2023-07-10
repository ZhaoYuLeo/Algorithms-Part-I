package chapter1.section4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Formulate and validate a hypothesis describing the number of triples of N random int
 * values that sum to 0. The values are uniformly distributed between -M and M, where
 * M is not small.
 *
 * Reference: https://math.stackexchange.com/questions/671362/sum-of-three-numbers-from-unformly-distributed-set-equals-to-zero
 */
public class Ex_40_3SumForRandomValues {
    public static Integer[] randomValues(int N, int M) {
        if (N > M + M + 1) {
            throw new RuntimeException("Random int values are not enough to form the array");
        }
        Set<Integer> input = new HashSet<>();
        while (input.size() < N) {
            input.add(StdRandom.uniform(-M, M + 1));
        }
        Integer[] result = new Integer[N];
        return input.toArray(result);
    }

    public static int ThreeSum(Integer[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N - 1; i += 1) {
            int lo = i + 1;
            int hi = N - 1;
            while(lo < hi) {
                int sum = a[lo] + a[hi] + a[i];
                if (sum == 0) {
                    count += 1;
                    lo += 1;
                    hi -= 1;
                } else if (sum < 0) {
                    lo += 1;
                } else {
                    hi -= 1;
                }
            }
        }
        return count;
    } 

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);
        StdOut.println("Size: " + N + " distributed between " + -M + " and " + M);
        Integer[] random = randomValues(N, M);
        for (int i : random) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(ThreeSum(random));
    }
}
