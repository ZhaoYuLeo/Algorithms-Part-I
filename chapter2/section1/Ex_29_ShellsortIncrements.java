package chapter2.section1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;

/**
 * Run experiments to compare the increment sequence in ALGORITHM 2.3 with the
 * sequence 1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929, 16001, 36289,
 * 64769, 146305, 260609 (which is formed by merging together the sequences
 * 9*4^k - 9*2^k + 1 and 4^k - 3*2^k + 1)
 */
public class Ex_29_ShellsortIncrements extends Sort {
    public Ex_29_ShellsortIncrements() {
        super("Shellsort increments");
    }

    public <T extends Comparable<T>> void sort(T[] a) {
        int N = a.length;
        int[] hs = mergeSequence(N);
        
        for (int k = hs.length - 1; k > -1; k -= 1) {
            int h = hs[k];

            // h-sort the array
            for (int i = h; i < N; i += 1) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
        }
    }

    public static int[] mergeSequence(int N) {
        ArrayList<Integer> a = sequence1(N);
        ArrayList<Integer> b = sequence2(N);
        int[] hs = new int[a.size() + b.size()];
        int i = 0;
        int j = 0;
        int k = 0;
        while (k < hs.length) {
            if (i >= a.size()) {
                hs[k] = b.get(j);
                j += 1;
                k += 1;
                continue;
            }

            if (j >= b.size()) {
                hs[k] = a.get(i);
                i += 1;
                k += 1;
                continue;
            }

            if (a.get(i) < b.get(j)) {
                hs[k] = a.get(i);
                i += 1;
                k += 1;
                continue;
            } 

            if (a.get(i) >= b.get(j)) {
                hs[k] = b.get(j);
                j += 1;
                k += 1;
                continue;
            }
        }
        return hs;
    }

    private static ArrayList<Integer> sequence1(int N) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0;; i += 1) {
            int s = 9 * (int)Math.pow(4, i) - 9 * (int)Math.pow(2, i) + 1;
            if (s > N) {
                break;
            }
            a.add(s);
        }
        return a;
    }

    private static ArrayList<Integer> sequence2(int N) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0;; i += 1) {
            int s = (int)Math.pow(4, i + 2) - 3 * (int)Math.pow(2, i + 2) + 1;
            if (s > N) {
                break;
            }
            a.add(s);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] hs = mergeSequence(Integer.parseInt(args[0]));
        for (int h : hs) {
            System.out.println(h);
        }
        String[] a = {"S", "H", "E", "L", "L", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Sort instance = new Ex_29_ShellsortIncrements();
        instance.sort(a);
        show(a);
    }
}
