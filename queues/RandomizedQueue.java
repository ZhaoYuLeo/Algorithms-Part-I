/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rdQueue;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // array
        rdQueue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("You cannot enqueue null element.");
        }
        if (size == rdQueue.length) {
            resize(2 * rdQueue.length);
        }
        rdQueue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. No item can be removed.");
        }
        int index = StdRandom.uniform(size);
        Item item = rdQueue[index];
        rdQueue[index] = rdQueue[--size];
        rdQueue[size] = null;
        if (size > 0 && size == rdQueue.length / 4) {
            resize(rdQueue.length / 2);
        }
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = rdQueue[i];
        }
        rdQueue = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty. No item can be removed.");
        }
        return rdQueue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RdQueueIterator();
    }

    private class RdQueueIterator implements Iterator<Item> {
        private Item[] currentQueue = (Item[]) new Object[rdQueue.length];
        private int currentSize = size;

        public RdQueueIterator() {
            for (int i = 0; i < rdQueue.length; i++) {
                currentQueue[i] = rdQueue[i];
            }
        }

        public boolean hasNext() {
            return currentSize != 0;
        }

        public void remove() {
            throw new UnsupportedOperationException("Sorry. This operation is not supported.");
        }

        public Item next() {
            if (isEmpty()) {
                throw new NoSuchElementException("There are no more items to return.");
            }
            int index = StdRandom.uniform(currentSize);
            Item item = currentQueue[index];
            currentQueue[index] = currentQueue[--currentSize];
            currentQueue[currentSize] = null;
            return item;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> RdQueue = new RandomizedQueue<Integer>();
        StdOut.println(
                "The randomized queue is empty: " + RdQueue.isEmpty() + "; \nSize is: " + RdQueue
                        .size());
        StdOut.println("Please enter one of these follow operations you want to perform: \n"
                               + "eq, dq, sp, ep, pr, sz");
        while (!StdIn.isEmpty()) {
            String command = StdIn.readString();
            switch (command) {
                case "eq":
                    StdOut.println("Enter an integer which would be insert into queue:");
                    RdQueue.enqueue(StdIn.readInt());
                    break;
                case "dq":
                    StdOut.println("Remove a random item: " + RdQueue.dequeue());
                    break;
                case "sp":
                    StdOut.println("A random item: " + RdQueue.sample());
                    break;
                case "ep":
                    StdOut.println("This randomized queue is empty: " + RdQueue.isEmpty());
                    break;
                case "pr":
                    for (Integer d : RdQueue) {
                        StdOut.println(d);
                    }
                    break;
                case "sz":
                    StdOut.println("Size is: " + RdQueue.size());
                    break;
                default:
                    break;
            }
        }
    }
}

