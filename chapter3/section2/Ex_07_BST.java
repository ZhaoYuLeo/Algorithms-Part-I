package chapter3.section2;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Add to BST a recursive method avgCompares() that computes the average number
 * of compares required by a random search hit in a given BST (the internal path
 * length of the tree divided by its size, plus one). Develop two implementations:
 * a recursive method (which takes linear time and space proportional to the
 * height), and a method like size() that adds a field to each node in the tree
 * (and takes linear space and constant time per query).
 */
public class Ex_07_BST<Key extends Comparable<Key>, Value> {
    private Node root;              // root of BST

    private class Node {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private int N;              // # nodes in subtree rooted here
        private int height;         // height of the subtree
        private int internalPathLength = 0;// The sum of the depths of all nodes called the internal path length of the tree 

        public Node(Key key, Value val, int N, int height) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.height = height;
        }
    }

    /**
     * Return the average number of compares required by a random search hit
     */
    public double avgCompares() {
        return internalPathLength(root) / size() + 1;
    }

    /**
     * Adding the depths of all nodes
     */
    private int internalPathLength(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N - 1 + internalPathLength(x.left) + internalPathLength(x.right);
    }

    public double avgCompares2() {
        return internalPathLength2(root) / size() + 1;
    }

    private int internalPathLength2(Node x) {
        if (x == null) {
            return 0;
        }
        return x.internalPathLength;
    }

    public int size() {
        return size(root);
//        return root.N;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // Return value associated with key in the subtree rooted at x;
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
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) return new Node(key, val, 1, 0);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height2(x.left), height2(x.right)) + 1;
        x.internalPathLength = internalPathLength2(x.left) + internalPathLength2(x.right) + x.N - 1;
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
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height2(x.left), height2(x.right)) + 1;
        x.internalPathLength = internalPathLength2(x.left) + internalPathLength2(x.right) + x.N - 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height2(x.left), height2(x.right)) + 1;
        x.internalPathLength = internalPathLength2(x.left) + internalPathLength2(x.right) + x.N - 1;
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
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        // remaind me of the percolation/union-find, we use this to reduce the height of the tree
        // merge smaller one to bigger one
        x.N = size(x.left) + size(x.right) + 1;
        x.height = Math.max(height2(x.left), height2(x.right)) + 1;
        x.internalPathLength = internalPathLength2(x.left) + internalPathLength2(x.right) + x.N - 1;
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

    public int height() {
        return height(root);
    }

    /**
     * A recursive method which takes linear time and space proportional
     * to the height.
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * Adds a field to each node in the tree and takes linear space and
     * constant time per query.
     */
    public int height2() {
        return height2(root);
    }

    private int height2(Node x) {
        if (x == null) {
            return -1;
        }
        return x.height;
    }

    public Iterable<Key> levelOrder() {
        // Returns the keys in the BST in level order (for debugging)
        Queue<Key> keys = new Queue<Key>();
        // record in stack if we write this method in a recursive way.
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
        Ex_07_BST<String, Integer> st = new Ex_07_BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder()) {
            StdOut.println(s + " " + st.get(s));
        }

        StdOut.println();

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }

        StdOut.println(st.height());
        StdOut.println(st.height2());

        StdOut.println(st.avgCompares());
        StdOut.println(st.avgCompares2());
    }
}
