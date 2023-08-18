package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

public class Shellsort extends Sort {

    public Shellsort() {
        super("Shellsort");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i += 1) {
                for (int j = i; j > h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new InsertionSort();
        instance.sort(a);
        show(a);
    }
}
