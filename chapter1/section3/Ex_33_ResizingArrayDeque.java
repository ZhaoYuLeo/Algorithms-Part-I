package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * A double-ended queue is like a stack or a queue but supports adding and
 * removing items at both ends.
 */
public class Ex_33_ResizingArrayDeque<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 2;

    // |+++right++++left+++|
    private Item[] dq; // queue entries
    private int N;     // size
    private int left;  // going to leave this position
    private int right; // going to insert in this position


    public Ex_33_ResizingArrayDeque() {
        dq = (Item[]) new Object[INIT_SIZE];
        left = 0; 
        right = 0;
        N = 0;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * number of items in the deque
     */
    public int size() {
        return N;
    }

    /**
     * resize the underlying array
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i += 1) {
            copy[i] = dq[(i + left) % dq.length];
        }
        dq = copy;
        left = 0;
        right = N;
    }

    /**
     * add an item to the left end
     */
    public void pushLeft(Item item) {
        if (N == dq.length) {
            resize(2 * N);
        }
        left = (left - 1 + dq.length) % dq.length;
        dq[left] = item;
        N += 1;
    }

    /**
     * add an item to the right end
     */
    public void pushRight(Item item) {
        if (N == dq.length) {
            resize(2 * N);
        }
        dq[right] = item;
        right = (right + 1) % dq.length;
        N += 1;
    }

    /**
     * Remove an item from the left end.
     */
    public Item popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = dq[left];
        dq[left] = null;
        left = (left + 1) % dq.length;
        N -= 1;
        if (N == dq.length / 4) {
            resize(N / 2);
        }
        return item;
    }

    /**
     * Remove an item from the right end.
     */
    public Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        right = (right - 1 + dq.length) % dq.length;
        Item item = dq[right];
        dq[right] = null;
        N -= 1;
        if (N == dq.length / 4) {
            resize(N / 2);
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayDequeIterator();
    }

    private class ResizingArrayDequeIterator implements Iterator<Item> {
        private int cur = left;
        private int count = 0;

        public boolean hasNext() {
            return count < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = dq[cur];
            cur = (cur + 1) % dq.length;
            count += 1;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex_33_ResizingArrayDeque<String> deque = new Ex_33_ResizingArrayDeque<>();

        deque.pushLeft("We");
        deque.pushRight("are");
        deque.pushLeft("going");
        deque.pushRight("to");
        StdOut.println(deque.popLeft());
        deque.pushRight("die");
        deque.pushRight(",");
        deque.pushRight("and");
        deque.pushLeft("that");
        StdOut.println(deque.popLeft());
        deque.pushLeft("makes");
        deque.pushLeft("us");
        deque.pushLeft("the");
        StdOut.println(deque.popRight());
        StdOut.println(deque.popLeft());
        StdOut.println(deque.popRight());
        deque.pushRight("lucky");
        deque.pushLeft("ones");
        deque.pushLeft(".");

        StdOut.println("size is " + deque.size());
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
