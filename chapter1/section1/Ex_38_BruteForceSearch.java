package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

/*
 * Modify the test client in BinarySearch to remove any duplicate keys in the whitelist after the sort.
 */
public class Ex_38_BruteForceSearch {

    public static int bruteForceSearch(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }
    public static int binarySearch(int key, int[] a) {
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

    public static double time(String alg, int key, int[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("B")) binarySearch(key, a);
        if (alg.equals("F")) bruteForceSearch(key, a);
        return timer.elapsedTime();
    }


    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);
        String alg = args[1];
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            Integer key = StdIn.readInt();
            StdOut.println(time(alg, key, whitelist));
        }
    }
}
