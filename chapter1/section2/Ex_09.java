package chapter1.section2;

import java.util.Arrays;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/*
 * Use a Counter to count the total number of keys examined during all searches
 * and then print the total after all searches are complete.
 */
public class Ex_09 {

    // iteration
    public static int rank(int key, int[] a, Counter count) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            count.increment();
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        Counter count = new Counter("check");
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (rank(key, whitelist, count) < 0)
                StdOut.println(key);
        }
        StdOut.println(count);

    }
}
