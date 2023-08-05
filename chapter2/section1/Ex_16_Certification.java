package chapter2.section1;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

/**
 * Write a check() method that calls sort() for a given array and returns true if
 * sort() puts the array in order and leaves the same set of objects in the array
 * as were there initially, false otherwise. Do not assume that sort() is restircted
 * to move data only with exch(). You may use Arrays.sort() and assum that it is
 * correct.
 */
public class Ex_16_Certification {
    public static <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 1; i < a.length; i += 1) {
            a[i] = a[0];
        }
    }

    public static <T extends Comparable<T>> boolean check(T[] a)  {
        T[] copy = (T[]) new Comparable[a.length];
        // https://stackoverflow.com/questions/34827626/cannot-be-cast-to-ljava-lang-comparable
        // T[] copy = (T[]) new Object[a.length]; // error
        for (int i = 0; i < a.length; i += 1) {
            copy[i] = a[i];
        }
        // Ex_11.sort(a);
        sort(a);
        Arrays.sort(copy);
        for (int i = 0; i < a.length; i += 1) {
            if (!a[i].equals(copy[i])) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Integer[] a = {5, 3, 9, 7, 0, 4, 6};
        String[] b = {"E", "X", "A", "M", "P", "L", "E"};
        StdOut.println(check(a));
        StdOut.println(check(b));
    }
}
