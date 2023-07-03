package chapter1.section4;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/** 
 * Add an instance method howMany() to StaticSETofInts (page 99) that finds the number
 * of occurrences of a given key in time proportional to log N in the worst case.
 */
public class Ex_11_HowMany {
    private int[] a;
    
    public Ex_11_HowMany(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }

    public int howMany(int key) {
        // find the smallest index
        int smallestIndex = smallest(key);
        // find the highest index
        int highestIndex = highest(key);
        if (smallestIndex == -1) {
            return 0;
        }
        return highestIndex - smallestIndex + 1;
    }

    private int smallest(int key) {
        // variant binary search
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo < a.length && a[lo] == key) {
            return lo;
        }
        return -1;
    }

    private int highest(int key) {
        // variant binary search
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
        if (hi > 0 && a[hi] == key) {
            return hi;
        }
        return -1;
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) {
        // Binary search.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {0, 0, 0, 1, 3, 4, 4, 4, 8, 12, 12, 19, 19, 19, 19, 19};
        Ex_11_HowMany stat = new Ex_11_HowMany(a);
        int result = stat.howMany(Integer.parseInt(args[0]));
        StdOut.println(result);
    }
}
