package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

public class Ex_03_OrderedLinkedListMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
    private int N;      // the number of keys is the priority queue
    private Node head;  // the head sentinel of the linked list

    private class Node {
        Key key;
        Node next;

        public Node(Key key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    /**
     * Create a priority queue
     */
    public Ex_03_OrderedLinkedListMaxPQ() {
        head = new Node(null, null);
        N = 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        // sentinel -> maximum -> ... -> key -> ... -> null
        Node node = head;
        while (node.next != null && less(key, node.next.key)) {
            node = node.next;
        }
        node.next = new Node(key, node.next);
        N += 1;
    }

    @Override
    public Key delMax() {
        if (head.next == null) {
            return null;
        }
        Key key = head.next.key;
        head.next = head.next.next;
        N -= 1;
        return key;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Ex_03_OrderedLinkedListMaxPQ<Integer> pq = new Ex_03_OrderedLinkedListMaxPQ<>();
        pq.insert(18);
        pq.insert(2);
        pq.insert(20);
        pq.insert(12);
        pq.insert(16);
        pq.insert(26);
        pq.insert(16);
        pq.insert(17);
        while (!pq.isEmpty()) {
            StdOut.println(pq.delMax());
        }
    }

}

