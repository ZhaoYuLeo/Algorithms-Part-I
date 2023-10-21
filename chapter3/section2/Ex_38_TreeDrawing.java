package chapter3.section2;

import java.util.Collections;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Add a method draw() to BST that draws BST figures in the style of the txt. Hint: Use instance
 * variable to hold node coordinates, and use a recursive method to set the values of these variable.
 */
public class Ex_38_TreeDrawing<Key extends Comparable<Key>, Value> {
    private Node root;              // root of BST

    private class Node {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private int N;              // # nodes in subtree rooted here
        private double x, y;        // the x and y coordinates
        private Node pred, succ;    // links to the predecessor and successor nodes

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
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
        root = put(root, key, val, null, null);
    }

    private Node put(Node x, Key key, Value val, Node pred, Node succ) {
        // Change key's value to val if key in subtree rooted at x.
        // Otherwise, add new node to subtree associating key with val.
        if (x == null) {
            Node node = new Node(key, val, 1);
            if (pred != null) {
                pred.succ = node;
                node.pred = pred;
            }
            if (succ != null) {
                node.succ = succ;
                succ.pred = node;
            }
            return node;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val, pred, x);
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
                x.succ.pred = null;
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
            if (x.pred != null) {
                x.pred.succ = null;
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
            if (x.right == null && x.pred != null) {
                x.pred.succ = x.succ;
                return x.left;
            }
            if (x.left == null && x.succ != null) {
                x.succ.pred = x.pred;
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = minMoved(t.right);
            x.left = t.left;
            
            t.pred.succ = t.succ;
            t.succ.pred = t.pred;
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

    /** Draw BST figures */
    public void draw() {
        double R = 0.35; // The radius of the node
        setXCoordinates();
        setYCoordinates(root, 0);

        StdDraw.setXscale(- 1, root.N);
        StdDraw.setYscale( - root.N, 1);
        StdDraw.setPenRadius(0.005);

        drawLine(root);
        StdDraw.setPenRadius(0.002);
        drawCircle(root, R);
    }

    private void drawCircle(Node n, double R) {
        if (n == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(n.x, n.y, R);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(n.x, n.y, R);
        StdDraw.text(n.x, n.y, n.key.toString());
        drawCircle(n.left, R);
        drawCircle(n.right, R);
    }

    private void drawLine(Node n) {
        if (n == null) {
            return;
        }
        if (n.left == null) {
            StdDraw.line(n.x, n.y, n.x - 0.3, n.y - 0.5);
        }
        if (n.right == null) {
            StdDraw.line(n.x, n.y, n.x + 0.3, n.y - 0.5);
        }
        if (n.left != null) {
            StdDraw.line(n.x, n.y, n.left.x, n.left.y);
        }
        if (n.right != null) {
            StdDraw.line(n.x, n.y, n.right.x, n.right.y);
        }
        drawLine(n.left);
        drawLine(n.right);
    }

    private void setXCoordinates() {
        Node node = min(root);
        node.x = 0;
        while (node.succ != null) {
            node.succ.x = node.x + 1;
            node = node.succ;
        }
    }

    private void setYCoordinates(Node n, double y) {
        if (n == null) {
            return;
        }
        // Do you know the shape of the tree produced by the following commented out code?
        // if (n.succ != null && n.succ.x == 0) {
        //     n.succ.x = n.x + 1;
        // }
        // if (n.pred != null && n.pred.x == 0) {
        //     n.pred.x = n.x - 1;
        // }
        n.y = y;
        setYCoordinates(n.left, y - 1);
        setYCoordinates(n.right, y - 1);
    }

    public static void main(String[] args) {
        Ex_38_TreeDrawing<String, Integer> st = new Ex_38_TreeDrawing<String, Integer>();
        String[] str = {"S", "E", "X", "A", "R", "C", "H", "M"};
        for (int i = 0; i < str.length; i += 1) {
            String key = str[i];
            st.put(key, i);
        }
        st.draw();
        st.delete("E");
        st.draw();
    }
}
