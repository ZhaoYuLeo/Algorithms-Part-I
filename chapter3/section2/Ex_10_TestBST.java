package chapter3.section2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

/**
 * Write a test client TestBST.java for use in testing the implementations of
 * min(), max(), floor(), ceiling(), select(), rank(), delete(), deleteMin(),
 * deleteMax(), and keys() that are given in the text. Start with the standard
 * indexing client given on page 370. Add code to take additional command-line
 * arguments, as approprivate.
 */
public class Ex_10_TestBST {

    public static void main(String[] args) {
        String text = "S E A R C H E X A M P L E";
        String[] keys = text.split("\\s+");

        BST<String, Integer> bst = new BST<>();
        for (int i = 0; i < keys.length; i += 1) {
            bst.put(keys[i], i);
        }

        StdOut.println("Print keys in level order");
        StdOut.println("------------------------------------");
        for(String s : bst.levelOrder()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();

        // keys()
        StdOut.println("Testing keys()");
        StdOut.println("------------------------------------");
        for(String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();

        // min()
        StdOut.println("min = " + bst.min());
        // max()
        StdOut.println("max = " + bst.max());
        StdOut.println();

        // select()
        StdOut.println("Testing select()");
        StdOut.println("------------------------------------");
        for(int i = 0; i < bst.size(); i += 1) {
            StdOut.println(i + " " + bst.select(i));
        }
        StdOut.println();

        // floor(), ceiling(), rank()
        StdOut.println("Testing rank floor ceiling");
        StdOut.println("------------------------------------");
        for (char i = 'A'; i <= 'Z'; i += 1) {
            String s = i + "";
            StdOut.printf("%2s %4d %4s %4s\n", s, bst.rank(s), bst.floor(s), bst.ceiling(s));
        }
        StdOut.println();

        // deleteMin()
        for (int i = 0; i < bst.size() / 3; i += 1) {
            bst.deleteMin();
        }
        StdOut.println("After deleting the smallest " + bst.size() / 3 + " keys");
        StdOut.println("------------------------------------");
        for(String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();

        // deleteMax()
        for (int i = 0; i < bst.size() / 3; i += 1) {
            bst.deleteMax();
        }
        StdOut.println("After deleting the biggest " + bst.size() / 3 + " keys");
        StdOut.println("------------------------------------");
        for(String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();

        // delete()
        while (bst.size() != 0) {
            bst.delete(bst.select(bst.size() / 3));
        }
        StdOut.println("After deleting the remaining keys");
        StdOut.println("------------------------------------");
        Queue<Integer> a = new Queue<>();
        for(String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();

        StdOut.println("After adding back N keys");
        StdOut.println("------------------------------------");
        for (int i = 0; i < keys.length; i += 1) {
            bst.put(keys[i], i);
        }
        for(String s : bst.keys()) {
            StdOut.println(s + " " + bst.get(s));
        }
        StdOut.println();
    }
}
