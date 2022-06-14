/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);

        RandomizedQueue<String> rdQueue = new RandomizedQueue<String>();
        StdRandom.uniform(size);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rdQueue.enqueue(s);
        }
        for (int i = 0; i < size; i++) {
            StdOut.println(rdQueue.dequeue());
        }
    }
}
