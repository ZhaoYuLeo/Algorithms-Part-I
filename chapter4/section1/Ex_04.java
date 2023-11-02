package chapter4.section1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Add a method hasEdge() to Graph which takes two int arguments v and w and returns true
 * if the graph has an edge v-w, false otherwise.
 */
public class Ex_04 extends UndirectedGraph {

    public Ex_04(int V) {
        super(V);
    }

    /**
     * Returns true of the graph has an edge v-w, false otherwise.(but they may be connected)
     */
    public boolean hasEdge(int v, int w) {
        for (int a : adj(v)) {
            if (a == w) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Ex_04 g = new Ex_04(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 2);
        g.addEdge(1, 3);
        StdOut.println(g);
        StdOut.println("0-1: " + g.hasEdge(0, 1));
        StdOut.println("0-3: " + g.hasEdge(0, 3));
    }
}
