package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implements the queue abstraction with a fixed-size array and extends to resizing array.
 */
public class Ex_14_ResizingArrayQueueOfStrings<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 8;

    // - a b c d -
    private Item[] a; // queue entries
    private int N; // size
    private int first; // index of the first element
    private int last; // index of the next available slot 

    public Ex_14_ResizingArrayQueueOfStrings(int cap) {
        a = (Item[]) new Object[cap];
        N = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds the item to this queue.
     * @param item the item to add
     */
    public void enqueue(Item item) {
        if (N == a.length) {
            resize(2 * N);
        }
        a[last] = item;
        last = (last + 1) % a.length;
        N += 1;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (N == 0) {
            throw new NoSuchElementException();
        }
        N -= 1;
        Item item = a[first];
        a[first] = null; // delete reference
        first = (first + 1) % a.length;
        if (N == a.length / 4) {
            resize(N / 2);
        }
        return item;
    }

    /**
     * Is the queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number if items in this queue.
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
            copy[i] = a[i + first];
        }
        a = copy;
        first = 0;
        last = N;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order.
     */
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        public boolean hasNext() {
            return N > i;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = a[(i + first) % a.length];
            i += 1;
            return item;
        }
    }

    public static void main(String[] args) {
        Ex_14_ResizingArrayQueueOfStrings<String> queue = new Ex_14_ResizingArrayQueueOfStrings<String>(100);
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.enqueue(item);
            } else if (!queue.isEmpty()) {
                StdOut.print(queue.dequeue() + " ");
            }
        }
        StdOut.println("(" + queue.size() + " left on queue");
    }
}
