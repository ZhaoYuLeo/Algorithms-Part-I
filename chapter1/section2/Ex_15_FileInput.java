package chapter1.section2;

import java.util.Queue;
import java.util.LinkedList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Develop a possible implementation of the static readInts() method from In
 * that is based on the split() method in String.
 */
public class Ex_15_FileInput {

    public static int[] readInts(String name) {
        In in = new In(name);
        String input = in.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < ints.length; i++)
            ints[i] = Integer.parseInt(words[i]);
        return ints;
    }

    public static int[] readIntsB(String name) {
        In in = new In(name);
        Queue<Integer> q = new LinkedList<Integer>();
        while (!in.isEmpty())
            q.add(in.readInt());

        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = q.remove();
        return a;
    }

    public static void main(String[] args) {
        int[] a = readInts(args[0]);
        int[] b = readIntsB(args[0]);

        for (int i : a) {
            StdOut.println(i);
        }

        for (int i : b) {
            StdOut.println(i);
        }
    }
}
