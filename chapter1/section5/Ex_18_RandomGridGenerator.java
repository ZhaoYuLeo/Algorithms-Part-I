package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import chapter1.section3.Ex_34_RandomBag;

/**
 * Takes an int value N from the command line, generates all the connections in an N-by-N grid,
 * puts them in random order, randomly orients them (so that p q and q p are equally likely to
 * occur), and prints the result to standard output. To randomly order the connections, use a
 * RandomBag (see EXERCISE 1.3.34 on page 167). To encapsulate p and q in a single object, use
 * the Connection nested class shown below. Package your program as two static methods: generate(),
 * which takes N as argument and returns an array of connections, and main(), which takes N from
 * the command line, calls generate(), and iterates through the returned array to print the connections.
 */
public class Ex_18_RandomGridGenerator {

    private static class Connection {
        int p;
        int q;

        public Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }

    public static Connection[] generate(int N) {
        Ex_34_RandomBag<Connection> rb = new Ex_34_RandomBag<>();
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (i == j) {
                    continue;
                }
                Connection c = new Connection(i, j);
                rb.add(c);
            }
        }

        Connection[] cs = new Connection[rb.size()];
        int index = 0;
        for (Connection c : rb) {
            cs[index] = c;
            index += 1;
        }
        return cs;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Connection[] cs = generate(N);
        for (Connection c : cs) {
            StdOut.println(c.p + " - " + c.q);
        }
    }
}
