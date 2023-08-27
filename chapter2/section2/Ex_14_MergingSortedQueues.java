package chapter2.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a static method that takes two queues of sorted items as arguments and returns
 * a queue that results from merging the queues into sorted order.
 */
public class Ex_14_MergingSortedQueues {
    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        assert isSorted(q1);
        assert isSorted(q2);

        Queue<T> merged = new Queue<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (less(q1.peek(), q2.peek())) {
                merged.enqueue(q1.dequeue());
            } else {
                merged.enqueue(q2.dequeue());
            }
        }

        while (!q1.isEmpty()) {
            merged.enqueue(q1.dequeue());
        }

        while (!q2.isEmpty()) {
            merged.enqueue(q2.dequeue());
        }

        return merged;
    }

    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public static <T extends Comparable<T>> boolean isSorted(Queue<T> q) {
        T prev = q.peek(); 
        for (T e : q) {
            if (less(e, prev)) {
                return false;
            }
            prev = e;
        }
        return true;
    }
    
    public static <T extends Comparable<T>> void show(Queue<T> q) {
        for (T e : q) {
            StdOut.print(e + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Queue<Integer> a = new Queue<>();
        for (Integer i = 0; i < 10; i += 1) {
            a.enqueue(i * 2);
        }
        show(a);
        StdOut.println(isSorted(a));

        Queue<Integer> b = new Queue<>();
        for (Integer i = 0; i < 10; i += 1) {
            b.enqueue(i * 2 + 1);
        }
        show(b);

        Queue<Integer> c = merge(a, b);
        show(c);

    }
}
