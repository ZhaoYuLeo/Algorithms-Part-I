package chapter2.section2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a mergesort implementation based on the idea of doing k-way merges (rather
 * than 2-way merges). Analyze your algorithm, develop a hypothesis regarding the best
 * value of k, and run experiments to validate your htpothesis.
 */
public class Ex_25_MultiwayMergesort {

    public static void sort(Comparable[] a, int k) {
        assert k > 1 : "Merge more than two subarray";
        Comparable[] aux = new Comparable[a.length];

        sort(a, aux, k, 0, a.length);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int k, int lo, int hi) {
        if (hi <= lo + 1) {
            return;
        }
        
        int kWays = Math.min(k, hi - lo);

        int[] index = new int[kWays + 1];

        int len = hi - lo;
        int size = len / kWays;
        int remain = len % kWays;

        int lWall = lo, rWall = 0;
        index[0] = lWall;

        for (int i = 1; i <= kWays; i += 1) {
            rWall = lWall + size;
            index[i] = rWall;

            sort(a, aux, k, lWall, rWall);

            lWall = rWall;
        }

        if (remain != 0) {
            sort(a, aux, k, index[kWays], hi);

            index = Arrays.copyOf(index, index.length + 1);
            index[kWays + 1] = hi;
        }

        kMerge(a, aux, index);
    }

    public static void kMerge(Comparable[] a, Comparable[] aux, int[] index) {
        // assume subarrays divided by index are sorted.
        
        int k = index.length - 1, lo = index[0], hi = index[k];


        // hi is not included in the subarray
        for (int i = lo; i < hi; i += 1) {
            aux[i] = a[i];
        }

        int[] indexCopy = new int[k + 1];
        for (int i = 0; i <= k; i += 1) {
            indexCopy[i] = index[i];
        }

        for (int j = lo; j < hi; j += 1) {

            // find the smallest element. O(k)
            int min = 0;
            while (index[min] == -1) {
                min += 1;
            }
            for (int i = 1; i < k; i += 1) {
                if (index[i] == -1) {
                    continue;
                }

                if (less(aux[index[i]], aux[index[min]])) {
                    min = i;
                }
            }

            // put the smallest element in the original array and move index 
            a[j] = aux[index[min]];
            index[min] += 1;

            // all elements in the subarray are taken out
            if (index[min] >= indexCopy[min + 1]) {
                index[min] = -1;
            }
        }

    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void show(int[] a) {
        for (int i = 0; i < a.length; i += 1) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Integer[] a = {0, 5, 2, 4, 8, 6, 7, 1, 3, 9};
        sort(a, 3);
        show(a);
    }
}
