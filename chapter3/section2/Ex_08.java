package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a static method optCompares() that takes an integer argument N and computes
 * the number of compares required by a random search hit in an optimal (perfectly
 * balance) BST, where all the null links are on the same level if the number of links
 * is a power of 2 or on one of two levels otherwise.
 */
public class Ex_08 {

    /**
     * The number of compares required by a random search hit in an optimal BST
     *      o
     *     / \
     *    o   o
     *   / \
     *  o   o
     */
    public static int optCompares(int N) {
        int height = (int) (Math.log(N) / Math.log(2));
        // 2^0 * 1 + 2^1 * 2 + 2^2 * 3 + ... + 2^(h - 1) * (h) = n
        //           2^1 * 1 + 2^2 * 2 + 2^3 * 3 + ... + 2^(h) * (h) = 2n
        // 2^0 * 1 + 2^1 * 1 + 2^2 * 1 + ... + 2^(h - 1) * 1 - 2^(h) * (h) = -n
        // 1 + 2^h * (h - 1) = n
        // + (N - 2^h + 1) * (h + 1) = (N + 1) * (h + 1) - 2^(h + 1) + 1
        return ((N + 1) * (height + 1) - (int) Math.pow(2, height + 1) + 1) / N;
    }

    public static int optCompares2(int N) {
        return optCompares2(N, 1, 0, 1) / N;
    }

    public static int optCompares2(int N, int layer, int depth, int compares) {
        int newLayer = 2 * layer;
        int newDepth = depth + 1;
        int total = newLayer - 1; // 2^0 + 2^1 + ... + 2^k = 2^(k + 1) - 1
        if (total >= N) {
            return compares;
        }
        if (total + newLayer > N) {
            return compares + (N - total) * (newDepth+ 1);
        }
        return optCompares2(N, newLayer, newDepth, compares + newLayer * (newDepth + 1));
    }

    public static void main(String[] args) {
        for (int N = 2; N < 200; N += 1 ) {
            StdOut.println(N + " " + optCompares(N));
            if (optCompares(N) != optCompares2(N)) {
                StdOut.println(N + " not equal");
            }
        }

    }
}
