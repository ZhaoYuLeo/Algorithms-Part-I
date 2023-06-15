package chapter1.section3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * In the Josephus problem from antiquity, N people are in dire
 * straits and agree to the following strategy to reduce the population. 
 * They arrange themselves in a circle (at positions numbered from 0 to N–1)
 * and proceed around the circle, eliminating every Mth person until only
 * one person is left. Legend has it that Josephus ﬁgured out where to sit to
 * avoid being eliminated.
 */
public class Ex_37_Josephus {
    /**
     * Write a Queue client Josephus that takes N and M from the command line
     * and prints out the order in which people are eliminated (and thus would
     * show Josephus where to sit in the circle).
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int M = Integer.parseInt(args[1]);

        Queue<Integer> c = new Queue<>();
        for (int i = 0; i < N; i += 1) {
            c.enqueue(i);
        }
        while(c.size() > 0) {
            for (int i = 1; i < M; i += 1) {
                c.enqueue(c.dequeue());
            }
            StdOut.print(c.dequeue() + " ");
        }
        StdOut.println();
    }
}
