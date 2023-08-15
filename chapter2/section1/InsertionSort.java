package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

public class InsertionSort extends Sort {

    public InsertionSort() {
        super("Insertion");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;

        for (int i = 1; i < N; i += 1) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j -= 1) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new InsertionSort();
        instance.sort(a);
        show(a);
    }
}
