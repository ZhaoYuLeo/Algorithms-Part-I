import edu.princeton.cs.algs4.StdOut;

/**
 * Add a method find() to the union-find data types so that find(i) returns
 * the largest element in the connected component containing i.
 * Running time of operations, union(), connected(), and find() should take
 * logarithmic time or better.
 */
public class UFMax implements UF {
    private int[] sites;
    private int count;

    public UFMax(int n) {
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
        if (rootP > rootQ) {
            sites[rootQ] = rootP;
        } else {
            sites[rootP] = rootQ;
        }
        count -= 1;
    }

    // create an abstract class if you want to delete these codes
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
        UFMax ufMax = new UFMax(10);
        ufMax.union(1, 2);
        ufMax.union(1, 6);
        ufMax.union(2, 9);
        ufMax.union(3, 4);
        ufMax.union(8, 5);
        ufMax.union(5, 3);
        StdOut.println("The largest in {1, 2, 6, 9} is " + ufMax.find(1));
        StdOut.println("The largest in {3, 4, 5, 8} is " + ufMax.find(4));
    }
}
