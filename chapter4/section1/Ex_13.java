package chapter4.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * Add a distTo() method to the BreadthFirstPaths API and implementation, which
 * returns the number of edges on the shortest path from the source to a given
 * vertex. A distTo() query should run in constant time.
 */
public class Ex_13 implements Paths {
    private boolean[] marked;   // Is a shortest path to this vertex known?
    private int[] edgeTo;       // last vertex on known path to this vertex
    private int[] distTo;       // distance on known path to this vertex
    private final int s;        // source


    /**
     * Find paths in G from source s
     */
    public Ex_13(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        this.s = s;

        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * Is there a path from s to v?
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Path from s to v; null of no such path
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

    /**
     * Returns the number of edges on the shortest path from the source to a
     * given vertex.
     */
    public int distTo(int v) {
        return distTo[v];
    }

    public static void main(String[] args) {
        Graph G = new UndirectedGraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Ex_13 search = new Ex_13(G, s); 
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
            StdOut.println("the dist of this path: " + search.distTo(v));
        }
    }
}
