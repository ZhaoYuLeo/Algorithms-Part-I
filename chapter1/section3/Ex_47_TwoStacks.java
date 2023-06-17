package chapter1.section3;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * Implement two stacks with a single deque so that each operation takes a constant
 * number of deque operations.
 */
public class Ex_47_TwoStacks<Item> {
    private Ex_33_LinkedListDeque<Item> d;
    private int sizeA;
    private int sizeB;

    public Ex_47_TwoStacks() {
        d = new Ex_33_LinkedListDeque<Item>();
        sizeA = 0;
        sizeB = 0;
    }

    /**
     * is the stack A empty?
     */
    public boolean isEmptyA() {
        return sizeA == 0;
    }

    /**
     * is the stack A empty?
     */
    public boolean isEmptyB() {
        return sizeB == 0;
    }

    /**
     * number of items in the stack A
     */
    public int sizeA() {
        return sizeA;
    }

    /**
     * number of items in the stack B
     */
    public int sizeB() {
        return sizeB;
    }

    /**
     * add an item into stack A
     */
    public void pushA(Item x) {
        d.pushLeft(x);
        sizeA += 1;
    }

    /**
     * add an item into stack B
     */
    public void pushB(Item x) {
        d.pushRight(x);
        sizeB += 1;
    }

    /**
     * remove the most recently added item in stack A
     */
    public Item popA() {
        if (isEmptyA()) {
            throw new NoSuchElementException();
        }
        sizeA -= 1;
        return d.popLeft(); 
    }


    /**
     * remove the most recently added item in stack B
     */
    public Item popB() {
        if (isEmptyB()) {
            throw new NoSuchElementException();
        }
        sizeB -= 1;
        return d.popRight(); 
    }

    public static void main(String[] args) {
        Ex_47_TwoStacks<Integer> s = new Ex_47_TwoStacks<>();
        for (int i = 0; i < 40; i += 1) {
            if (i % 2 == 0) {
                s.pushA(i);
                if (i % 10 == 0) {
                    StdOut.println("A pop out: " + s.popA());
                }
            } else {
                s.pushB(i);
                if (i % 7 == 0) {
                    StdOut.println("B pop out: " + s.popB());
                }
            }
        }
        StdOut.println("Size of stack A is : " + s.sizeA());
        StdOut.println("Size of stack B is : " + s.sizeB());
        while(!s.isEmptyA()) {
            StdOut.print(s.popA() + " ");
        }
        StdOut.println();

        while(!s.isEmptyB()) {
            StdOut.print(s.popB() + " ");
        }
        StdOut.println();
    }
}
