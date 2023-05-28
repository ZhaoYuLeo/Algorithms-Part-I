package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_13 {

    /*
     * Print the transposition (rows and columns changed) of a two-dimensional array with M rows and N columns.
     */
    public static <T> void printTranspositionMatrix(T[][] matrix) {
        // All rows should have the same length otherwise we cannot transposition it. 
        // But we don't check.
        int[] rowsLength = new int[matrix.length];
        int max = 0;
        for (int i = 0; i < matrix.length; i += 1) {
            int rowLength = matrix[i].length;
            rowsLength[i] = rowLength;
            if (rowLength > max) {
                max = rowLength;
            }
        }
        for (int i = 0; i < max; i += 1) {
            for (int j = 0; j < matrix.length; j += 1) {
                if (i < rowsLength[j]) {
                    StdOut.printf("%3s", matrix[j][i]);
                }
            }
            StdOut.println();
        }
    }

    public static <T> void printMatrix(T[][] matrix) {
        for (int i = 0; i < matrix.length; i += 1) {
            for (int j = 0; j < matrix[i].length; j += 1) {
                StdOut.printf("%3s", matrix[i][j]);
            }
            StdOut.println();
        }
    }


    public static void main(String[] args) {
        Integer[][] intMatrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[][] strMatrix = {{"ab", "ac"}, {"ba", "bc", "bd"}, {"ca", "cb", "cd", "ce"}, {"da"}};
        StdOut.println("Integer Array:");
        printMatrix(intMatrix);
        StdOut.println("transposition to:");
        printTranspositionMatrix(intMatrix);
        StdOut.println("String Array:");
        printMatrix(strMatrix);
        StdOut.println("transposition to:");
        printTranspositionMatrix(strMatrix);
    }
}
