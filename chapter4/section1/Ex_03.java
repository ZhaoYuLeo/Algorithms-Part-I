package chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Create a copy constructor for Graph that takes as input a graph G and creates and initializes a new
 * copy of the graph. Any changes a client makes to G should not affect the newly created graph.
 */
public class Ex_03 implements Graph {
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    /**
     * This implementation maintains a vertex-indexed array of lists of integers.
     * Every edge appears twice.
     */
    public Ex_03(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];        // Create array of lists.
        for (int v = 0; v < V; v += 1) {          // Initialize all lists
            adj[v] = new Bag<Integer>();          //   to empty.
        }
    }

    /**
     * Reads a graph from an input stream, int the format V followed by E followed by a list of pairs
     * of int values between 0 and V - 1.
     */
    public Ex_03(In in) {
        this(in.readInt());         // Read V and construct this graph.
        int E = in.readInt();       // Read E.
        for (int i = 0; i < E; i += 1) {
            // Add an edge
            int v = in.readInt();   // Read a vertex,
            int w = in.readInt();       // read another vertex,
            addEdge(v, w);              // and add edge connecting them.
        }
    }

    /**
     * Initializes a new copy of given graph
     */
    public Ex_03(Ex_03 G) {
        this.V = G.V;
        this.E = G.E;
        // initializes the adj array
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v += 1) {
            adj[v] = new Bag<Integer>();
        }

        for (int v = 0; v < V; v += 1) {
            // keep the original order O → o → o → o
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);          // Add w to v's list.
        adj[w].add(v);          // Add v to w's list.
        E += 1;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v += 1) {
            s.append(v + ": ");
            for (int w : this.adj(v)) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Ex_03 g = new Ex_03(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        Ex_03 g2 = new Ex_03(g);
        g.addEdge(0, 3);
        StdOut.println("Print out g: ");
        StdOut.println(g);
        StdOut.println("Print out g2: ");
        StdOut.println(g2);
    }
}
