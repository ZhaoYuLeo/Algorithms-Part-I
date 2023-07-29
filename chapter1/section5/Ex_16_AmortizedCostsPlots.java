package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Instrument your implementations from Exercise 1.5.7 (QuickUnionUF and QuickFindUF that implement
 * quick-union and quick-find, respectively.)
 * to make amortized costs plots like those in the text.
 */
public class Ex_16_AmortizedCostsPlots {
    private abstract static class UF {
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

    private static class QuickFindUF extends UF { 
        public QuickFindUF(int N) {
            super(N);
        }

        public int find(int p) {
            return id[p];
        }

        public void union(int p, int q) {
            this.times = 0;

            // Put p and q into the same component.
            int pID = find(p);
            int qID = find(q);

            this.times += 2;

            // Nothing to do if p and q are already in the same component
            if (pID == qID) return;
            // Rename p's component to q's name.
            for (int i = 0; i < id.length; i += 1) {
                this.times += 1;
                if (id[i] == pID) {
                    this.times += 1;
                    id[i] = qID;
                }
            }
            count -= 1;

            // StdOut.println(this);
            // StdOut.println("\nTimes the array is accessed: " + times + "\n");
        }
    }

    private static class QuickUnionUF extends UF { 
        public QuickUnionUF(int N) {
            super(N);
        }

        public int find(int p) {
            while (p != id[p]) {
                this.times += 2;

                p = id[p];
            }

            this.times += 1;
            return p;
        }

        public void union(int p, int q) {
            this.times = 0;

            int pID = find(p);
            int qID = find(q);

            if (pID == qID) return;

            id[pID] = qID; 
            count -= 1;

            this.times += 1;

            // StdOut.println(this);
            // StdOut.println("\nTimes the array is accessed: " + times + "\n");
        }
    }

    public static void main(String[] args) {
        int type = 0;
        // Solve dynamic connectivity problem on StdIn.
        int N = StdIn.readInt();                // Read number of sites.

        UF uf = null;

        if (type == 1) {
            uf = new QuickFindUF(N);    // Initialize N components.
            StdDraw.setXscale(0, 750);
            StdDraw.setYscale(-1, 1000);
        } else {
            uf = new QuickUnionUF(N);
            StdDraw.setXscale(0, 900);
            StdDraw.setYscale(-1, 500);
        }
        StdDraw.setPenRadius(0.005);

        int count = 0;
        int total = 0;
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();            // Read pair to connect.
            // if (qf.connected(p, q)) continue;   // Ignore if connected.
            uf.union(p, q);                     // Combine components

            count += 1;
            total += uf.times;
            if (count == 1) {
                StdOut.println(total + " ");
            }
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(count, uf.times);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(count, total/count);
        }

        StdOut.println(total + " " + count + " " + N);

//        int[] sequence = {0, 1, 2, 3, 0, 2, 4, 5, 6, 5, 7, 6, 4, 8, 3, 8};
//
//        StdDraw.setXscale(-1, 13);
//        StdDraw.setYscale(-2, 25);
//        StdDraw.setPenRadius(0.01);
//
//        for (int i = 1; i < sequence.length; i += 2) {
//            StdOut.println("Union " + sequence[i - 1] + " and " + sequence[i]);
//            qf.union(sequence[i - 1], sequence[i]);
//            qf.drawTree();
//        }

    }

}
