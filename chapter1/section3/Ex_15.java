package chapter1.section3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Taks a command-line argument k and prints the kth from the last string
 * found on standard input. 1th, 2th,...,nth.
 * Assume: the standard input has k or more strings.
 */
public class Ex_15 {
    public static String lastKth(Queue<String> queue, int k) {
        // if no element exists in queue, return "".
        String item = "";

        int sum = queue.size();
        for (String s : queue) {
            if ((sum - k) == 0) {
                item = s;
                break;
            }
            k += 1;
        }
        return item;
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Queue<String> queue = new Queue<>();
        while(!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        StdOut.println("The kth from the last string is " + lastKth(queue, k));
    }
}
