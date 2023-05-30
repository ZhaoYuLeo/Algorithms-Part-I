package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

/*
 * Creates an N-by-N boolean array a[][]. a[i][j] is true if i and j are
 * relatively prime(have no common factors), and false otherwise.
 */
public class Ex_30_ArrayExercise {
    /*
     * Return true if n and m are relatively prime
     */
    public static boolean coprime(int n, int m) { 
        return gcb(n, m) == 1; 
    }

    public static int gcb(int p, int q) {
        if (q == 0) {
            return p;
        }
        return gcb(q, p % q);
    }
    
    public static boolean[][] array(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Argument must be greater than zero since this would be the size of an array.");
        }
        boolean[][] array = new boolean[n][n];
        for (int i = 0; i < n; i += 1) {
            for (int j = i; j < n; j += 1) {
                array[i][j] = array[j][i] = coprime(i, j);
            }
        }
        return array;
    }

    public static void main(String[] args) {
        StdOut.println(gcb(0, -1)); // -1.
        Ex_11.printBooleanMatrix(array(4));
    }
}
