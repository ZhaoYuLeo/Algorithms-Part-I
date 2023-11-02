package chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Adjacency-lists data structure, where we keep track of all the vertices adjacent to each vertex on a linked list
 * that is associated with that vertex.
 */
public class UndirectedGraph implements Graph {
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    /**
     * This implementation maintains a vertex-indexed array of lists of integers.
     * Every edge appears twice.
     */
    public UndirectedGraph(int V) {
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
    public UndirectedGraph(In in) {
        this(in.readInt());         // Read V and construct this graph.
        int E = in.readInt();       // Read E.
        for (int i = 0; i < E; i += 1) {
            // Add an edge
            int v = in.readInt();   // Read a vertex,
            int w = in.readInt();       // read another vertex,
            addEdge(v, w);              // and add edge connecting them.
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
        UndirectedGraph g = new UndirectedGraph(new In(args[0]));
        StdOut.println(g);
    }
}
