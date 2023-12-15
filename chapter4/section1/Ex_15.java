package chapter4.section1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Modify the input stream constructor for Graph to also allow adjacency lists from standard input
 * (in a manner similar to SymbolGraph), as in the example tinyGaadj.txt shown at right. After the
 * number of vertices and edges, each line contains a vertex and its list of adjacent vertices.
 */
public class Ex_15 {
    private final int V;        // number of vertices
    private int E;              // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    public Ex_15(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];      // Create array of lists.
        for (int v = 0; v < V; v += 1) {        // Initialize all lists
            adj[v] = new Bag<Integer>();        //  to empty.
        }
    }

    public Ex_15(In in) {
        this(in.readInt());     // Read V and construct this graph.
        int E = in.readInt();   // Read E.
        in.readLine();
        String sp = " ";        // Assume the split character is <space>
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = Integer.parseInt(a[0]);
            for (int w = 1; w < a.length; w += 1) {
                addEdge(v, Integer.parseInt(a[w]));
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
        adj[v].add(w);      // Add w to v's list.
        adj[w].add(v);      // Add v to w's list.
        E += 1;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder(); 
        str.append(V);
        str.append(" vertices, ");
        str.append(E);
        str.append(" edges\n");
        for (int v = 0; v < V; v += 1) {
            str.append(v);
            str.append(": ");
            for (int w : this.adj(v)) {
                str.append(w);
                str.append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Ex_15 G = new Ex_15(new In(args[0]));
        StdOut.println(G);
    }
}
