package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

public class SelectionSort extends Sort {

    public SelectionSort() {
        super("Selection");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;

        for (int i = 0; i < N; i += 1) {
            int min = i;
            for (int j = i + 1; j < N; j += 1) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new SelectionSort();
        instance.sort(a);
        show(a);
    }
}
