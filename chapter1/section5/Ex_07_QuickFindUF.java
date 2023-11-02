package chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement quick-find
 */
public class Ex_07_QuickFindUF extends UF {

    public Ex_07_QuickFindUF(int N) {
        super(N);        
    }

    /**
     * Returns the canonical element of the set containing element
     */
    @Override
    public int find(int p) {
        validate(p);
        return id[p];
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
        validate(p);
        validate(q);
        int pRoot = id[p];
        int qRoot = id[q];
        // connected, same component
        if (pRoot == qRoot) {
            return;
        }
        for (int i = 0; i < id.length; i += 1) {
            if (id[i] == pRoot) {
                id[i] = qRoot;
            }
        }
        count -= 1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Ex_07_QuickFindUF uf = new Ex_07_QuickFindUF(n);
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
