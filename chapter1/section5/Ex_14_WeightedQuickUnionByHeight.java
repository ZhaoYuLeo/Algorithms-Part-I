package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Develop a UF implementation that uses the same basic strategy as weighted
 * quick-union but keeps track of tree height and always links the shorter tree
 * to the taller one.
 * Prove a logarithmic upper bound on the height of the trees for N sites with
 * your algorithm.
 */
public class Ex_14_WeightedQuickUnionByHeight {
    private int[] id;   // access to component id (site indexed)

    // Why you don't record the height of each node?
    // This means I need to update the heights of all nodes in one of the trees when 'union'. The number of array accesses would be proportional to the size of the tree.
    // How to implement weighted quick-union by height with path compression?
    // The loop in find would change the height of the tree. Determining the height of the tree will be difficult.
    private int[] ht;   // height of component for roots (site indexed)
    private int count;  // number of components

    private int times; // number of times the array is accessed for each union
    private int layer = 24; // the layer of current tree

    public Ex_14_WeightedQuickUnionByHeight(int N) {
        count = N;
        id = new int[N];
        ht = new int[N];
        for (int i = 0; i < N; i += 1) {
            id[i] = i;
            ht[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p!= id[p]) {
            p = id[p];
            this.times += 2;
        }

        this.times += 1;
        return p;
    }

    public void union(int p, int q) {
        this.times = 0;

        // Give p and q the same root.
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (ht[pRoot] < ht[qRoot]) {
            id[pRoot] = qRoot;
            ht[qRoot] = Math.max(ht[pRoot] + 1, ht[qRoot]);
        } else {
            id[qRoot] = pRoot;
            ht[pRoot] = Math.max(ht[qRoot] + 1, ht[pRoot]);
        }

        count -= 1;

        this.times += 6;
        StdOut.println(this);
        StdOut.println("\nTimes the array is accessed: " + times + "\n");
    }

    public void drawTree() {
        ArrayList<Integer> root = new ArrayList<>();
        // Stored each layer of the tree.
        Map<Integer, ArrayList<Integer>> tree = new HashMap<>();
        for (int i = 0; i < id.length; i += 1) {
            int parent = id[i];
            ArrayList<Integer> bs = tree.get(parent);
            if (bs == null) {
                bs = new ArrayList<>();
            }
            if (parent == i) {
                root.add(i);
            } else {
                bs.add(i);
            }
            tree.put(parent, bs);
        }
        for (Integer r : root) {
            StdOut.print(r);
            StdDraw.text(r * 1.5, layer, r.toString());
            draw(r, tree, r * 1.5, 1);
        }
        StdOut.println();
        layer -= 3;
    }

    public void draw(int r, Map<Integer, ArrayList<Integer>> tree, double width, int deepth) {
        ArrayList<Integer> nodes = tree.get(r);
        if (nodes == null) return;
        double num = (nodes.size() - 1) / 2.0;
        double inter = 0.8;
        double pos = width - inter * num;
        int i = 0;
        for (Integer n : nodes) {
            StdOut.print("-" + n);
            StdDraw.text(pos + i * inter, layer - deepth, n.toString());
            draw(n, tree, pos + i * inter, deepth + 1);
            i += 1;
        }
        StdOut.print(" ");
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < id.length; i += 1) {
            s.append(id[i]);
            s.append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int N = 10;
        Ex_14_WeightedQuickUnionByHeight uf = new Ex_14_WeightedQuickUnionByHeight(N);
        // input pairs: 0-1 2-3 4-5 6-7 0-2 4-6 4-0 will create path 3-2-5-4 of length 4
        // int[] sequence = {0, 1, 2, 3, 4, 5, 6, 7, 0, 2, 4, 6, 4, 0, 3, 8};

        // 3-2-0-4 in weighted quick-union, 3-2-0 in weighted quick union by height.
        int[] sequence = {0, 1, 2, 3, 0, 2, 4, 5, 6, 5, 7, 6, 4, 8, 3, 8};

        StdDraw.setXscale(-1, 13);
        StdDraw.setYscale(-2, 25);
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i < sequence.length; i += 2) {
            StdOut.println("Union " + sequence[i - 1] + " and " + sequence[i]);
            uf.union(sequence[i - 1], sequence[i]);
            uf.drawTree();
        }
    }
}
