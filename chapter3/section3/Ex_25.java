package chapter3.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

/**
 * Top-down 2-3-4 trees. Develop an implementation of the basic symbol-table API
 * that uses balanced 2-3-4 trees as the underlying data structure, using the 
 * red-black representation and the insertion method described in the text, where
 * 4-nodes are split by flipping colors on the way down the search path and balancing
 * on the way up.
 */
public class Ex_25<Key extends Comparable<Key>, Value> extends RedBlackT<Key, Value> {

    /**
     * Return true if the current node is red, false otherwise.
     */
    protected boolean isRed(Node n) {
        if (n == null) {
            return false;
        }
        return n.color == RED;
    }

    /** 
     * put key-value pair into the table
     * (remove key from table if value is null)
     */
    public void put(Key key, Value val) {
        if (key == null) {
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node n, Key key, Value val) {
        if (n == null) {
            return new Node(key, val, RED, 1);
        }
        // split 4-nodes into two 2-nodes to make sure that the current node is not
        // a 4 node so we are assured that there will be room to insert the new key
        // at the bottom
        if (!isRed(n) && isRed(n.left) && isRed(n.right)) {
            flipColors(n);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, val);
        } else if (cmp > 0) {
            n.right = put(n.right, key, val);
        } else {
            n.val = val;
        }
        // fix-up any right-leaning links doing transformations on the way up the 
        // path to balance any 4-nodes that may have been created.
        if (isRed(n.right) && !isRed(n.left)) {
            n = rotateLeft(n);
        }
        if (isRed(n.left) && isRed(n.left.left)) {
            n = rotateRight(n);
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    /**
     * Make a right-leaning link lean to the left.
     *  |           |
     *  E           S
     * / \      →  / \
     *    S*      E*
     *   / \     / \
     */
    private Node rotateLeft(Node h) {
        assert (h != null) && !isRed(h.left) && isRed(h.right);
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
     * Make a left-leaning link lean to the right.
     *    |         |
     *    S         E
     *   / \    →  / \
     *  E*            S*
     * / \           / \
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
     * Flip the colors of a node and its two children.
     *    |            |
     *    o            o*
     *   / \          / \
     *  o*  o*   →   o   o
     * / \ / \      / \ / \
     */
    private void flipColors(Node h) {
        assert (h != null) && (h.left != null) && (h.right != null);
        assert !isRed(h) && isRed(h.left) && isRed(h.right);
        h.color = !h.color;
        h.left.color =!h.left.color;
        h.right.color = !h.right.color;
    }

    /** 
     * value paired with key (null if key is absent)
     */
    public Value get(Key key) {
        if (key == null) {
            return null;
        }
        Node n = get(root, key);
        return n == null ? null : n.val;
    }

    private Node get(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            return get(n.left, key);
        } else if (cmp > 0) {
            return get(n.right, key);
        } else {
            return n;
        }
    }

    /**
     * Return the smallest key in the tree.
     */
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    /**
     * Return the node associated with the smallest key rooted at node
     */
    public Node min(Node n) {
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    /**
     * Return the largest key in the tree.
     */
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    /**
     * Return the node associated with the smallest key rooted at node
     */
    public Node max(Node n) {
        if (n.right == null) {
            return n;
        }
        return max(n.right);
    }

    /**
     * Return all the keys in the table in ascending order
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * Add all keys between lo and hi in the subtree rooted at n.
     */
    private void keys(Node n, Queue<Key> queue, Key lo, Key hi) {
        if (n == null) {
            return;
        }
        int cmpLo = lo.compareTo(n.key);
        int cmpHi = hi.compareTo(n.key);
        if (cmpLo < 0) {
            keys(n.left, queue, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            queue.enqueue(n.key);
        }
        if (cmpHi > 0) {
            keys(n.right, queue, lo, hi);
        }
    }

    public static void main(String[] args) {
        String[] a = {"S", "E", "A", "R", "C", "H", "E", "X"}; // internalPath 12
        //String[] a = {"A", "C", "E", "H", "L", "M", "P", "R", "S", "X"}; // internalPath 45
        Ex_25<String, Integer> t234 = new Ex_25<>();
        for (int i = 0; i < a.length; i += 1) {
            t234.put(a[i], i);
        }

        for (String s : t234.keys()) {
            StdOut.println(s + " " + t234.get(s));
        }
        t234.draw(a[a.length - 1]);

    }
}

