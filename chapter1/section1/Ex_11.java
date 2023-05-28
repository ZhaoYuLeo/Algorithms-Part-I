package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_11 {
    public static void printBooleanMatrix(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i += 1) {
            for (int j = 0; j < matrix[i].length; j += 1) {
                StdOut.print(i);
                StdOut.print(j);
                StdOut.print(matrix[i][j] ? '*' : ' ');
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        boolean[][] matrix = {{true, false, false},
                             {true, true, true},
                             {false, true, false},
                             {false, false, false}};
        printBooleanMatrix(matrix);
    }
}
