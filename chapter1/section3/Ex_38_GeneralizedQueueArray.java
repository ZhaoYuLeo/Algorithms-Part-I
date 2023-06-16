package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * Delete kth element. Array implementation.
 */
public class Ex_38_GeneralizedQueueArray<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 2;

    private Item[] a; // queue entries
    private int N;     // size

    public Ex_38_GeneralizedQueueArray() {
        a = (Item[]) new Object[INIT_SIZE];
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
        assert N <= capacity : "will lose elements.";
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i += 1) {
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * add an item
     */
    public void insert(Item x) {
        if (N == a.length) {
            resize(N * 2);
        }
        a[N++] = x;
    }

    /**
     * delete and return the kth least recently inserted item
     * 0th..kth
     */
    public Item delete(int k) {
        if (isEmpty() || k < 0 || k >= N) {
            throw new NoSuchElementException();
        }
        Item item = a[k];
        for (int i = k; i < N - 1; i += 1) {
            a[i] = a[i + 1];
        }
        a[--N] = null;
        if (N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayDequeIterator();
    }

    private class ResizingArrayDequeIterator implements Iterator<Item> {
        private int count = 0;

        public boolean hasNext() {
            return count < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[count++];
        }
    }

    public static void main(String[] args) {
        Ex_38_GeneralizedQueueArray<Integer> deque = new Ex_38_GeneralizedQueueArray<>();
        for (int i = 0; i < 10; i += 1) {
            deque.insert(i);
        }
        for (Integer s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        StdOut.println(deque.delete(7) + " ,size is " + deque.size());

        for (int i = 5; i > 0; i -= 1) {
            StdOut.println(deque.delete(i - 1) + " ,size is " + deque.size());
        }
    }
}
