package chapter1.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement a queue with two stacks so that each queue operation takes
 * a constant amortized number of stack operations.
 */
public class Ex_27_QueueWithTwoStacks<Item> /* implements Iterable<Item> */ {
    private Stack<Item> tail;
    private Stack<Item> head;

    public Ex_27_QueueWithTwoStacks() {
        tail = new Stack<>();
        head = new Stack<>();
    } 
    
    public boolean isEmpty() {
        return tail.isEmpty() && head.isEmpty();
    }

    public int size() {
        return tail.size() + head.size();
    }

    public void enqueue(Item item) {
        tail.push(item);
    }

    /**
     * constant (amortized)
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("no element to dequeue.");
        }
        if (head.isEmpty()) {
            moveToHead();
        }
        return head.pop();
    }

    /**
     * Pop out all items in tail and push them into head.
     * O(k) after k operation
     */
    private void moveToHead() {
        while (!tail.isEmpty()) {
            head.push(tail.pop());
        }
    }

    // public Iterator<Item> iterator() {
    //     return new QueueIterator();
    // }

    // private class QueueIterator implements Iterator<Item> {
    //     public boolean hasNext()

    //     public void remove() {//unsupported operation}

    //     public void next() { }
    // }
    public static void main(String[] args) {
        Ex_27_QueueWithTwoStacks<String> q = new Ex_27_QueueWithTwoStacks<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                StdOut.print(q.dequeue() + " ");
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
