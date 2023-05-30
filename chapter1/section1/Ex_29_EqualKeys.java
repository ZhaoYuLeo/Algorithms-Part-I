package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*
 * If i and j are the values returned by rank(key, a) and count(key, a) respectively,
 * then a[i..i+j-1] are the values in the array that are equal to key.
 */
public class Ex_29_EqualKeys {

    /*
     * Takes a key and a sorted array of int values (some of which may be equal)
     * as arguments and returns the number of elements that are smaller than the key.
     */
    public static int rank(int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    /*
     * Similar to rank but returns the number of elements greater than the key.
     */
    public static int greater(int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return a.length - lo;
    }

    /*
     * Similar to rank but returns the number of elements equal to the key.
     */
    public static int count(int key, int[] a) {
        return a.length - rank(key, a) - greater(key, a);
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        for (int i = 0; i < whitelist.length; i += 1) {
            StdOut.println(i + " : " + whitelist[i]);
        }
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            StdOut.println("smaller than " + key + " : " + rank(key, whitelist) + "; equal : " + count(key, whitelist));
        }

    }
}
