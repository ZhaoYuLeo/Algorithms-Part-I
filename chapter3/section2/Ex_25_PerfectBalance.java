package chapter3.section2;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a program that inserts a set of keys into an initially empty BST such that 
 * the tree produced is equivalent to binary search, in the sense that the sequence
 * of compares done in the search for any key in the BST is the same as the sequence
 * of compares used by binary search for the same set of keys.
 */
public class Ex_25_PerfectBalance { 
    private static void perfect(BST<String, Integer> st, String[] a) {
        // Can you build the BST tree without sorting the array?
        // I don't know.
        Arrays.sort(a);
        perfect(st, a, 0, a.length - 1);
    }

    private static void perfect(BST<String, Integer> st, String[] a, int lo, int hi) {
        if (hi < lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        st.put(a[mid], mid); // any value you want
        perfect(st, a, lo, mid - 1);
        perfect(st, a, mid + 1, hi);
    }

    public static int binarySearch(String[] a, String key) {
        Arrays.sort(a);
        return binarySearch(a, key, 0, a.length - 1);
    }

    private static int binarySearch(String[] a, String key, int lo, int hi) {
        if (hi < lo) {
            // return -1;
            // return the index of element that is less than key
            return hi;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(a[mid]);

        StdOut.println(a[mid]);

        if (cmp < 0) {
            return binarySearch(a, key, lo, mid - 1);
        } else if (cmp > 0) {
            return binarySearch(a, key, mid + 1, hi);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        String[] word = {"P", "E", "R", "F", "C", "T", "B", "I", "N", "A", "R", "Y", "S", "R", "H"};
        BST<String, Integer> st = new BST<>();
        String key = StdIn.readString();
        perfect(st, word);
        st.get(key);
        StdOut.println("Binary search");
        binarySearch(word, key);
    }
}
