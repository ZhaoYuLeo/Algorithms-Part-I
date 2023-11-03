package chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Weighted quick-union (without path compression) 
 * N constructor, lgN find, lgN union. Use at most cMlgN array accesses to process M connections
 * among N sites for a small constant c. Empirical studies on huge problems tell use that weighted
 * quick-union typically solves practical problems in constant time per operation. It's frequently
 * the case that a 1-node tree is merged with a larger tree, which puts the node just one link
 * from the root.
 */
public class WeightedQuickUnionUF {
    private int[] id;   // the parent of i
    private int[] sz;   // size of component for roots (site indexed)
    private int count;  // number of components

    /**
     * Initializes an empty union-find data structure with n elements,
     * and each element is in its own set.
     */
    public WeightedQuickUnionUF(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i += 1) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * Returns the number of sets.
     */
    public int count() {
        return count;
    }

    /**
     * Returns the numebr of sets.
     */
    public int find(int p) {
        validate(p);
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    /**
     * Return true of the two elements are in the same set.
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Validate the index p.
     */
    private void validate(int p) {
        int n = id.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Merge the set containing element p with the set containing element q.
     */
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // make smaller root point to larger one
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        count -= 1;
    }

    /**
     * Reads an integer and s sequence of pairs of integers between 0 and (n-1) from
     * standard input, where each integer in the pair represents some elements; If
     * the elements are in different sets, merge the two sets and print the pair to
     * standard output.
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
