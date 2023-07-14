package chapter1.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Show the contents of the id[] array and the number of times the array is
 * accessed for each input pair when you use quick-find for the sequence
 * 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 */
public class Ex_01 {
    private int[] id;  // access to component id (site indexed)
    private int count; // number of components

    public Ex_01(int N) {
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

    public int find(int p) {
        return id[p];
    }

    //  public void union(int p, int q) {
    //      // Put p and q into the same component.
    //      int pID = find(p);
    //      int qID = find(q);
    //      // Nothing to do if p and q are already in the same component
    //      if (pID == qID) return;
    //      // Rename p's component to q's name.
    //      for (int i = 0; i < id.length; i += 1) {
    //          if (id[i] == pID) {
    //              id[i] = qID;
    //          }
    //      }
    //      count -= 1;
    //  }

    public void union(int p, int q) {
        int times = 2;
        int newRoot = id[q];
        int oldRoot = id[p];
        if (newRoot == oldRoot) {
            return;
        }
        for (int i = 0; i < id.length; i += 1) {
            times += 1;
            if (id[i] == oldRoot) {
                times += 1;
                id[i] = newRoot;
            }
            StdOut.print(id[i] + " ");
        }
        count -= 1;
        StdOut.println("\nTimes the array is accessed: " + times + "\n");
    }

    public static void main(String[] args) {
        int N = 10;
        Ex_01 uf = new Ex_01(N);
        int[] sequence = {9, 0, 3, 4, 5, 8, 7, 2, 2, 1, 5, 7, 0, 3, 4, 2};
        for (int i = 1; i < sequence.length; i += 2) {
            StdOut.println("Union " + sequence[i - 1] + " and " + sequence[i]);
            uf.union(sequence[i - 1], sequence[i]);
        }
    }
}
