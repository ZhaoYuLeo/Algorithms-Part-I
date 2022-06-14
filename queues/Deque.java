/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;

    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null can't be inserted.");
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = null;

        if (oldFirst == null) {
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null can't be inserted.");
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }


    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("Sorry. This operation is not supported.");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("There are no more items to return.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        // construct
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println(
                "The deque is empty: " + deque.isEmpty() + "; \nSize is: " + deque.size());
        StdOut.println("Please enter one of these follow operations you want to perform: \n"
                               + "af, al, rf, rl, pr, sz, ep");
        while (!StdIn.isEmpty()) {
            String command = StdIn.readString();
            switch (command) {
                case "af":
                    StdOut.println("Please enter first item: ");
                    deque.addFirst(StdIn.readInt());
                    break;
                case "al":
                    StdOut.println("Please enter last item: ");
                    deque.addLast(StdIn.readInt());
                    break;
                case "rf":
                    Integer firstItem = deque.removeFirst();
                    StdOut.println("First item " + firstItem + " is removed from this deque;");
                    break;
                case "rl":
                    Integer lastItem = deque.removeLast();
                    StdOut.println("Last item " + lastItem + " is removed from this deque;");
                    break;
                case "pr":
                    int index = 0;
                    for (Integer d : deque) {
                        StdOut.println(index++ + ": " + d);
                    }
                    break;
                case "sz":
                    StdOut.println("size is: " + deque.size());
                    break;
                case "ep":
                    StdOut.println("The deque is empty: " + deque.isEmpty());
                    break;
                default:
                    StdOut.println("No operation is choosen.");
                    break;
            }
        }
    }
}
