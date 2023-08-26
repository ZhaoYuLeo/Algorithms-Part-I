package chapter2.section2;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Show that the number of compares used by mergesort is monotonically increasing (C(N+1) > C(N) for all N > 0).
 */
public class Ex_07 {
    private static Double[] aux;

    public static int numberOfCompares;

    public static void sort(Double[] a) {
        numberOfCompares = 0;
        aux = new Double[a.length];
        sort(a, 0, a.length - 1);
    }

    
    public static void sort(Double[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Double[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static boolean less(Double v, Double w) {
        numberOfCompares += 1;

        return v.compareTo(w) < 0;
    }


    public static Double[] randomInput(int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i += 1) {
            a[i] = StdRandom.uniform();
        }
        return a;
    }

    public static void main(String[] args) {
        int maxScale = Integer.parseInt(args[0]); 
        int prev = 0;
        for (int n = 128; n < maxScale; n += n) {
            Double[] a = randomInput(n);
            sort(a);
            if (numberOfCompares < prev) {
                StdOut.println(false);
            }
            prev = numberOfCompares;
            StdOut.println(n + " " + numberOfCompares);
        }
    }
}
