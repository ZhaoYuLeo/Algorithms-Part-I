package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Show the contents of the id[] array and the number of times the array
 * is accessed for each input pair when you use quick-find for the sequence
 * 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2.
 * In addition, draw the forest of trees represented by the id[] array
 * after each input pair is processed.
 */
public class Ex_02 {
    private int[] id;  // access to component id (site indexed)
    private int count; // number of components
    private int times; // number of times the array is accessed for each union

    public Ex_02(int N) {
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
        while (id[p] != p) {
            p = id[p];
            this.times += 1;
        }
        this.times += 1;
        return p; 
    }

    public void union(int p, int q) {
        this.times = 0;
        int newRoot = find(p);
        int oldRoot = find(q);
        if (newRoot == oldRoot) {
            return;
        }
        id[p] = id[q];
        this.times += 2;
        count -= 1;
        StdOut.println(this);
        StdOut.println("\nTimes the array is accessed: " + times + "\n");
    }

    public void draw() {
        int y = 0;
        for (int i = 0; i < id.length; i += 1) {
            StdDraw.point(i, y);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < id.length; i += 1) {
            s.append(id[i]);
            s.append(" ");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Ex_02 uf = new Ex_02(10);
        int[] sequence = {9, 0, 3, 4, 5, 8, 7, 2, 2, 1, 5, 7, 0, 3, 4, 2};
        for (int i = 1; i < sequence.length; i += 2) {
            StdOut.println("Union " + sequence[i - 1] + " and " + sequence[i]);
            uf.union(sequence[i - 1], sequence[i]);
        }
    }
}
