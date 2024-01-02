package chapter3.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import util.VisualAccumulator;

/**
 * Cost plots. Instrument RedBlackBST so that you can produce plots like the
 * ones in this section showing the cost of each put() operation during the
 * computn = 11.005994702356057
 * ation(see EXERCISE 3.1.38).
 */
public class Ex_43_CostPlots {
    public static class RedBlackBSTWithCost<Key extends Comparable<Key>, Value> extends RedBlackBST<Key, Value> {
        private int compares = 0;

        /**
         * Return the number of compares of the put() operations.
         */
        public int getCost() {
            return this.compares;
        }

        /**
         * Insert key-value pair into the symbol table
         */
        public void put(Key key, Value val) {
            this.compares = 0;
            root = put(root, key, val);
            root.color = BLACK;
        }

        /**
         * Insert the key-value pair in the subtree rooted at h
         */
        protected Node put(Node h, Key key, Value val) {
            if (h == null) {
                return new Node(key, val, RED, 1);
            }
            int cmp = key.compareTo(h.key);
            this.compares += 1;
            if (cmp < 0) {
                h.left = put(h.left, key, val);
            } else if (cmp > 0) {
                h.right = put(h.right, key, val);
            } else {
                h.val = val;
            }
            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }
            h.size = size(h.left) + size(h.right) + 1;
            return h;
        }
    }

    public static void main(String[] args) {
        VisualAccumulator va = new VisualAccumulator(14350, 25
                , "Costs for java FrequencyCounter 8 < tale.txt using RedBlackBST"
                , "compares");
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        RedBlackBSTWithCost<String, Integer> st = new RedBlackBSTWithCost<>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) {
                continue;
            }
            words += 1;
            Integer count = st.get(key);
            if (count == null) {
                st.put(key, 1);
                distinct += 1;
            } else {
                st.put(key, count + 1);
            }
            int cost = st.getCost();
            va.addDateValue(cost);
        }
        va.stop();
        StdOut.println(va);
        // Result:
        // n = 14346, mean = 11.005994702356057
    }
}
