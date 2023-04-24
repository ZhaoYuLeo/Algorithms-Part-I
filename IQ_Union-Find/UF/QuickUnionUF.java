/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF implements UF {
    private int[] sites;
    private int count;

    public QuickUnionUF(int n) {
        sites = new int[n];
        count = n;
        for (int i = 0; i < n; i += 1) {
            sites[i] = i;
        }
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        sites[rootP] = rootQ;
        count -= 1;
    }

    public int find(int p) {
        validate(p);
        while (p != sites[p]) {
            p = sites[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    /**
     * Validate if the input number is legal.
     *
     * @param p input number
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    private void validate(int p) {
        int max = sites.length;
        if ((p >= max) || (p < 0)) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + max);
        }
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
