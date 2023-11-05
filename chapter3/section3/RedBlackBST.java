package chapter3.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * A left-leaning red-black BST
 *       o
 *     /   \
 *    o*    o
 *   / \   / \
 *  o   o
 * / \ / \
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;      // root of the BST

    private class Node {
        private Key key;            // key
        private Value val;          // associated data
        private Node left, right;    // links to left and right subtrees
        private boolean color;      // color of parent link
        private int size;           // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    /**
     * Initialization
     */
    public RedBlackBST() {
    }

    /**
     * Return the number of key-value pairs in this symbol tables
     */
    public int size() {
        return size(root);
    }

    /**
     * Number of node in subtree rooted at x; 0 of x is null
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /**
     * Is this symbol table empty?
     */
    public boolean isEmpty() {
        return root == null; // or size() == 0;
    }

    /**
     * Is node x red; false if x is null ?
     */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * Make a right-leaning link lean to the left
     * o          o
     *  \    →   /
     *   o*     o*
     */
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right) && !isRed(h.left);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * Make a left-leaning link lean to the right
     *   o       o
     *  /    →    \
     * o*          o*
     */
    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left) && !isRed(h.right);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * Flip the colors of a node and its two children
     *    |              |
     *    o              o*
     *   / \            / \
     *  o*  o*   →     o   o
     * / \ / \        / \ / \
     */
    private void flipColors(Node h) {
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h)) && isRed(h.left) && isRed(h.right);
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * Insert key-value pair into the symbol table
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * Insert the key-value pair in the subtree rooted at h
     */
    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, RED, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (!isRed(h.right) && isRed(h.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println();
        // for (String s : st.keys()) {
        //     StdOut.println(s + " " + st.get(s));
        // }
        // StdOut.println();
    }
}

