package chapter4.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * The girth of a graph is the length of its shortest cycle. If a graph is acyclic, then its
 * girth is infinite. Add a method girth() to GraphProperties that returns the girth of the
 * graph. Hint: Run BFS from each vertex. The shortest cycle containing s is a shortest path
 * from s to some vertex v, plus the edge from v back to s.
 */
public class Ex_17 extends Ex_16_GraphProperties {
    // However this is not infinite.
    private int girth = Integer.MAX_VALUE;      // The length of its shortest cycle

    public Ex_17(Graph G) {
        super(G);
        for (int v = 0; v < G.V(); v += 1) {
            // TODO: There are some cycles repeated.
            // Can be reduced from O(V) to O(C), C is the number of components?
            BFPCycle paths = new BFPCycle(G, v);
            if (paths.girth() < girth) {
                girth = paths.girth();
            }
        }
    }

    /**
     * Retrun the girth of the graph G. 
     */
    public int girth() {
        return girth;
    }

    /**
     * Find circle in G from source s
     */
    public static class BFPCycle {
        // However this is not infinite.
        private int girth = Integer.MAX_VALUE;      // The length of its shortest cycle
        private boolean[] marked;   // Is a shortest path to this vertex known?
        private int[] edgeTo;       // last vertex on known path to this vertex
        private int[] distTo;       // distance on known path to this vertex
        private final int s;        // source


        public BFPCycle(Graph G, int s) {
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
                        marked[w] = true;
                        distTo[w] = distTo[v] + 1;
                        edgeTo[w] = v;
                        queue.enqueue(w);
                    } else if (w != edgeTo[v]) {
                        // We meet marked intersection. This must be a cycle and we know the length.
                        // discard the cycle path
                        int cycle = distTo[w] + distTo[v] + 1;
                        if (cycle < this.girth) {
                            this.girth = cycle;
                        }
                    }
                }
            }
        }

        /**
         * Return the length of the shortest cycle containing the given source s
         */
        public int girth() {
            return this.girth;
        }
    }

    public static void main(String[] args) {
        Graph G = new UndirectedGraph(new In(args[0]));
        Ex_17 p = new Ex_17(G);
        StdOut.println(G);
        StdOut.println("Girth of the graph:" + p.girth());
    }
}
