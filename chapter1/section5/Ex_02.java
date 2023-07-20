package chapter1.section5;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Show the contents of the id[] array and the number of times the array
 * is accessed for each input pair when you use quick-find for the sequence
 * 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 * In addition, draw the forest of trees represented by the id[] array
 * after each input pair is processed.
 */
public class Ex_02 {
    private int[] id;  // access to component id (site indexed)
    private int count; // number of components
    private int times; // number of times the array is accessed for each union
    private int layer = 24; // the layer of current tree

    public Ex_02(int N) {
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
        while (id[p] != p) {
            p = id[p];
            this.times += 2;
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
        id[p] = qRoot;
        this.times += 2;
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

//    public void draw() {
//        //TODO: Can I build a tree structure using this datatype?
//        // I think you are on wrong way. Your map cannot contain itself by this way.
//        // But this data structure must exist. I will wait util something similar occurs.
//        Map<Integer, ArrayList<Integer>> tree = new HashMap<>();
//        // find leafs
//        int[] copy = new int[id.length];
//        int[] compare = new int[id.length];
//        for (int i = 0; i < id.length; i += 1) {
//            copy[i] = id[i];
//            compare[i] = i;
//            if (id[i] == i) {
//                // get root
//                tree.put(i, null);
//            }
//        }
//        Arrays.sort(copy);
//        // 0 1 1 2 2 4 4 4 6 8
//        // 0 1 2 3 4 5 6 7 8 9
//        // 4 1 1 4 1 2 6 2 8 0 
//        int prev = copy[0];
//        int i = 0;
//        int j = 0;
//        while (j < id.length) {
//            if (i == id.length) {
//                tree.put(compare[j], null);
//                j += 1;
//                continue;
//            }
//            if (copy[i] == compare[j]) {
//                // move forward 
//                prev = copy[i];
//                i += 1;
//                j += 1;
//            } else {
//                if (prev == copy[i]) {
//                    i += 1;
//                } else {
//                    // found leaf
//                    tree.put(compare[j], null);
//                    j += 1;
//                }
//            } 
//        }
//        // build tree from leafs
//        // 0 1 2 3 4 5 6 7 8 9 | 9
//        // 4 1 1 4 1 2 6 2 8 0 | 3 5 7 9
//        Map<Integer, ArrayList<Integer>> growTree = new HashMap<>();
//        for (Integer node: tree.keySet()) {
//            int parent = id[node];
//            ArrayList<Integer> branches = growTree.get(parent);
//            if (branches != null) {
//                branches.add(node);
//            } else {
//                branches = new ArrayList<Integer>();
//                if (parent != node) {
//                    branches.add(node);
//                }
//            }
//            growTree.put(parent, branches);
//        }
//        tree = growTree;
//
//        for (Integer node: growTree.keySet()) {
//            StdOut.print(node);
//            for (Integer branche : growTree.get(node)) {
//                StdOut.print("-" + branche);
//            }
//            StdOut.print(" ");
//        }
//        StdOut.println();
//        // draw trees in different layer.
//        for (int i = 0; i < id.length; i += 1) {
//            int parent = id[i];
//            ArrayList<Integer> branches = tree.get(parent);
//            if (branches == null ) {
//                branches = new ArrayList<Integer>();
//            }
//            if (parent != i) {
//                branches.add(i);
//            }
//            tree.put(parent, branches); 
//        }
//        int high;
//        int highest = 0;
//        for (Integer parent : tree.keySet()) {
//             StdOut.println(parent);
//             StdDraw.point(parent, layer);
//             ArrayList<Integer> branches = tree.get(parent);
//             high = 0;
//             for (Integer b : branches) {
//                 high += 1;
//                 StdDraw.point(parent, layer - high);
//             }
//             if (high > highest) {
//                 highest = high;
//             }
//             StdOut.println();
//        }
//
//        layer -= (highest + 2);
//    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < id.length; i += 1) {
            s.append(id[i]);
            s.append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Ex_02 uf = new Ex_02(10);
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
