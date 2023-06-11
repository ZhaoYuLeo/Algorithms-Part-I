package chapter1.section3;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_16 {

    public static int[] readInts1(String name) {
        String input = StdIn.readAll();
        String[] words = input.split("\\s+");
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++)
            ints[i] = Integer.parseInt(words[i]);
        return ints;
    }

    public static int[] readInts(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<Integer>();
        while (!in.isEmpty())
            q.enqueue(in.readInt());
        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = q.dequeue();
        return a;
    }

    /**
     * Reads dates from standard input in the speciﬁed format and returns an array containing them.
     */
    public static Date[] readDates(String name) {
        In in = new In(name);
        Queue<String> q = new Queue<>();
        while (!in.isEmpty())
            q.enqueue(in.readString());
        int N = q.size();
        Date[] a = new Date[N];
        for (int i = 0; i < N; i += 1) {
            String[] f = q.dequeue().split("/");
            int m = Integer.parseInt(f[0]);
            int d = Integer.parseInt(f[1]);
            int y = Integer.parseInt(f[2]);
            a[i] = new Date(m, d, y);
        }
        return a;
    }

    public static void main(String[] args) {
        Date[] dates = readDates(args[0]);
        for (Date d : dates) {
            StdOut.println(d);
        }
    }
}
