package chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * Create a copy constructor for Digraph that takes as input a digraph G and
 * creates and initializes a new copy of the digraph. Any changes a client
 * makes to G should not affect the newly created digraph.
 */
public class Ex_03_Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Ex_03_Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v += 1) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Ex_03_Digraph(Ex_03_Digraph G) {
        this(G.V());
        for (int v = 0; v < V; v += 1) {
            for (int w : G.adj(v)) {
                addEdge(v, w);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E += 1;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Ex_03_Digraph reverse() {
        Ex_03_Digraph R = new Ex_03_Digraph(V);
        for (int v = 0; v < V; v += 1) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v += 1) {
            s += v + ": ";
            for (int w : this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Ex_03_Digraph d1 = new Ex_03_Digraph(5);
        d1.addEdge(0, 1);
        d1.addEdge(0, 2);
        d1.addEdge(0, 3);
        d1.addEdge(0, 4);
        d1.addEdge(1, 2);

        StdOut.println(d1);

        Ex_03_Digraph d2 = new Ex_03_Digraph(5);
    }
}
