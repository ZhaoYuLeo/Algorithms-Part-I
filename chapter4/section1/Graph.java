package chapter4.section1;

/**
 * Fundamental graph operations for undirected graph
 */
public interface Graph {

    /**
     * Number of vertices
     */
     int V();

    /**
     * Number of edges
     */
    int E();

    /**
     * Add edge v-w to this graph
     */
    void addEdge(int v, int w);

    /**
     * Vertices adjacent to w
     */
    Iterable<Integer> adj(int v);

    /**
     * String representation
     */
    String toString();
}
