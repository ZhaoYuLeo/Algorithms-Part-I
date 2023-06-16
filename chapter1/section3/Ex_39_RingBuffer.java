package chapter1.section3;

import java.lang.RuntimeException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * A ring buffer, or circular queue, is a FIFO data structure of a ﬁxed size N.
 * It is useful for transferring data between asynchronous processes or for storing
 * log ﬁles. When the buffer is empty, the consumer waits until data is deposited;
 * when the buffer is full, the producer waits to deposit data.
 * Ref: https://en.wikipedia.org/wiki/Circular_buffer
 */
public class Ex_39_RingBuffer<Item> implements Iterable<Item> {

    private Item[] a; // buffer entries
    private int N;     // size
    private int readIndex;
    private int writeIndex;


    public Ex_39_RingBuffer(int capacity) {
        a = (Item[]) new Object[capacity];
        N = 0;
        readIndex = 0; 
        writeIndex = 0;
    }

    /**
     * is the buffer empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }


    /**
     * is the buffer full?
     */
    public boolean isFull() {
        return N == a.length;
    }

    /**
     * number of items in the buffer 
     */
    public int size() {
        return N;
    }

    /**
     * add an item to the end of the buffer
     */
    public void put(Item item) {
        if (isFull()) {
            throw new RuntimeException();
        }
        a[writeIndex] = item;
        writeIndex = (writeIndex + 1) % a.length;
        N += 1;
    }

    /**
     * remove and return the first item in the buffer
     */
    public Item get() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item temp = a[readIndex];
        a[readIndex] = null;
        readIndex = (readIndex + 1) % a.length;

        N -= 1;
        return temp;
    }


    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int cur = readIndex;
        private int count = 0;

        public boolean hasNext() {
            return count < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            count += 1;
            Item item = a[cur];
            cur = (cur + 1) % a.length;
            return item;
        }
    }


    public static void main(String[] args) {
        Ex_39_RingBuffer<Integer> buffer = new Ex_39_RingBuffer<>(10);
        for (int s = 0; s < 15; s += 1) {
            try {
                if (s % 3 == 0) {
                    StdOut.println("Consumer get: " + buffer.get());
                } else {
                    buffer.put(s);
                }
            } catch(Exception e) {
                // do nothing
            }
        }

        for (Integer s : buffer) {
            StdOut.println(s);
        }

        StdOut.println("size is " + buffer.size());
    }
}
