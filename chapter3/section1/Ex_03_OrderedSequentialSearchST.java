package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a symbol-table implementation OrderedSequentialSearchST that uses an ordered linked list as the
 * underlying data structure to implement our ordered symbol-table APT.
 */
public class Ex_03_OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
    private Node sentinel;     // also the one with the smallest key
    private int N = 0;      // size

    private class Node {
        Key key;
        Value val;
        Node pre;
        Node next;

        public Node() {}

        public Node(Key key, Value val, Node pre, Node next) {
            this.key = key;
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public Ex_03_OrderedSequentialSearchST() {
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void put(Key key, Value val) {
        // keep the sequences ordered
        Node node = search(key);    // return the node with the key which is equal to or less than the given key.

        if (node.key != null && node.key.compareTo(key) == 0) {
            node.val = val;
        } else {
            Node newNode = new Node(key, val, node, node.next);
            node.next.pre = newNode;
            node.next = newNode;
            N += 1;
        }
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }

        Node node = search(key);

        if (node.key != null && node.key.compareTo(key) == 0) {
            return node.val;
        }

        return null;
    }

    /** return the node with the key which is equal to or less than the given key */
    private Node search(Key key) {
        // binary search cannot be implemented with only one chain
        Node node = sentinel.next;

        while (node != sentinel) {
            if (node.key.compareTo(key) == 0) { 
                return node;
            } 
            if (node.key.compareTo(key) > 0) { 
                return node.pre;
            } 
            node = node.next;
        }
        return node.pre;
    }

    public void delete(Key key) {
        if (key == null) {
            return;
        }
        Node node = search(key);
        if (node.key != null && node.key.compareTo(key) == 0) {
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node = null;
            N -= 1;
        }
    }

    public boolean contains(Key key) {
        Node node = search(key);
        if (node.key != null && node.key.compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    /** smallest key */
    public Key min() {
        return sentinel.next.key;
    }

    /** largest key */
    public Key max() {
        return sentinel.pre.key;
    }

    /** largest key less than or equal to key */
    public Key floor(Key key) {
        Node node = search(key);
        return node.key;
    }

    /** smallest key greater than or equal to key */
    public Key ceiling(Key key) {
        Node node = search(key);
        if (node.key != null && node.key.compareTo(key) == 0) {
            return node.key;
        }
        return node.next.key;
    }

    /** number of keys less than key */
    public int rank(Key key) {
        int i = 0;
        for (Node node = sentinel.next; node != sentinel && node.key.compareTo(key) < 0; node = node.next) {
            i += 1;
        }
        return i;
    }

    /** key of rank k */
    public Key select(int k) {
        Node node = sentinel;
        for (int i = 0; i < k; i += 1) {
            node = node.next;
        }
        return node.key;
    }

    /** delete smallest key */
    public void deleteMin() {
        Node min= sentinel.next;
        if (min!= sentinel) {
            min.pre.next = min.next;
            min.next.pre = min.pre;
            min = null;
            N -= 1;
        }
    }

    /** delete largest key */
    public void deleteMax() {
        Node max = sentinel.pre;
        if (max != sentinel) {
            max.pre.next = max.next;
            max.next.pre = max.pre;
            max = null;
            N -= 1;
        }
    }

    /** number of keys in [lo..hi] */
    public int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        }
        int count = 0;
        for (Node node = sentinel.next; node != sentinel; node = node.next) {
            if (node.key.compareTo(lo) >= 0 && node.key.compareTo(hi) <= 0) {
                count += 1;
            }
        }
        return count;
    }

    /** keys in [lo..hi], in sorted order */
    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> keys = new Queue<>();
        if (hi.compareTo(lo) < 0) {
            return keys;
        }

        Node node = search(lo); 
        if (node.key.compareTo(lo) == 0) {
            keys.enqueue(node.key);
            node = node.next;
        }

        while (node != sentinel && node.key.compareTo(hi) <= 0) {
            keys.enqueue(node.key);
            node = node.next;
        }
        return keys;
    }

    /** all keys in the table, in sorted order */
    Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        Ex_03_OrderedSequentialSearchST<String, Integer> ost = new Ex_03_OrderedSequentialSearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            ost.put(key, i);
        }

        for (String s : ost.keys()) {
            StdOut.println(s + " " + ost.get(s));
        }

        StdOut.println("size: " + ost.size());

        StdOut.println("delete min and max");
        ost.deleteMin();
        ost.deleteMax();

        for (String s : ost.keys()) {
            StdOut.println(s + " " + ost.get(s));
        }
    }
}
