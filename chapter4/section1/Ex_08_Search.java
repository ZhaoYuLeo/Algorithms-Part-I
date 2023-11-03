package chapter4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import chapter1.section5.WeightedQuickUnionUF;

/**
 * Develop an implementation for the Search API on page 528 that uses UF, as described in the text
 * Utilize the characteristics of UF (weighted to handle large data).
 */
public class Ex_08_Search {
    private WeightedQuickUnionUF uf;
    private int source;     // a source vertex s

    /**
     * Find vertices connected to a source vertex s
     */
    public Ex_08_Search(Graph G, int s) {
        int V = G.V();
        this.uf = new WeightedQuickUnionUF(V);
        for (int v = 0; v < V; v += 1) {
            for (int w : G.adj(v)) {
                uf.union(v, w);
            }
        }
        this.source = s;
    }

    /**
     * is v connected to s?
     */
    public boolean marked(int v) {
        return uf.connected(v, source);
    }

    /**
     * how many vertices are connected to s?
     */
    public int count() {
        return uf.weight(uf.find(this.source));
    }

    public static void main(String[] args) {
        Graph G = new UndirectedGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Ex_08_Search search = new Ex_08_Search(G, s);

        for (int v = 0; v < G.V(); v += 1) {
            if (search.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if (search.count() != G.V()) {
            StdOut.print("NOT ");
        }
        StdOut.println("connected");
    }
}
