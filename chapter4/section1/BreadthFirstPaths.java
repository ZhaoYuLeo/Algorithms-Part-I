package chapter4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Breadth-first search to find paths in a graph.
 */
public class BreadthFirstPaths implements Paths {

    private boolean[] marked;   // Is a shortest path to this vertex known?
    private int[] edgeTo;       // last vertex on known path to this vertex 
    private final int s;        // source

    /**
     * This Graph client uses breadth-first search to find paths in a graph with the fewest number of edges
     * from the source s given in the constructor.
     * @param G
     * @param s
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * Marks all vertices connected to s.
     * @param G
     * @param s
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<>();
        marked[s] = true;
        q.enqueue(s);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    /**
     * Whether a given vertex v is connected to s 
     * @param v
     * @return whether connected
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Get a path from s to v with the property that no other such path from s
     * to v has fewer edges.
     * @param v
     * @return path
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        Graph G = new UndirectedGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Paths search = new BreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v += 1) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
            }
            StdOut.println();
        }
    }
}

