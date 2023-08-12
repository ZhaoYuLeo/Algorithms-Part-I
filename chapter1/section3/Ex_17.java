package chapter1.section3;

import chapter2.section1.Ex_21_ComparableTransactions;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a static method readtTransactions() for Transactions that reads
 * Transactions string from standard input in the format specified in the
 * table on page 119 and returns an array containing them.
 */
public class Ex_17 {
    public static Ex_21_ComparableTransactions[] readtTransactions(String name) {
        In in = new In(name);
        Queue<String> q = new Queue<>();
        while (!in.isEmpty())
            q.enqueue(in.readLine());
        int N = q.size();
        Ex_21_ComparableTransactions[] t = new Ex_21_ComparableTransactions[N];
        for (int i = 0; i < N; i += 1) {
            t[i] = new Ex_21_ComparableTransactions(q.dequeue());
        }
        return t;
    }

    public static void main(String[] args) {
        Ex_21_ComparableTransactions[] transactions = readtTransactions(args[0]);
        for (Ex_21_ComparableTransactions t : transactions) {
            StdOut.println(t);
        }
    }

}
