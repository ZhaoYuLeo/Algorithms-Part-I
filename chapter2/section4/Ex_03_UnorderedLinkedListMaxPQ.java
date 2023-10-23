package chapter2.section4;

import edu.princeton.cs.algs4.StdOut;

public class Ex_03_UnorderedLinkedListMaxPQ<Key extends Comparable<Key>> implements MaxPQ<Key> {
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
    public Ex_03_UnorderedLinkedListMaxPQ() {
        head = new Node(null, null);
        N = 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void insert(Key key) {
        // Insert element behind the head sentinel
        head.next = new Node(key, head.next);
        N += 1;
    }

    @Override
    public Key delMax() {
        // Find the largest one and delete it.
        // Since this is a single linked list, we are detaching the cherry from the stems.
        if (head.next == null) {
            return null;
        }
        Node maxPred = head;
        for (Node node = head; node.next != null; node = node.next) {
            if (less(maxPred.next.key, node.next.key)) {
                maxPred = node;
            }
        }
        Key key = maxPred.next.key;
        maxPred.next = maxPred.next.next;
        N -= 1;
        return key;
    }

    private boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Ex_03_UnorderedLinkedListMaxPQ<Integer> pq = new Ex_03_UnorderedLinkedListMaxPQ<>();
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

