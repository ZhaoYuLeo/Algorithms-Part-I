package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

import chapter1.section3.Ex_17;

/**
 * Reads a sequence of transactions from standard input, sorts them,
 * and prints the result on standard output.
 */
public class Ex_22_SortTransactions {

    public static void main(String[] args) {
        Ex_21_ComparableTransactions[] transactions = Ex_17.readtTransactions(args[0]);
        Ex_11.sort(transactions);
        for (Ex_21_ComparableTransactions t : transactions) {
            StdOut.println(t);
        }
    }
}
