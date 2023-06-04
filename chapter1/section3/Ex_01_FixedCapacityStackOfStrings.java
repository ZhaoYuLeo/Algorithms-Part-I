package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex_01_FixedCapacityStackOfStrings {
    private String[] a; // stack entries
    private int N; // size

    public Ex_01_FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() { 
        return N == 0;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item; 
    }

    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        Ex_01_FixedCapacityStackOfStrings s;
        s = new Ex_01_FixedCapacityStackOfStrings(1);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (s.isFull()) {
                StdOut.println("Stack is full.");
                break;
            }
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

