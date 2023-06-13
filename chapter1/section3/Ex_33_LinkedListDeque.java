package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * A double-ended queue is like a stack or a queue but supports adding and
 * removing items at both ends.
 */
public class Ex_33_LinkedListDeque<Item> implements Iterable<Item> {
    private Node sential;
    private int N;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    public Ex_33_LinkedListDeque() {
        sential = new Node();
        sential.next = sential;
        sential.prev = sential;
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
     * add an item to the left end
     */
    public void pushLeft(Item item) {
        Node oldLeft = sential.next;

        Node left = new Node();
        left.item = item;

        left.next = oldLeft;
        left.prev = sential;
        sential.next = left;
        oldLeft.prev = left;

        N += 1;
    }

    /**
     * add an item to the right end
     */
    public void pushRight(Item item) {
        Node oldRight = sential.prev;

        Node right = new Node();
        right.item = item;

        right.prev = oldRight;
        right.next = sential;
        sential.prev = right;
        oldRight.next = right;

        N += 1;
    }

    /**
     * Remove an item from the left end.
     */
    public Item popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node left = sential.next;
        Item item = left.item;

        left = left.next;
        left.prev = sential;
        sential.next = left;

        N -= 1;
        return item;
    }

    /**
     * Remove an item from the right end.
     */
    public Item popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node right = sential.prev;
        Item item = right.item;

        right = right.prev;
        right.next = sential;
        sential.prev = right;

        N -= 1;
        return item;
    }

    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        private Node cur = sential.next;

        public boolean hasNext() {
            return cur != sential;
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
        Ex_33_LinkedListDeque<String> deque = new Ex_33_LinkedListDeque<>();

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
