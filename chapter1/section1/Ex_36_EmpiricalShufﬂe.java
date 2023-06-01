package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;;

/*
 */
public class Ex_36_EmpiricalShufﬂe {

    // The type of argument in the original code is double[].
    // I change it to int[] because it will be much easy to
    // handle in the shuffleTest(): a[i] is i, is a[a[i]].
    // And shuffle() doesn't care what in a.
    public static void shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // Exchange a[i] with random element in a[i..N-1]
            int r = i + StdRandom.uniform(N-i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    private static void init(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            a[i] = i;
        }
    }

    public static void shuffleTest(int M, int N) {
        int[] a = new int[M];
        int[][] count = new int[M][M];
        for (int t = 0; t < N; t += 1) {
            init(a);
            shuffle(a);
            for (int j : a) {
                count[a[j]][j] += 1;
            }
        }
        StdOut.printf("Result Table (%d size, %d times):\n", M, N);

        for (int i = 0; i < count.length; i += 1) {
            for (int j = 0; j < count[0].length; j += 1) {
                StdOut.printf(" %5d", count[i][j]);
            }
            StdOut.println();
        } 
    }


    public static void main(String[] args) {
        shuffleTest(5, 1000);
    }
}
