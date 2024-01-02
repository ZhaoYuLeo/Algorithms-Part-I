package chapter3.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Queue;

import chapter3.section1.ST;
/**
 * 2-3 trees without balance restriction. Develop an implementation of the basic
 * symbol-table API that uses 2-3 trees that are not necessarily balanced as the
 * underlying data structure. Allow 3-nodes to lean either way. Hook the new node
 * onto the bottom with a black link when inserting into a 3-node at the bottom.
 * Run experiments to develop a hypothesis estimating the average path length in 
 * a tree built from N random insertions.
 */
public class Ex_23<Key extends Comparable<Key>, Value> extends RedBlackT<Key, Value> {

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
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, val);
        } else if (cmp > 0) {
            n.right = put(n.right, key, val);
        } else {
            n.val = val;
        }
        if (isRed(n) && isRed(n.left)) {
            n.left.color = BLACK;
        }
        if (isRed(n) && isRed(n.right)) {
            n.right.color = BLACK;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
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
        // String[] a = {"S", "E", "A", "R", "C", "H", "E", "X"}; // internalPath 12
        String[] a = {"A", "C", "E", "H", "L", "M", "P", "R", "S", "X"}; // internalPath 45
        Ex_23<String, Integer> RBT = new Ex_23<>();
        for (int i = 0; i < a.length; i += 1) {
            RBT.put(a[i], i);
        }

        for (String s : RBT.keys()) {
            StdOut.println(s + " " + RBT.get(s));
        }
        RBT.draw(a[a.length - 1]);


        // Run experiments to develop a hypothesis estimating the average path 
        // length in a tree built from N random insertions.
        for (int N = 100; N < 1000000; N *= 10) {
            int times = 20;
            int t23AvgPath = 0;
            int rbtAvgPath = 0;
            RedBlackBST.setAnimate(false);
            for (int t = 0; t < times; t += 1) {
                Ex_23<Integer, Integer> t23 = new Ex_23<>();
                RedBlackBST<Integer, Integer> rbt = new RedBlackBST<>();
                for (int i = 0; i < N; i += 1) {
                    int key = StdRandom.uniform(N);
                    t23.put(key, i);
                    rbt.put(key, i);
                }
                t23AvgPath += t23.avgPath();
                rbtAvgPath += rbt.avgPath();
            }
            StdOut.println("Average path for size " + N + ": 2-3(no balance) " + t23AvgPath / times + " r-b " + rbtAvgPath / times);
        }
        // Average path for size 100: 2-3(no balance) 6 r-b 5
        // Average path for size 1000: 2-3(no balance) 10 r-b 8
        // Average path for size 10000: 2-3(no balance) 15 r-b 12
        // Average path for size 100000: 2-3(no balance) 19 r-b 15
    }
}

