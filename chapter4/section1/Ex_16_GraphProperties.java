package chapter4.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

/**
 * The eccentricity of a vertex v is the the length of the shortest path from that vartex to the
 * furthest vertex from v. The diameter of a graph is the maximum eccentricity of any vertex. The
 * radius of a graph is the smallest eccentricity of any vertex. A center is a vertex whose
 * accentricity is the radius. Implement the API of GraphProperties.
 */
public class Ex_16_GraphProperties {

    private int[] eccentricities;
    private int diameter;
    private int radius;
    private int center;

    /**
     * Constructor (exception if G not connected)
     */
    public Ex_16_GraphProperties(Graph G) {
        eccentricities = new int[G.V()];
        diameter = 0;
        radius = G.V();
        center = 0;

        for (int v = 0; v < G.V(); v += 1) {
            Ex_13 paths = new Ex_13(G, v);
            int eccentricityV = 0;      // furthest in the shortest path
            for (int w = 0; w < G.V(); w += 1) {
                if (w == v) {           // can be deleted
                    continue;
                }
                if (paths.distTo(w) > eccentricityV) {
                    eccentricityV = paths.distTo(w);
                }
            }
            eccentricities[v] = eccentricityV;
            if (eccentricityV > diameter) {
                diameter = eccentricityV;
            }
            if (eccentricityV < radius) {
                radius = eccentricityV;
                center = v;
            }
        }

    }

    /**
     * Eccentricity of v
     * @param v
     * @return the eccentricity of vertex v
     */
    public int eccentricity(int v) {
        return eccentricities[v];
    }

    /**
     * Diameter of G 
     * @return the diameter of graph G
     */
    public int diameter() {
        return diameter;
    }

    /**
     * Radius of G 
     * @return the radius of G
     */
    public int radius() {
        return radius;
    }

    /**
     * A center of G
     * @return the center of graph G
     */
    public int center() {
        return center;
    }

    public static void main(String[] args) {
        Graph G = new UndirectedGraph(new In(args[0]));
        Ex_16_GraphProperties p = new Ex_16_GraphProperties(G);
        StdOut.println(G);
        StdOut.println("Diameter of the graph:" + p.diameter());
        StdOut.println("Radius of the graph:" + p.radius());
        StdOut.println("Center of the graph:" + p.center());
        for (int v = 0; v < G.V(); v += 1) {
            StdOut.println("eccentricity of " + v + " is " + p.eccentricity(v));
        }
    }
}
