package chapter3.section3;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;


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

    /**
     * Get the value of the given key.
     */
    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        Node n = get(root, key);
        return n == null ? null : n.val;
    }

    /**
     * Return the node associated with the given key; null if not exists.
     */
    private Node get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    /**
     * Return the smallest key in the tree 
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("call min() with empty symbol table");
        }
        return min(root).key;
    }

    /**
     * Return the node associated with the smallest key rooted at node
     */
    private Node min(Node node) {
        // assert node != null;
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * Return the largest key in the tree
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("call max() with empty symbol table");
        }
        return max(root).key;
    }

    /**
     * Return the node associated with the largest key rooted at node
     */
    private Node max(Node node) {
        // assert node != null
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * Returns all keys in the tree in ascending order
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    /**
     * Returns all keys in the tree between lo and hi in ascending order
     */
    private Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * Add all keys between lo and hi in the subtree rooted at node.
     */
    private void keys(Node node, Queue<Key> queue, Key lo, Key hi) {
        if (node == null) {
            return;
        }
        int cmpLo = lo.compareTo(node.key);
        int cmpHi = hi.compareTo(node.key);
        if (cmpLo < 0) {
            keys(node.left, queue, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            queue.enqueue(node.key);
        }
        if (cmpHi > 0) {
            keys(node.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        StdOut.println();
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println();
    }
}

