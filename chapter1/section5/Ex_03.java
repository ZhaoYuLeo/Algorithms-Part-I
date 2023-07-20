package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Show the contents of the id[] array and the number of times the array
 * is accessed for each input pair when you use weighted quick-union for
 * the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 */
public class Ex_03 {
    private int[] id;  // access to componet id (site indexed)
    private int[] sz;  // size of component for roots (site indexed)
    private int count; // number of components
    private int times; // number of times the array is accessed for each union
    private int layer = 24; // the layer of current tree

    public Ex_03(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i += 1) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (id[p] != p) {
            this.times += 2;
            p = id[p];
        }
        this.times += 1;
        return p;
    }

    public void union(int p, int q) {
        this.times = 0;
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (sz[p] > sz[q]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        this.times += 5;
        count -= 1;
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
        Ex_03 uf = new Ex_03(10);
        int[] sequence = {9, 0, 3, 4, 5, 8, 7, 2, 2, 1, 5, 7, 0, 3, 4, 2};
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
