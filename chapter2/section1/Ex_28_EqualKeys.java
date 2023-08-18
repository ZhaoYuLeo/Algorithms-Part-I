package chapter2.section1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Formulate and validate hypotheses about the running time of insertion sort and selection
 * sort for arrays that contain just two key values, assuming that the values are equally
 * likely to occur.
 */
public class Ex_28_EqualKeys {

    public static void main(String[] args) {
        Sort alg1 = new InsertionSort();
        Sort alg2 = new SelectionSort();
        int initSize = 128;
        int N = Integer.parseInt(args[0]) + 7;
        int T = Integer.parseInt(args[1]);
        int scale = 2;
        StdOut.println("Insertion Sort:");
        ArrayList<Double> t1 = SortCompare.timeRandomArraysWithCertainValues(alg1, N, T, scale, initSize); // total for insertion sort
        StdOut.println("Selection Sort:");
        ArrayList<Double> t2 = SortCompare.timeRandomArraysWithCertainValues(alg2, N, T, scale, initSize); // total for selection sort
    }
}
