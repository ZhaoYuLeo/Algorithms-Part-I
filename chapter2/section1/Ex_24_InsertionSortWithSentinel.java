package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Develop an implementation of insertion sort that eliminates the j>0 test in
 * the inner loop by first putting the smallest item into position. Use
 * SortCompare to evaluate the effectiveness of doing so. Note: It is often
 * possible to avoid an index-out-of-bounds test in this way-the element that
 * enables the test to be eliminated is known as a sentinel.
 */
public class Ex_24_InsertionSortWithSentinel extends Sort {
    public Ex_24_InsertionSortWithSentinel() {
        super("Insertion Sort With Sentinel");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;

        int min = 0;

        for (int i = 0; i < N; i += 1) {
            if (less(a[i], a[min])) {
                min = i;
            }
        }
        exch(a, min, 0);

        for (int i = 1; i < N; i += 1) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {3, 9, 6, 8, 4, 0, 1, 7};
        Sort instance = new Ex_24_InsertionSortWithSentinel();
        instance.sort(a);
        show(a);

        Sort alg1 = instance;
        Sort alg2 = new InsertionSort();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        double t1 = SortCompare.timeRandomInput(alg1, N, T); // total for alg1
        double t2 = SortCompare.timeRandomInput(alg2, N, T); // total for alg2
        StdOut.printf("For %d random Doubles\n %s is", N, alg1.getName());
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2.getName());
        
        // slightly difference
        // 0 1 3 4 6 7 8 9
        // For 4000 random Doubles
        //  Insertion Sort With Sentinel is 1.1 times faster than Insertion
    }
}
