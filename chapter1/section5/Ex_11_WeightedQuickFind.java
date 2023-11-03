package chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement weighted quick-find, where you always change the id[] entries of
 * the smaller component to the identifier of the larger component.
 * How does this change affect performance?. Halve the array access of the
 * union operation at most. 
 */
public class Ex_11_WeightedQuickFind extends UF {
    private int[] sz;     // size of component

    public Ex_11_WeightedQuickFind(int N) {
        super(N);        
        sz = new int[N];
        for (int i = 0; i < N; i += 1) {
            sz[i] = 1;
        }
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
        // find the larger component and the smaller one.
        int oldRoot, newRoot; 
        if (sz[pRoot] < sz[qRoot]) {
            oldRoot = pRoot;
            newRoot = qRoot;
        } else {
            oldRoot = qRoot;
            newRoot = pRoot;
        }
        // change the entries of the smaller component to the entries of larger component.
        for (int i = 0; i < id.length; i += 1) {
            if (id[i] == oldRoot) {
                id[i] = newRoot;
            }
        }
        sz[newRoot] += sz[oldRoot];
        count -= 1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        Ex_11_WeightedQuickFind uf = new Ex_11_WeightedQuickFind(n);
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
