package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Find a local minimum: a pair of indices i and j such that a[i][j] < a[i+1][j],
 * a[i][j] < a[i][j+1], a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1].
 * proportional to N in the worst case.
 */
public class Ex_19_LocalMinmumMatrix {
    public static int[] localMinimumMatrix(double[][] a) {
        // assume a is an N-by-N array of N^2 distinct integers
        int[] pair = new int[2];
        int rLo = 0;
        int rHi = a.length - 1;
        while (rLo <= rHi) {
            int rMid = rLo + (rHi - rLo) / 2;
            int c = smallest(a[rMid]); 

            if (rMid == 0 || rMid == a.length - 1) {
                if (a[rLo][c] < a[rHi][c]) {
                    pair[0] = rLo;
                } else {
                    pair[0] = rHi;
                }
                pair[1] = c;
                break;
            }

            if (a[rMid][c] < a[rMid - 1][c] && a[rMid][c] < a[rMid + 1][c]) {
                pair[0] = rMid;
                pair[1] = c;
                break;

            } else if (a[rMid - 1][c] < a[rMid + 1][c]) {
                rHi = rMid - 1;
            } else {
                rLo = rMid + 1;
            }
        }
        return pair;
    }

    public static int smallest(double[] a) {
        int indice = 0;
        for (int i = 0; i < a.length; i += 1) {
            if (a[i] < a[indice]) {
                indice = i;
            }
        }
        return indice;
    }

    public static void main(String[] args) {
        double[][] as = {{11, 1, 3, 4},
                         {4, 6, 5, 9},
                         {1, 3, 15, 10},
                         {8, 2, 9, 7}};

        double[][] bs = {{1, 0}, {2, 3}};

        for (double[] e : as) {
            StdOut.println(smallest(e));
        }

        int[] i = localMinimumMatrix(as);
        StdOut.println(as[i[0]][i[1]]);

        int[] i2 = localMinimumMatrix(bs);
        StdOut.println(bs[i2[0]][i2[1]]);
    }
}
