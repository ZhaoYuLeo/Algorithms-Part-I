package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Using linked lists or a resizing array, develop a weighted quick-union implementation
 * that removes the restriction on needing the number of objects ahead of time. Add a
 * method newSite() to the API, which returns as int identifier.
 */
public class Ex_20_DynamicGrowth extends UF {
    // How fast it is to find p in a linked list?
    private final static int INIT_SIZE = 4;
    private int size;
    private int[] sz;


    public Ex_20_DynamicGrowth() {
        super(INIT_SIZE);
        sz = new int[INIT_SIZE];
        for (int i = 0; i < INIT_SIZE; i += 1) {
            sz[i] = 1;
        }
        size = INIT_SIZE;
    }

    /**
     * add one site
     */
    public int newSite() {
        if (id.length == size) {
            resizeArray(size * 2);
        } 
        id[size] = size;
        sz[size] = 1;

        size += 1;
        count += 1;
        
        // return new site id
        return size - 1;
    }

    public void resizeArray(int N) {
        int[] newId = new int[N];
        int[] newSz = new int[N];
        for (int i = 0; i < id.length; i += 1) {
            newId[i] = id[i];
            newSz[i] = sz[i];
        }
        id = newId;
        sz = newSz;
    }

    public boolean connected(int p, int q) {
        while (p >= size || q >= size) {
            newSite();
        }
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p >= size) {
            newSite();
        }
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        while (p >= size || q >= size) {
            newSite();
        }
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

        count -= 1;
    }

    public static void main(String[] args) {
        Ex_20_DynamicGrowth dg = new Ex_20_DynamicGrowth();
        int[] sequence = {0, 1, 2, 3, 4, 5, 6, 7, 0, 2, 4, 6, 4, 0, 3, 8};
        StdDraw.setXscale(-1, 13);
        StdDraw.setYscale(-2, 25);
        StdDraw.setPenRadius(0.01);
        for (int i = 1; i < sequence.length; i += 2) {
            dg.union(sequence[i - 1], sequence[i]);
            dg.drawTree();
        }
    }
}
