package chapter3.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Count red nodes. Write a program that computes the percentage of red nodes in
 * a given red-black BST. Test your program by running at least 100 trials of the
 * experiment of inserting N random keys into an initially empty tree, for N = 10^4,
 * 10^5, and 10^6, and formulate an hypothesis.
 */
public class Ex_42_CountRedNodes {

    public static int countRed(RedBlackT t) {
        // RedBlackT.Node n = t.root;
        int count = 0;
        Stack<RedBlackT.Node> stack = new Stack<>();
        stack.push(t.root);
        while (!stack.isEmpty()) {
            RedBlackT.Node n = stack.pop();
            if (n == null) {
                continue;
            }
            if (t.isRed(n)) {
                count += 1;
            }
            stack.push(n.right);
            stack.push(n.left);
        }
        return count;
    }

    /**
     * Run experiment of inserting N random keys into an empty tree 100 times.
     */
    public static double trials(int N) {
        int times = 100;    // run 100 trials of the experiment
        int totalN = 0;     // total number of nodes after 100 trials
        int totalRed = 0;   // total number of red nodes after 100 trials
        RedBlackBST.setAnimate(false);
        for (int t = 0; t < times; t += 1) { 
            RedBlackBST<Integer, Integer> rbt = new RedBlackBST<>();
            for (int i = 0; i < N; i += 1) {
                int item = StdRandom.uniform(Integer.MAX_VALUE);
                rbt.put(item, i);
            }
            totalN += rbt.size();
            totalRed += countRed(rbt);
        }
        return totalRed * 1.0 / totalN;
    }

    public static void main(String[] args) {
        // String[] a = {"S", "E", "A", "R", "C", "H", "E", "X"}; // internalPath 12
        // RedBlackBST<String, Integer> st = new RedBlackBST<>();
        // for (int i = 0; i < a.length; i += 1) {
        //     st.put(a[i], i);
        // }
        // StdOut.println("Red nodes for default tree is " + countRed(st));

        StdOut.printf("%8s | %10s\n", "N", "Cound red nodes");
        for (int t = 0; t < 3; t += 1) {
            int n = (int)Math.pow(10, 4 + t);
            double count = trials(n);
            StdOut.printf("%8d | %9.5f\n", n, count);
        }
        //       N | Cound red nodes
        //   10000 |   0.25309
        //  100000 |   0.25393
        // 1000000 |   0.25390
    }
}
