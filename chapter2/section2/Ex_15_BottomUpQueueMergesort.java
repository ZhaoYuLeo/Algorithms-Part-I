package chapter2.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Develop a bottom-up mergesort implementation based on the following approach: Given N items,
 * create N queues, each containing one of the items. Create a queue of the N queues. Then
 * repeatedly apply the merging operation of EXERCISE 2.2.14 to the first two queues and reinsert
 * the merged queue at the end. Repeat until the queue of queues contains only one queue.
 */
public class Ex_15_BottomUpQueueMergesort {
    public static <T extends Comparable<T>> Queue<T> bottomUp(Queue<Queue<T>> items) {
         while (items.size() > 1) {
             items.enqueue(Ex_14_MergingSortedQueues.merge(items.dequeue(), items.dequeue()));
         }
        return items.dequeue();
    }

    public static <T extends Comparable<T>> void show(Queue<T> q) {
        for (T e : q) {
            StdOut.print(e + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        int N = 20;
        Queue<Queue<Integer>> a = new Queue<>();
        for (int i = 0; i < N; i += 1) {
            Queue<Integer> item = new Queue<>();
            item.enqueue(StdRandom.uniform(N));
            a.enqueue(item);
        }
        Queue<Integer> result = bottomUp(a);
        show(result);
    }
}
