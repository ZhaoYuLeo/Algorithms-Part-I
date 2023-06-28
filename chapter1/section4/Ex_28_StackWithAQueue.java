package chapter1.section4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

/**
 * Implement a stack with a single queue so that each stack operations
 * takes a linear number of queue operations.
 */
public class Ex_28_StackWithAQueue<Item> {
    private Queue<Item> q;

    public Ex_28_StackWithAQueue() {
        q = new Queue<>();
    }

    public boolean isEmpty() {
        return q.isEmpty();
    }

    public int size() {
        return q.size();
    }

    public void push(Item item) {
        q.enqueue(item);
    }

    public Item pop() {
        if (isEmpty()) {
            throw new RuntimeException("no element to dequeue.");
        }
        int count = q.size();
        while(count > 1) {
            count -= 1;
            q.enqueue(q.dequeue());
        }
        return q.dequeue();
    }
    
    public static void main(String[] args) {
        Ex_28_StackWithAQueue<String> s = new Ex_28_StackWithAQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.push(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
