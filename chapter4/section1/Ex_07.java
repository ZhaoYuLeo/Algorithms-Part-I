package chapter4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a test client for Graph that reads a graph from the input stream
 * named as command-line argument and then prints it, relying on toString().
 */
public class Ex_07 {
    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph(new In(args[0]));
        StdOut.println(g);
    }
}
