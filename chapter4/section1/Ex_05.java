package chapter4.section1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Modify Graph to disallow parallel edges and self-loops
 */
public class Ex_05 extends Ex_04 {

    public Ex_05(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        // disallow parallel edges and self-loops
        if (hasEdge(v, w) || v == w) {
            return;
        }
        super.addEdge(v, w);
    }

    public static void main(String[] args) {
        StdOut.println("allow parallel edges and self-loops:");
        Ex_04 g = new Ex_04(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(3, 3);
        g.addEdge(0, 2);
        StdOut.println(g);

        StdOut.println("not allow parallel edges and self-loops:");
        Ex_05 g2 = new Ex_05(4);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(3, 3);
        g2.addEdge(0, 2);
        StdOut.println(g2);
    }
}
