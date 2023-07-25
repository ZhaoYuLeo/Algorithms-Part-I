package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Modify quick-union (page 224) to include path compression, by adding a loop 
 * to union() that links every site on the paths from p and q to the roots of
 * their trees to the root of the new tree. Give a sequence of input pairs that
 * causes this method to produce a path of length 4.
 * Note: The amortized cost per operation for this algorithm is known to be
 * logarithmic.
 */
public class Ex_12_QuickUnionWithPathCompression {
    private int[] id;   // access to component id (site indexed)
    private int count;  // number of components

    private int times; // number of times the array is accessed for each union
    private int layer = 24; // the layer of current tree

    public Ex_12_QuickUnionWithPathCompression(int N) {
        // Initialize component id array.
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i += 1) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        // Find component name.
        int root = p;
        while (root != id[root]) {
            root = id[root];
            this.times += 2;
        }

        // add another loop to find() that sets the id[] entry corresponding to each node encountered along the way to link directly to the root.
        while (p != id[p]) {
            int temp = id[p];
            id[p] = root;
            p = temp;
            this.times += 3;
        }
        this.times += 2;
        return p;
    } 

    public void union(int p, int q) {
        this.times = 0;

        // Give p and q the same root.
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;

        count -= 1;

        this.times += 1;
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
        Ex_12_QuickUnionWithPathCompression uf = new Ex_12_QuickUnionWithPathCompression(N);
        int[] sequence = {0, 1, 2, 3, 4, 5, 6, 7, 0, 2, 4, 6, 0, 4, 7, 8};
        // Since we only modified the entries while examining, we will produce the path 0-3-7-8 of length 4 given this sequence of input pairs.
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
