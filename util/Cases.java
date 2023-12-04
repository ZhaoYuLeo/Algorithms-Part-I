package util;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Provide various test data
 */
public class Cases {

    private final static int MAX = 100000;

    // Corner Cases

    /**
     * Return an integer array of size N which are already in order.
     */
    public static Integer[] ordered(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = i;
        }
        return a;
    }

    /**
     * Return an integer array of size N which are in reverse order.
     */
    public static Integer[] reverseOrdered(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = N - i;
        }
        return a;
    }

    /**
     * Return an integer array of size N where all keys are the same
     */
    public static Integer[] same(int N) {
        int key = StdRandom.uniform(-MAX, MAX); 
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = key;
        }
        return a;
    }

    /**
     * Return an integer array of size N consisting of only two distinct values.
     */
    public static Integer[] twoDistinct(int N) {
        int key1 = StdRandom.uniform(-MAX, MAX); 
        int key2 = key1;
        while (key2 == key1) {
            key2 = StdRandom.uniform(-MAX, MAX); 
        }
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            if (StdRandom.random() < 0.5) {
                a[i] = key1;
            } else {
                a[i] = key2;
            }
        }
        return a;
    }

    /**
     * Return an integer array of size N consisting of only k distinct values.
     */
    public static Integer[] kDistinct(int N, int k) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform(0, k); // integers from 0 to k
        }
        return a;
    }

    /**
     * Return an integer array of size 0
     */
    public static Integer[] zero() {
        Integer[] a = new Integer[0];
        return a;
    }

    /**
     * Return an integer array of size 1
     */
    public static Integer[] one() {
        Integer[] a = new Integer[1];
        a[0] = StdRandom.uniform(-MAX, MAX); 
        return a;
    }

    // Random Array
    
    /**
     * Return an integer array of size N with random value
     */
    public static Integer[] randomArray(int N) {
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform(-MAX, MAX); 
        }
        return a;
    }

    /**
     * Return an integer array of size N with distinct random value
     */
    public static Integer[] randomDistinctArray(int N) {
        Integer[] a = new Integer[N];
        int gap = StdRandom.uniform(N);
        for (int i = 0; i < N; i += 1) {
            a[i] = i * gap + StdRandom.uniform(gap);
        }
        StdRandom.shuffle(a);
        return a;
    }

    public static void test(Integer[] a) {
        StdOut.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int N = 10;
        test(ordered(N));
        test(reverseOrdered(N));
        test(same(N));
        test(twoDistinct(N));
        test(zero());
        test(one());
        test(randomArray(N));
        test(randomDistinctArray(N));
    }
}
