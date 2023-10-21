package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Support an extended API ThreadedST that supports the following additional operations in constant time:
 * Key next(Key key) key that follows key (null if key is the maximum)
 * Key prev(Key key) key that precedes key (null if key is the minimum)
 * To do so, add fields pred and succ to Node that contain links to the predecessor and successor nodes,
 * and modify put(), deleteMin(), deleteMax(), and deleted() to maintain these fields.
 */
public class Ex_34_Threading<Key extends Comparable<Key>, Value> {
    private Node root;              // root of BST

    private class Node {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private Node prev, succ;    // links to the predecessor and successor nodes
        private int N;              // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    /** returns key that follows key (null if key is the maximum) */
    public Key next(Key key) {
        // not in constant time for I had to find the node with the given key
        Node node = get(root, key);
        if (node != null && node.succ != null) {
            return node.succ.key;
        }
        return null;
    }

    public Key prev(Key key) {
        Node node = get(root, key);
        if (node != null && node.prev!= null) {
            return node.prev.key;
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key) {
        Node n = get(root, key);
        if (n == null) {
            return null;
        }
        return n.val;
    }

    private Node get(Node x, Key key) {
        // Return the node associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val, null, null);
    }

    private Node put(Node x, Key key, Value val, Node prev, Node succ) {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.

        // The predecessor and successor nodes are the neighbors of the current node when mapping the tree into an axis.
        // For any node, the pred and succ are unique. Two different nodes won't point to the same pred node or succ
        // (thanks to no same key).
        // In the following process, we are spiraling down the tree. Each time we turn a corner, we get a more precise
        // interval, until we can go no further.
        if (x == null) {
            Node node = new Node(key, val, 1);
            if (prev != null) {
                prev.succ = node;
                node.prev = prev;
            }
            if (succ != null) {
                node.succ = succ;
                succ.prev = node;
            }
            return node;
        }
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, val, prev, x);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val, x, succ);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // Return Node containing key of rank k.
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }


    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            if (x.succ != null) {
                x.succ.prev = null;
            }  
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Return the tree without the min and keep the pred and the succ links of the min unchanged.
     * This method is called by delete() to prevent the succ links from being cutting off by
     * deleteMin() when adjusting the structure.
     */
    private Node minMoved(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = minMoved(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            if (x.prev != null) {
                x.prev.succ = null;
            }
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left  = delete(x.left,  key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null && x.left == null) {
                return null;
            }
            if (x.right == null && x.prev != null) {
                x.prev.succ = x.succ;
                return x.left;
            }
            if (x.left == null && x.succ != null) {
                x.succ.prev = x.prev;
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = minMoved(t.right);
            x.left = t.left;

            t.prev.succ = t.succ;
            t.succ.prev = t.prev;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    public Iterable<Key> levelOrder() {
        // Returns the keys in the BST in level order (for debugging)
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) {
                continue;
            }
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public static void main(String[] args) {
        Ex_34_Threading<String, Integer> st = new Ex_34_Threading<String, Integer>();
         for (int i = 0; !StdIn.isEmpty(); i += 1) {
             String key = StdIn.readString();
             st.put(key, i);
         }


        for (String s : st.levelOrder()) {
            StdOut.println(s + " " + st.get(s));
            StdOut.println("for " + s + " prev is " + st.prev(s) + "; succ is " + st.next(s));
        }

        StdOut.println();

        st.delete("e");

        for (String s : st.levelOrder()) {
            StdOut.println(s + " " + st.get(s));
            StdOut.println("for " + s + " prev is " + st.prev(s) + "; succ is " + st.next(s));
        }

    }
}
