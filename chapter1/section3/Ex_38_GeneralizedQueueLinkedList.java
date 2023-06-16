package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

/**
 * Delete kth element. Linked-list implementation.
 */
public class Ex_38_GeneralizedQueueLinkedList<Item> implements Iterable<Item> {
    private Node sentinel;
    private int N;

    private class Node {
        public Item item;
        public Node next;
        public Node prev;
    }

    public Ex_38_GeneralizedQueueLinkedList() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev= sentinel;
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
     * add an item
     */
    public void insert(Item x) {
        Node oldTail = sentinel.prev;

        Node tail = new Node();
        tail.item = x;

        tail.prev = oldTail; 
        oldTail.next = tail;
        tail.next = sentinel;
        sentinel.prev= tail; 
        N += 1;
    }

    /**
     * delete and return the kth least recently inserted item
     * 0th...kth
     */
    public Item delete(int k) {
        if (isEmpty() || k < 0 || k >= N) {
            throw new NoSuchElementException();
        }
        Node kth = sentinel;
        while (k > 0) {
            kth = kth.next;
            k -= 1;
        }
        Item item = kth.next.item;
        kth.next = null;
        N -= 1;
        return item;
    }


    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        private Node cur = sentinel.next;

        public boolean hasNext() {
            return cur != sentinel;
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
        Ex_38_GeneralizedQueueLinkedList<Integer> deque = new Ex_38_GeneralizedQueueLinkedList<>();
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
