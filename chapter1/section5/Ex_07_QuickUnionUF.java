package chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement quick-union
 */
public class Ex_07_QuickUnionUF extends UF {

    public Ex_07_QuickUnionUF(int N) {
        super(N);        
    }

    /**
     * Returns the canonical element of the set containing element
     */
    @Override
    public int find(int p) {
        validate(p);
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    /**
     * Validate the given index p
     */
    private void validate(int p) {
        if ( p < 0 || p >= id.length) {
            throw new IllegalArgumentException("index out of range.");
        }
    }

    /**
     * Merges the set containing element with the set
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[pRoot] = qRoot;
        count -= 1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Ex_07_QuickUnionUF uf = new Ex_07_QuickUnionUF(n);
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
