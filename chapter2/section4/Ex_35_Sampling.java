package chapter2.section4;

import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Sampling from a discrete probability distribution. Write a class Sample with a constructer that takes
 * an array p[] of Double values as argument and supports the following two operations: random() - return
 * an index i with probability p[i] / T (where T is the sum of the numbers in p[]) - and change(i, v) -
 * change the value of p[i] to v. Hint: Use a complete binary tree where each node has implied weight p[i].
 * Store in each node the cumulative weight of all the nodes in its subtree. To generate an random index,
 * pick a random number between 0 and T and use the cumulative weights to determine which branch of the
 * subtree to explore. When updating p[i], change all of do for heaps.
 *
 * For example:
 * Random value in [0...1) like 0.3, 0.5, 0.8:
 *                     1(0.2, 1)
 *              /                     \
 *         2(0.1, 0.4)                3(0.1, 0.4)
 *         /         \                /         \ 
 *   4(0.2, 0.2)  5(0.1, 0.1)   6(0.1, 0.1)  7(0.2, 0.2)
 *
 *  p: [0.2, 0.1, 0.1, 0.2, 0.1, 0.1, 0.2]
 *  [0, 0.2, 0.3, 0.4, 0.6, 0.7, 0.8, 1.0]
 */
public class Ex_35_Sampling {
    private Node[] tree;// A complete binary tree for logN search
    private int N;      // The number of elements
    private Double T;   // The sum of the numbers in p[]

    private class Node {
        Double weight;
        Double cumulativeWeight;

        public Node(Double w) {
            this.weight = w;
            this.cumulativeWeight = w;
        }

        public void cumulate(Node n, Node m) {
            this.cumulativeWeight = this.weight + n.cumulativeWeight + m.cumulativeWeight;
        }

        public void cumulate(Node n) {
            this.cumulativeWeight = this.weight + n.cumulativeWeight;
        }

        public void change(Double v) {
            this.weight = v;
        }
        @Override
        public String toString() {
            return "w: " + this.weight + " c: " + this.cumulativeWeight;
        }
    }

    public Ex_35_Sampling(Double[] p) {
        this.N = p.length;
        tree = new Node[N + 1];
        for (int i = 0; i < N; i += 1) {
            tree[i + 1] = new Node(p[i]);
        }
        arrage();
        if (p[1] != null) {
            this.T = tree[1].cumulativeWeight;
        }
    }

    private void arrage() {
        for (int i = N / 2; i > 0; i -= 1) {
            if (2 * i < N) { // two child
                tree[i].cumulate(tree[2 * i], tree[2 * i + 1]);
            } else {
                tree[i].cumulate(tree[2 * i]);
            }
        }
    }

    /**
     * Return an index i with probability p[i] / T
     * T is the sum of the numbers in p[]
     */
    public int random() {
        Double r = StdRandom.random() * T;
        int k = 1;
        while (k <= N) {
            int j = 2 * k; // left child
            if (j <= N) {
                if (r.compareTo(tree[j].cumulativeWeight) < 0) {
                    k = j;
                    continue;
                }
                r -= tree[j].cumulativeWeight;
            }
            if (r.compareTo(tree[k].weight) < 0) {
                return k - 1;
            }
            r -= tree[k].weight;
            k = j + 1; //right child
        }
        return -1;
    }

    /**
     * Change the value of p[i] to v
     */
    public void change(int i, Double v) {
        tree[i + 1].change(v);
        arrage();
    }

    public static void main(String[] args) {
        // assume all elements in p sum up to 1
        Double[] p = {0.2, 0.1, 0.1, 0.2, 0.1, 0.1, 0.2};
        Ex_35_Sampling sample = new Ex_35_Sampling(p);
        int SCALE = 10000;
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < SCALE; i += 1) {
            int index = sample.random();
            result.put(index, result.getOrDefault(index, 0) + 1);
        }
        for (int i = 0; i < p.length; i += 1) {
            StdOut.println(i + ", " + p[i] * 100.0 / 1 + "%"); 
        }
        StdOut.println();
        for(Map.Entry<Integer, Integer> entry : result.entrySet()) {
            StdOut.println(entry.getKey() + ", " + entry.getValue() * 100.0 / SCALE + "%");
        }
    }
}
