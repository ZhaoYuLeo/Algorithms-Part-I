package chapter3.section1;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {
    private Node first;     // first node

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
    }


    public static void main(String[] args) {
    }
}
