package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*
 * Binary search
 */
public class Ex_22 {

     // // iteration
     // public static int rank(int key, int[] a) {
     //     // Array must be sorted.
     //     int lo = 0;
     //     int hi = a.length - 1;
     //     while (lo <= hi) {
     //         // Key is in a[lo..hi] or not present.
     //         int mid = lo + (hi - lo) / 2;
     //         if (key < a[mid]) hi = mid - 1;
     //         else if (key > a[mid]) lo = mid + 1;
     //         else return mid;
     //     }
     //     return -1;
     // }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }

    // recursion
    private static int rank(int key, int[] a, int lo, int hi, int depth) {
        // traces the method calls
        for (int i = 0; i < depth; i += 1) {
            StdOut.print("  ");
        }
        StdOut.printf("lo: %d, hi: %d\n", lo, hi);
        // Index of key in a[], if present, is not smaller than lo
        // and not larger than hi. 
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, depth + 1);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, depth + 1);
        else return mid;
    }


    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) < 0)
                StdOut.println(key);
        }

    }
}
