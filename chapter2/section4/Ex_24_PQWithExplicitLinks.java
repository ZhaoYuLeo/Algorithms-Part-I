package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

/**
 * Implement a priority queue using a heapordered binary tree, but use a triply linked structure instead of an array.
 * You will need three links per node: two to traverse down the tree and one to traverse up the tree. Your implementation
 * should guarantee logarithmic running time per operation, even if no maximum priority-queue size is known ahead of time.
 */
public class Ex_24_PQWithExplicitLinks<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private int N = 0;      // the number of elements in the pq
    public Node root;

    private class Node {
        Key key;

        Node parent;    // Not used in this implementation. TODO: Maybe you can implement the pq in a different way with swim 
        Node left;
        Node right;

        int height;     // keep the shape of the tree(fully inserted)

        public Node(Key key) {
            this.key = key;
        }
    }


    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        Node inserted = new Node(key);
        root = insert(root, inserted);
        N += 1;
    }

    /**
     * Insert node into the tree and return the root node
     */
    private Node insert(Node node, Node inserted) {
        if (node == null) {
            return inserted;
        }

        if (less(node, inserted)) {
            Key temp = inserted.key;
            inserted.key = node.key;
            node.key = temp;
        }

        // alternate insert 
        if (rightShorter(node)) {
            Node n = insert(node.right, inserted);
            n.parent = node;
            node.right = n;
        } else {
            Node n = insert(node.left, inserted);
            n.parent = node;
            node.left = n;
        }

        node.height = 1 + subHeight(node);
        return node;
    }

    /**
     * Get the height of the tree
     */
    private int getHeight(Node n) {
        if (n == null) {
            return 0;
        }
        return n.height;
    }

    /**
     * Return the max height of subtree of n
     */
    private int subHeight(Node n) {
        if (n == null) {
            return 0;
        }
        return Math.max(getHeight(n.left), getHeight(n.right));
    }

    /**
     * Return the child with smaller height or the left child
     * which will result in alternating insertions into the subtrees
     */
    private boolean rightShorter(Node n) {
        if (n == null) {
            return false;
        }
        return getHeight(n.right) < getHeight(n.left);
    }

    @Override
    public Key delMax() {
        if (isEmpty()) {
            return null;
        }
        Key max = root.key;
        N -= 1;
        root = delMax(root);
        return max;
    }

    /**
     * Delete the root of the binary tree and build a new max-oriented one
     */
    private Node delMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        if (less(node.left, node.right)) {
            Node sub = delMax(node.right);

            node.left.parent = node.right;
            node.right.left = node.left;
            node.right.right = sub;
            node.right.parent = node.parent;
            return node.right;
        } else {
            Node sub = delMax(node.left);

            node.right.parent = node.left;
            node.left.right = node.right;
            node.left.left = sub;
            node.left.parent = node.parent;
            return node.left;
        }
    }

    private boolean less(Node v, Node w) {
        return v.key.compareTo(w.key) < 0;
    }


    public static void main(String[] args) {
        // Print the bottom M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        Ex_24_PQWithExplicitLinks<Transaction> pq = new Ex_24_PQWithExplicitLinks<>();
        while (StdIn.hasNextLine()) {
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) {
                pq.delMax();    // Remove maximum if M+1 entries on the PQ.
            }
        }

        Stack<Transaction> stack = new Stack<Transaction>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMax());
        }
        for (Transaction t : stack) {
            StdOut.println(t);
        }
    }

}

