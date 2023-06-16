package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implements the well-known move-to-front strategy, which is useful for 
 * caching, data compression ,and many other application where items that
 * have been recently accessed more likely to be reaccessed.
 * Move-to-front:
 * Read in a previously unseen characters, insert it at the front of the list.
 * Read in a duplicate character, delete it from the list and reinsert it at
 * the beginning.
 */
public class Ex_40_MoveToFront<Item> implements Iterable<Item> {
    private Node sentinel;
    private int N;

    private class Node {
        public Item item;
        public Node next;
    }

    public Ex_40_MoveToFront() {
        sentinel = new Node();
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
     * Check if any duplicate item exists. If it does, delete the duplicate.
     * Assum: there will be only one duplicate at most.
     */
    private void deleteDuplicate(Item x) {
        if(isEmpty()) {
            return;
        } 
        Node cur = sentinel;
        while(cur.next != null) {
            if (cur.next.item.equals(x)) {
                cur.next = cur.next.next;
                N -= 1;
                break;
            }
            cur = cur.next;
        }
    }

    /**
     * add an item at the front of the list using move-to-front strategy
     */
    public void insert(Item x) {
        deleteDuplicate(x);

        Node first = new Node();
        first.item = x;

        first.next = sentinel.next; 
        sentinel.next = first; 
        N += 1;
    }


    public Iterator<Item> iterator() {
        return new NoDuplicateStackIterator();
    }

    private class NoDuplicateStackIterator implements Iterator<Item> {
        private Node cur = sentinel.next;

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
        Ex_40_MoveToFront<String> mtf = new Ex_40_MoveToFront<>();
        String unweaving = "Most people are never going to die because they are never going to be born";
        StdOut.println(unweaving);

        for (String s : unweaving.split("\\s+")) {
            mtf.insert(s);
        }

        StdOut.println(mtf.size());
        for (String s : mtf) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
