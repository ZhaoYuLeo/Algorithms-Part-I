package chapter1.section5;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import chapter1.section5.UF;

/**
 * Show the contents of the id[] array and the number of times the array
 * is accessed for each input pair when you use weighted quick-union for
 * the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 */
public class Ex_03 extends UF {
    private int[] sz;  // size of component for roots (site indexed)

    public Ex_03(int N) {
        super(N);        
        sz = new int[N];
        for (int i = 0; i < N; i += 1) {
            sz[i] = 1;
        }
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

    public static void main(String[] args) {
        Ex_03 uf = new Ex_03(10);
        // int[] sequence = {9, 0, 3, 4, 5, 8, 7, 2, 2, 1, 5, 7, 0, 3, 4, 2};
        int[] sequence = {0, 1, 2, 3, 4, 5, 6, 7, 0, 2, 4, 6, 4, 0, 3, 8};
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
