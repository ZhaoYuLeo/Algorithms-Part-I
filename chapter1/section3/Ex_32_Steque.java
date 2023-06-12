package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * A stack-ended queue supports push, pop and enqueue.
 * This implementation is based on linked-list.
 */
public class Ex_32_Steque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // Add item to the beginning of the list.
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        // if isEmpty() was "return first == null;" we would never get there. 
        if (isEmpty()) {
            // if node was null, there was no address for last points to.
            // we have to modify this by hand.
            last = first;
        }
        N += 1;
    }

    public Item pop() {
        // pop out one item from the beginning of the list.
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        N -= 1;
        // we cannot change the order since isEmpty() relies on the front lines.
        if (isEmpty()) {
            // The old first which the last once pointed to has been thrown away.
            last = null;
        }
        return item;
    }

    public void enqueue(Item item) {
        // Add item to the end of the list.
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N += 1;
    }

    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item> {
        private Node cur = first;

        public boolean hasNext() {
            return cur != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = cur.item;
            cur = cur.next; 
            return item;
        }
    }

    public static void main(String[] args) {
        Ex_32_Steque<String> steque = new Ex_32_Steque<>();
        steque.push(",");
        steque.enqueue("the");
        steque.push("Hello");
        steque.enqueue("world");
        for (String s : steque) {
            StdOut.println(s);
        }
    }
}
