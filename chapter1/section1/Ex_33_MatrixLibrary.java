package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_33_MatrixLibrary {

    /*
     * vector dot product
     */
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("The length of two vectors should be same if you wantt to do dot product.");
        }
        double result = 0;
        for (int i = 0; i < x.length; i += 1) {
            result += x[i] * y[i];
        }
        return result;
    }

    /*
     * Verify the given array is a matrix:
     * 1. element exists even if there's only one
     * 2. rectangle
     */
    public static void verifyMatrix(double[][] a) {
        if (a == null || a[0] == null) {
            throw new IllegalArgumentException("Please provide a matrix.");
        }
        for (int i = 1; i < a.length; i += 1) {
            if (a[i] == null ||  a[i].length != a[i - 1].length) {
                throw new IllegalArgumentException("Please provide a matrix.");
            }
        }
    }

    public static int rows(double[][] a) {
        // Assume a is a matrix
        return a.length;
    }

    public static int cols(double[][] a) {
        // Assume a is a matrix
        return a[0].length;
    }

    /*
     * matrix-matrix product
     */
    public static double[][] mult(double[][] a, double [][] b) {
        verifyMatrix(a);
        verifyMatrix(b);

        if (cols(a) != rows(b)) {
            throw new IllegalArgumentException("The number of columns of the first matrix is not equal to the number of rows in the second matrix. ");
        }
        int rows = rows(a);
        int cols = cols(b);

        int counts = cols(a);

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows ; i += 1) {
            for (int j = 0; j < cols; j += 1) {
                for (int k = 0; k < counts; k += 1) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    /*
     * transpose 
     */
    public static double[][] transpose(double[][] a) {
        verifyMatrix(a);
        int rows = rows(a);
        int cols = cols(a);

        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i += 1) {
            for (int j = 0; j < cols; j += 1) {
                result[i][j] = a[j][i];
            }
        }
        return result;
    }
    /*
     * matrix-vector product.
     */
    public static double[] mult(double[][] a, double [] x) {
        verifyMatrix(a);
        int rowsA = rows(a);
        int colsA = cols(a);
        int rowsX = x.length;

        if (colsA != rowsX) {
            throw new IllegalArgumentException("The number of colums of the first matrix is not equal to the number of elements in the second vector.");
        }

        double[] result = new double[rowsA];
        for (int i = 0; i < rowsA; i += 1) {
            for (int j = 0; j < colsA; j += 1) {
                result[i] += a[i][j] * x[j];
            }
        }
        return result;

    }
    /*
     * vector-matrix product.
     */
    public static double[] mult(double[] y, double[][] a) {
        verifyMatrix(a);
        int colsY = y.length;
        int rowsA = rows(a);
        int colsA = cols(a);

        if (colsY != rowsA) {
            throw new IllegalArgumentException("The number of elements in the first vector is not equal to the number of rows in the second matrix.");
        }

        double[] result = new double[colsA];
        for (int i = 0; i < colsA; i += 1) {
            for (int j = 0; j < rowsA; j += 1) {
                result[i] += y[j] * a[j][i];
            }
        }
        return result;
    }

    public static void display(double[][] a) {
            verifyMatrix(a);
            int rows = rows(a);
            int cols = cols(a);

            for (int i = 0; i < rows; i += 1) {
                for (int j = 0; j < cols; j += 1) {
                    StdOut.printf(" %5.0f", a[i][j]);
                }
                StdOut.println();
            }
    }

    public static void display(double[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.printf(" %5.0f", a[i]);
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        StdOut.println("v1");
        double[] v1 = {0, 1, 2};
        display(v1);
        StdOut.println("v2");
        double[] v2 = {1, 1, 1};
        display(v2);
        StdOut.println("m1");
        double[][] m1 = {{1, 0, 0},
                         {0, 1, 0},
                         {0, 0, 1}};
        display(m1);
        StdOut.println("m2");
        double[][] m2 = {{1, 1, 1},
                         {1, 2, 4},
                         {1, 3, 9}};
        display(m2);
        StdOut.println("v1 · v2");
        StdOut.println(dot(v1, v2) + "");
        StdOut.println("m1 * m2");
        display(mult(m1, m2));
        StdOut.println("transpose m2");
        display(transpose(m2));
        StdOut.println("v2 * m2");
        display(mult(v2, m2));
        StdOut.println("m2 * v1");
        display(mult(m2, v1));
    }
}
