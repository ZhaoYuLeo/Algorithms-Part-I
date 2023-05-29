package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Euclid’s algorithm, the greatest common divisor of p and q.
 */
public class Ex_24 {
    public static int euclid(int p, int q) {
        return gcd(p, q, 0);
    }

    private static int gcd(int p, int q, int depth) {
        for (int i = 0; i < depth; i += 1) {
            StdOut.print("  ");
        }
        StdOut.printf("p: %d, q: %d\n", p, q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r, depth + 1);
    }

    public static void main(String[] args) {
        StdOut.println("Please enter the p and q in the following space. We would print out the two arguments for each call on Euclid's algorithm. 105 24 and 1111111 1234567 are recommended.");
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            euclid(p, q);
        }
    }
}
