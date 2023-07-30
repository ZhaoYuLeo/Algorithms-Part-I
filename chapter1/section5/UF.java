package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 */
public abstract class UF {
    protected int[] id;  // access to component id (site indexed)
    protected int count; // number of components

    public int times; // number of times the array is accessed for each union
    protected int layer = 24; // the layer of current tree

    public UF(int N) {
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

    public abstract int find(int p);

    public abstract void union(int p, int q);

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
}
