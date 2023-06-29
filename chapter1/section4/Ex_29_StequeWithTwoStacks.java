package chapter1.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a steque with two stacks so that each steque operation.
 * Takes a constant amortized number of stack operations.
 */
public class Ex_29_StequeWithTwoStacks<Item> {
    Stack<Item> head;
    Stack<Item> tail;

    public Ex_29_StequeWithTwoStacks() {
        head = new Stack<>();
        tail = new Stack<>();
    }

    public boolean isEmpty() {
        return head.isEmpty() && tail.isEmpty();
    }

    public int size() {
        return head.size() + tail.size();
    }

    public void push(Item item) {
        head.push(item);
    }

    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("no element to pop out.");
        }
        if (head.isEmpty()) {
            moveToHead();
        }
        return head.pop();

    }

    private void moveToHead() {
        while(!tail.isEmpty()) {
            head.push(tail.pop());
        }
    }

    public void enqueue(Item item) {
        tail.push(item);
    }

    public static void main(String[] args) {
        Ex_29_StequeWithTwoStacks<String> steque = new Ex_29_StequeWithTwoStacks<>();
        steque.push(",");
        steque.enqueue("the");
        steque.push("Hello");
        steque.enqueue("world");
        while(!steque.isEmpty()) {
            StdOut.println(steque.pop());
        }
    }
}
