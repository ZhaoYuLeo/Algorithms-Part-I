package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import chapter1.section5.UF;
import chapter1.section5.Ex_03;

/**
 * Develop a UF client ErdosRenyi that takes an integer value N from the command line, 
 * generates random pairs of integers between 0 and N-1, calling connected() to determine
 * if they are connected and then union() if not (as in our development client), looping
 * until all sites are connected, and printing the number of connections generated. Package
 * your program as a static method count() that takes N as argument and returns the number
 * of connections and a main() that takes N from the command line, calls count(), and prints
 * the returned value.
 */
public class Ex_17_RandomConnections {
    private static int p;
    private static int q;

    public static int count(int N, UF uf) {
        int connections = 0;

        while (uf.count() != 1) {
            randomPairs(N);
            connections += 1;
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }

        return connections;
    }

    private static void randomPairs(int N) {
        p = StdRandom.uniform(N);
        q = p;
        while (p == q) {
            q = StdRandom.uniform(N);
        }
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        UF uf = new Ex_03(N);
        StdOut.println(count(N, uf));
    }
}
