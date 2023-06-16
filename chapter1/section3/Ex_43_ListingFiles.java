package chapter1.section3;

import java.io.File;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Prints out all of the files contained in the folder, with the contents of
 * each folder recursively listed (indented) under that folder's name.
 */
public class Ex_43_ListingFiles {
    private static class Node {
        File item;
        Queue<Node> subs;
    }

    // tree
    public static Node fileLists(File file) {
        if (!file.exists()) {
            return null; 
        }
        Node n = new Node();
        n.item = file;

        if (file.isFile()) {
            n.subs = null;
        }

        if (file.isDirectory()) {
            Queue<Node> q = new Queue<>();
            File[] subFiles = file.listFiles();
            for (File f : subFiles) {
                q.enqueue(fileLists(f));
            }
            n.subs = q;
        }
        return n;
    }

    // print tree
    public static void printFileLists(Node n, String divider) {
        if (n == null) {
            return;
        }
        StdOut.println(n.item);
        if (n.subs == null) { // you have to check before iterator visits subs.
            return;
        }
        for (Node sn : n.subs) {
            StdOut.print(divider);
            printFileLists(sn, divider + divider);
        }
    }

    public static void main(String[] args) {
        String fileName = args[0];
        File file = new File(fileName);
        printFileLists(fileLists(file), "    ");
    }
}
