package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Prints out all integers of the form a^3 + b^3 where a and b are integers between 0 and N in sorted
 * order, without using excessive space. That is, instead of computing an array of the N^2 sums and
 * sorting them, build a minimum-oriented priority queue, initially containing (0^3, 0, 0), (1^3, 1, 0),
 * (2^3, 2, 0), ... ,(N^3, N, 0). Then, while the priority queue is nonempty, remove the smallest
 * item(i^3 + j^3, i, j), print it, and then, if j < N, insert the item (i^3 + (j + 1)^3, i, j + 1).
 * Use this program to find all distinct mintegers a, b, c, and d between 0 and 10^6 such that
 * a^3 + b^3 = c^3 + d^3.
 */
public class Ex_25_CubeSum implements Comparable<Ex_25_CubeSum>{
    private final int sum;
    private final int i;
    private final int j;

    public Ex_25_CubeSum(int i, int j) {
        this.sum = i * i * i + j * j * j;
        this.i = i;
        this.j = j;
    }

    public int compareTo(Ex_25_CubeSum that) {
        if (this.sum < that.sum) {
            return -1;
        }
        if (this.sum > that.sum) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        return sum + " = " + i + "^3" + " + " + j + "^3";
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        // While we have the same i, it's very easy to compare sizes between i^3 + a^3 and i^3 + b^3.
        // That's why we should always keep the minimum item with different i in the pq.
        // These are the ones we need to compare.

        HeapMinPQ<Ex_25_CubeSum> pq = new HeapMinPQ<Ex_25_CubeSum>();
        for (int i = 0; i <= N; i += 1) {
            pq.insert(new Ex_25_CubeSum(i, 0));
        }

        Ex_25_CubeSum prev = null;
        while (!pq.isEmpty()) {
            Ex_25_CubeSum cs = pq.delMin();
            StdOut.println(cs);
            if (cs.j < N) {
                pq.insert(new Ex_25_CubeSum(cs.i, cs.j + 1));
            }

            // If we want to find all distinct mintegers a, b, c, and d such that a^3 + b^3 = c^3 + d^3,
            // We just need to print out the adjacent equal cubesum since it's ordered.
            if (prev == null) {
                prev = cs;
            } else if (cs.compareTo(prev) != 0) {
                prev = cs;
            } else {
                StdOut.println(prev.i + "^3" + " + " + prev.j + "^3 = " + cs.i + "^3" + " + " + cs.j + "^3");
            }
        }
    }

}

