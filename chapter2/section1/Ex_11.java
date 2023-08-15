package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a veresion of shellsort that keeps the increment sequence in an
 * array, rather than computing it.
 */
public class Ex_11 extends Sort {

    public Ex_11() {
        super("Shellsort Keeps the Increment Sequence");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        int[] hs = generateHsequence(N);

        int maxHI = hs.length - 1;
        while (hs[maxHI] > N) {
            maxHI -= 1;
        }

        for (int hi = maxHI; hi > -1; hi -= 1) {
            int h = hs[hi];

            // h-sort the array
            for (int i = h; i < N; i += 1) {
                for (int j = i; j >= h && less(a[j], a[j - h]) ; j -= h) {
                    exch(a, j, j - h);
                }
            }
        }

    }

    private static int[] generateHsequence(int N) {
        int size = (int)(Math.ceil(Math.log(N / 3.0) / Math.log(3)) + 1);
        int[] hSequence = new int[size];
        int h = 1;

        for (int i = 0; i < size; i += 1) {
            hSequence[i] = h;
            h = 3 * h + 1;
        }
        return hSequence;
    }

    public static void main(String[] args) {
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new Ex_11();
        instance.sort(a);
        show(a);
    }
}

