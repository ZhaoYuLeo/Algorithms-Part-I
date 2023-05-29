package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*
 * Modify the test client in BinarySearch to remove any duplicate keys in the whitelist after the sort.
 */
public class Ex_28_RemoveDuplicates {

    // iteration
    public static int rank(int key, int[] a) {
        // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
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
        Arrays.sort(whitelist);
        StdOut.println("whitelist sorted: ");
        for (int w : whitelist) {
            StdOut.println(w);
        }

        int count = 0;
        int[] noduplicate = new int[whitelist.length];
        int prev = whitelist[0];
        noduplicate[0] = whitelist[0];
        for (int i = 1; i < whitelist.length; i += 1) {
            if (whitelist[i] != prev) {
                // // I wish I could write this here.
                // whitelist[i] = null;
                count += 1;
                prev = whitelist[i];
                noduplicate[count] = prev;
            }
        }
        whitelist = Arrays.copyOfRange(noduplicate, 0, count + 1);
        StdOut.println("whitelist removed duplicate: ");
        for (int n : whitelist) {
            StdOut.println(n);
        }
        while (!StdIn.isEmpty()) {
            Integer key = StdIn.readInt();
            if (rank(key, whitelist) < 0)
                StdOut.println(key);
        }

    }
}
