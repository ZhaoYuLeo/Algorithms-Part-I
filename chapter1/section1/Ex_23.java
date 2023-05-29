package chapter1.section1;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*
 * Add to the BinarySearch test client the ability to respond to a second argument: 
 * + to print numbers from standard input that are not in the whitelist, - to print
 * numbers that are in the whitelist.
 */
public class Ex_23 {

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
        char operation = args[1].charAt(0); //.
        int in = 1;
        if (operation == '+') {
            // keep the original value.
        } else if (operation == '-') {
            in = -1;
        } else {
            StdOut.println("Please provide '+' or '-' as the second argument using '-' to indicate you want the numbers in the whitelist, '+' in the opposite.");
        }
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) * in < 0)
                StdOut.println(key);
        }

    }
}
