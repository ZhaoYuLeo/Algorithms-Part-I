package chapter2.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Suppose that we scan over items with keys equal to the partitioning item's key instead of
 * stopping the scans when we enconnter them. show that the running time of this version of
 * quicksort is quadratic for all arrays with just a constant number of distinct keys.
 */
public class Ex_11 {
    public static int compare;
    public static int exch;
    public static double avgCompare = 1;
    public static double avgExch = 1;

    public static void experiment(int maxDistinct, int maxScale) {
        for (int k = 1; k < maxDistinct; k += 1) {
            for (int n = 1000; n < maxScale; n += n) {
                trial(n, k);
            }
            StdOut.println();
        }
    }

    public static void trial(int N, int k) {
        compare = 0;
        exch = 0;

        int totalCompare = 0;
        int totalExch = 0;
        int times = 100;
        StdOut.printf("%6s|%12s|%6s|%6s\n", "Size", "Distinct key", "Compare", "Exch");
        for (int t = 0; t < times; t += 1) {
            Integer[] a = new Integer[N];
            for (int i = 0; i < N; i += 1) {
                a[i] = StdRandom.uniform(k); // random int in [0, k).
            }
            sort(a);
            totalCompare += compare;
            totalExch += exch;
        }
        double prevCompare = avgCompare; 
        double prevExch = avgExch; 
        avgCompare = totalCompare * 1.0 / times;
        avgExch = totalExch * 1.0 / times; 
        StdOut.printf("%6d %12d %6.1f %6.1f\n", N, k, avgCompare * 1.0 / prevCompare, avgExch * 1.0 / prevExch);
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // assume lo < hi
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while(true) {
            while(less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while(less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (j <= i) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, j, lo);
        return j;
    }

    private static boolean less(Comparable v, Comparable w) {
        compare += 1;
 
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        exch += 1;

        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void display(Comparable[] a) {
        for (Comparable i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        experiment(4, 10000);

        // We can see from the the ratio that the running time is quadratic.(linear growth for logN)
        // Size|Distinct key|Compare|  Exch
        // 1000            1 449349.0 224674.5 (ignore the datum)
        // Size|Distinct key|Compare|  Exch
        // 2000            1    2.2    2.2
        // Size|Distinct key|Compare|  Exch
        // 4000            1    2.2    2.2
        // Size|Distinct key|Compare|  Exch
        // 8000            1    2.2    2.2

        // Size|Distinct key|Compare|  Exch
        // 1000            2    0.1    0.1
        // Size|Distinct key|Compare|  Exch
        // 2000            2    2.2    2.2
        // Size|Distinct key|Compare|  Exch
        // 4000            2    2.2    2.2
        // Size|Distinct key|Compare|  Exch
        // 8000            2    2.2    2.2

        // Size|Distinct key|Compare|  Exch
        // 1000            3    0.1    0.1
        // Size|Distinct key|Compare|  Exch
        // 2000            3    2.2    2.3
        // Size|Distinct key|Compare|  Exch
        // 4000            3    2.2    2.2
        // Size|Distinct key|Compare|  Exch
        // 8000            3    2.2    2.2
    }
