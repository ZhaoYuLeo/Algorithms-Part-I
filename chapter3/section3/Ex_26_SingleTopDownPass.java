package chapter3.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

/**
 * Develop a modified version of your solution to Exercise 3.3.25 that does not use recursion.
 * Complete all the work splitting and balancing 4-nodes (and balancing 3-nodes) on the way 
 * down the tree,finishing with an insertion at the bottom.
 *
 */
public class Ex_26_SingleTopDownPass<Key extends Comparable<Key>, Value> extends RedBlackT<Key, Value> {

    /** 
     * put key-value pair into the table
     * (remove key from table if value is null)
     */
    public void put(Key key, Value val) {
        if (key == null) {
            return;
        }
        if (root == null) {
            root = new Node(key, val, BLACK, 1);
            return;
        }
        Node h = root, p = null, gp = null, ggp = null;
        while (h != null) {
            int cmp = key.compareTo(h.key);
            if (cmp < 0) {
                if (h.left == null) {
                    h.left = new Node(key, val, RED, 1);
                } 
            } else if (cmp > 0) {
                if (h.right == null) {
                    h.right = new Node(key, val, RED, 1);
                }
            } else {
                h.val = val;
            }
            h.size = size(h.left) + size(h.right) + 1;
            // split 4-nodes into two 2-nodes to make sure that the current node is not
            // a 4 node so we are assured that there will be room to insert the new key
            // at the bottom
            if (!isRed(h) && isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }
            // right-leaning link would only be generated after inserting a new node to the right side of the parent node
            // or flip the colors of the right subtree of the parent node. It won't happen to grandparent by virtue of the
            // invariant.
            if (p != null && isRed(p.right) && !isRed(p.left)) {
                Node n = rotateLeft(p);
                if (gp == null) {
                    root = n;
                } else {
                    if (gp.left == p) { 
                        gp.left = n; 
                    } else {
                        gp.right = n;
                    }
                    gp.size = size(gp.left) + size(gp.right) + 1;
                }
            }
            if (gp != null && isRed(gp.left) && isRed(gp.left.left)) {
                Node m = rotateRight(gp);
                if (ggp == null) {
                    root = m;
                } else {
                    if (ggp.left == gp) {
                        ggp.left = m;
                    } else {
                        ggp.right = m;
                    }
                    ggp.size = size(ggp.left) + size(ggp.right) + 1;
                }
            }
            ggp = gp;
            gp = p;
            p = h;
            if (cmp < 0) {
                h = h.left;
            } else if (cmp > 0) {
                h = h.right;
            } else {
                h = null;
            }
        }
        root.color = BLACK;
    }

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
        Ex_26_SingleTopDownPass<String, Integer> t234 = new Ex_26_SingleTopDownPass<>();
        for (int i = 0; i < a.length; i += 1) {
            t234.put(a[i], i);
        }

        for (String s : t234.keys()) {
            StdOut.println(s + " " + t234.get(s));
        }
        t234.draw(a[a.length - 1]);

    }
}

