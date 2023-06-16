package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Create a new constructor for the linked-list implementation of Stack so that
 * `Stack<Item> t = new Stack<Item>(s);` makes t a reference to a new and 
 * independent copy of the stack s.
 */
public class Ex_42_CopyStack<Item> implements Iterable<Item> {
    private Node first; // top of stack (most recently added node)
    private int N;      // number of items

    private class Node {
        // nested class to define nodes
        Item item;
        Node next;
    }
    

    // A C A
    // B B B
    // C A C
    public Ex_42_CopyStack(Stack<Item> s) {
        Stack<Item> temp = new Stack<>();
        while(s.size() > 0) {
            temp.push(s.pop());
        }
        while(temp.size() > 0) {
            Item item = temp.pop();
            s.push(item);
            this.push(item);
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        // Add item to top of stack.
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N += 1;
    }

    public Item pop() {
        // Remove item from top of stack.
        Item item = first.item;
        first = first.next;
        N -= 1;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> s1 = new Stack<>();
        String sentence = "She was quite happy for me to leave the remainder behind.";
        for (String s : sentence.split("\\s+")) {
            s1.push(s);
        }
        Ex_42_CopyStack<String> s2 = new Ex_42_CopyStack<>(s1);

        StdOut.println("copy:");
        for (String s : s2) {
            StdOut.print(s + " ");
        }
        StdOut.println("\noriginal:");
        for (String s : s1) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
