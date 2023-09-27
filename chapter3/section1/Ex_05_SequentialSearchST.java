package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement size(), delete(), and keys() for SequentialSearchST.
 */
public class Ex_05_SequentialSearchST<Key, Value> implements ST<Key, Value> {
    private Node first;     // first node
    int N = 0;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;               // search hit
            }
        }
        return null;                        // search miss
    }

    @Override
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;                // search hit: update val.
                return;
            }
        }
        first = new Node(key, val, first);  // search miss: add new node.
        N += 1;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void delete(Key key) {
        if (key == null || isEmpty()) {
            return;
        }

        if (first.key.equals(key)) {
            first = first.next;
            N -= 1;
            return;
        }

        for (Node node = first; node.next != null; node = node.next) {
            if (node.next.key.equals(key)) {
                node.next = node.next.next;
                N -= 1;
                return;
            }
        }
    }

    @Override
    public Iterable<Key> keys() {
        Queue<Key> keys = new Queue<>();

        for (Node node = first; node != null; node = node.next) {
            keys.enqueue(node.key);
        }

        return keys;
    }


    public static void main(String[] args) {
        Ex_05_SequentialSearchST<String, Integer> st = new Ex_05_SequentialSearchST<>();
        String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < str.length; i += 1) {
            st.put(str[i], i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("The size is: " + st.size());

        for (int i = 0; i < 3; i += 1) {
            String key = str[StdRandom.uniform(str.length)];
            st.delete(key);
            StdOut.println("Randomly delete key: " + key);
        }
        StdOut.println("The size is: " + st.size());

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
