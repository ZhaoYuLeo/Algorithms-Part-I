package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Create a new constructor so that `Queue<Item> r = new Queue<Item>(q);`
 * makes r a reference to a new and independent copy of the queue q.
 */
public class Ex_41_CopyQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        public Item item;
        public Node next;
    }

    //   -----------
    //   ||        |
    //   *-****>   *****>  
    public Ex_41_CopyQueue(Queue<Item> q) {
        int size = q.size();
        N = 0;
        for (int i = 0; i < size; i += 1) {
            Item e = q.dequeue();
            q.enqueue(e);
            this.enqueue(e);
        }
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
     * add item to the end of the list.
     */
    public void enqueue(Item x) {
        Node oldLast = last;
        last = new Node();
        last.item = x;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        N += 1;
    }

    /**
     * remove item from the beginning of the list.
     */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N -= 1;
        return item;
    }


    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
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
        Queue<Integer> q1 = new Queue<>();
        for (int i = 0; i < 10; i += 1) {
            q1.enqueue(i);
        }
        Ex_41_CopyQueue<Integer> q2 = new Ex_41_CopyQueue<>(q1);

        StdOut.println("The original queue");
        for (Integer s : q1) {
            StdOut.print(s + " ");
        }
        StdOut.println("\nThe copy one");
        for (Integer s : q1) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
