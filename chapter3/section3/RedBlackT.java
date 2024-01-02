package chapter3.section3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import util.Constants;

/**
 * Red-Black tree with draw method
 */
public abstract class RedBlackT<Key extends Comparable<Key>, Value> {

    protected static final boolean RED = true;
    protected static final boolean BLACK = false;

    protected Node root;

    protected class Node {
        protected Key key;          // key
        protected Value val;        // associated data
        protected Node left, right; // links to left and right subtrees
        protected boolean color;    // color of parent link
        protected int size;         // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }


    /**
     * Return the sum of the depths of all nodes.
     */
    public int internalPath() {
        if (root == null) {
            return 0;
        }
        return internalPath(root, 0, 0);
    }

    /**
     * Adding the depths of all nodes.
     */
    private int internalPath(Node n, int sum, int path) {
        if (n == null) {
            return sum;
        }
        return internalPath(n.left, sum + path, path + 1) + internalPath(n.right, 0, path + 1);
    }

    /**
     * Return the average path length of the tree.
     */
    public double avgPath() {
        if (root == null) {
            return 0;
        }
        return (internalPath() * 1.0 / size()) + 1;
    }

    /**
     * Number of nodes in the tree.
     */
    public int size() {
        return size(root);
    }

    /**
     * Number of nodes in the subtree rooted at n.
     */
    protected int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    /**
     * Return true if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return true if the current node is red, false otherwise.
     */
    protected abstract boolean isRed(Node n);


    /**
     * Draw red-black tree.
     */
    public void draw(Key key) {
        double R = 0.35; // The radius of the node

        // keep the tree at the original position after local rotation.
        StdDraw.setCanvasSize(512, 512);
        // assert size(root) < 16;
        StdDraw.setScale(0, 16);
        StdDraw.setPenRadius(Constants.PEN1);
        drawLine(root, 0, 15);
        StdDraw.setPenRadius(Constants.PEN0);
        drawCircle(root, 0, 15, R, key);
    }

    /**
     * Draw all nodes of the tree rooted at the given node n.
     * And highlight the node associated with key.
     */
    protected void drawCircle(Node n, int prev, int y, double R, Key key) {
        if (n == null) {
            return;
        }
        
        int x = size(n.left) + prev + 1;
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x, y, R);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(x, y, R);
        StdDraw.setFont(Constants.BIG_KEY);
        if (n.key.equals(key)) {
            StdDraw.setPenColor(Constants.RED);
        }
        StdDraw.text(x, y, n.key.toString());
        StdDraw.setPenColor(StdDraw.BLACK);
        drawCircle(n.left, prev, y - 1, R, key);
        drawCircle(n.right, x, y - 1, R, key);
    }

    /**
     * Set the style of the red line.
     */
    private void setRedLine() {
        StdDraw.setPenColor(Constants.RED);
        StdDraw.setPenRadius(3 * Constants.PEN1);
    }

    /**
     * Set the style of the black line.
     */
    private void setBlackLine() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(Constants.PEN1);
    }

    /**
     * Draw all lines connected nodes of the tree rooted at n.
     */
    protected void drawLine(Node n, int prev, int y) {
        if (n == null) {
            return;
        }
        int x = size(n.left) + prev + 1;
        if (n.left == null) {
            StdDraw.line(x, y, x - 0.3, y - 0.5);
        }
        if (n.right == null) {
            StdDraw.line(x, y, x + 0.3, y - 0.5);
        }
        if (n.left != null) {
            if (isRed(n.left)) {
                setRedLine();
            }
            int xL = size(n.left.left) + prev + 1;
            int yL = y - 1;
            StdDraw.line(x, y, xL, yL);
        }
        setBlackLine();
        if (n.right != null) {
            if (isRed(n.right)) {
                setRedLine();
            }
            int xR = x + size(n.right.left) + 1;
            int yR = y - 1;
            StdDraw.line(x, y, xR, yR);
        }
        setBlackLine();
        drawLine(n.left, prev, y - 1);
        drawLine(n.right, x, y - 1);
    }

}

