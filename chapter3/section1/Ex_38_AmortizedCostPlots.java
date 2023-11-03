package chapter3.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop instrumentation for FrequencyCounter, SequentialSearchST, and BinarySearchST so that
 * you can produce plots like the ones in this section showing the cost of each put() operation
 * during the computation.
 */
public class Ex_38_AmortizedCostPlots {
    private static interface ST<Key, Value> {
        boolean contains(Key key);

        void put(Key key, Value val);

        Value get(Key key);

        int getCost();
    }

    private static class SequentialSearchST<Key, Value> implements ST<Key, Value> {
        private Node first;     // first node in the linked list

        private int nodeAccess;

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

        public int getCost() {
            return nodeAccess;
        }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public void put(Key key, Value val) {
            this.nodeAccess = 0;

            for (Node x = first; x != null; x = x.next) {
                this.nodeAccess += 1;

                if (key.equals(x.key)) {
                    x.val = val;

                    this.nodeAccess += 2;
                    return;
                }
            }
            first = new Node(key, val, first);
            this.nodeAccess += 1;
        }

        public Value get(Key key) {
            for (Node x = first; x != null; x = x.next) {

                if (key.equals(x.key)) {
                    return x.val;
                }
            }
            return null;
        }
    }

    private static class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {
        private static final int INIT_SIZE = 8;

        private Key[] keys;
        private Value[] vals;
        private int N;

        private int arrayAccess;

        public BinarySearchST() {
            keys = (Key[])   new Comparable[INIT_SIZE];
            vals = (Value[]) new Object[INIT_SIZE]; 
        }

        public int getCost() {
            return arrayAccess;
        }

        private void resize(int capacity) {
            assert capacity > N : "lose data";
            Key[]   keysTemp = (Key[])   new Comparable[capacity];
            Value[] valsTemp = (Value[]) new Object[capacity];
            for (int i = 0; i < N; i += 1) {
                keysTemp[i] = keys[i];
                valsTemp[i] = vals[i];

                this.arrayAccess += 4;
            }
            keys = keysTemp;
            vals = valsTemp;
        }

        /**
         * Return the number of keys less than key
         */
        private int rank(Key key) {
            int lo = 0, hi = N - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = keys[mid].compareTo(key);

                this.arrayAccess += 1;

                if (cmp < 0) {
                    lo = mid + 1;
                } else if (cmp > 0) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            }
            return lo;
        }

        public boolean contains(Key key) {
            return get(key) != null;
        }

        public void put(Key key, Value val) {
            this.arrayAccess = 0;

            if (key == null || val == null) {
                return;
            }
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                vals[i] = val;

                this.arrayAccess += 2;
                return;
            }
            if (N == keys.length) {
                resize(2 * N);
            }
            for (int j = N; j > i; j -= 1) {
                keys[j] = keys[j - 1];
                vals[j] = vals[j - 1];

                this.arrayAccess += 4;
            }
            keys[i] = key;
            vals[i] = val;
            N += 1;

            this.arrayAccess += 2;
        }

        public Value get(Key key) {
            if (N == 0) {
                return null;
            }
            int i = rank(key);
            if (i < N && keys[i].compareTo(key) == 0) {
                return vals[i];
            } else {
                return null;
            }
        }

    }

    public static class VisualAccumulator {
        private double total;
        private int N;

        public VisualAccumulator(int trials, double max) {
            StdDraw.setXscale(0, trials);
            StdDraw.setYscale(0, max);
            StdDraw.setPenRadius(0.005);
        }

        public void addDataValue(double val) {
            N += 1;
            total += val;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(N, val);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(N, total / N);
        }
    }

    public static void frequencyCounter(int minLen, boolean useBS) {
        int trials = 15000;
        double max = 30000;
        VisualAccumulator va = new VisualAccumulator(trials, max);
        ST<String, Integer> st = new SequentialSearchST<>(); 
        if (useBS) {
            st = new BinarySearchST<>();
        }
        int count = 0;
        String last = null;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
            va.addDataValue(st.getCost());
            count += 1;
            last = word;
        }
        StdOut.println("The last word inserted: " + last + "; The number of words processed before: " + (count - 1));
    }

    public static void main(String[] args) {
        frequencyCounter(Integer.parseInt(args[0]), Boolean.parseBoolean(args[1]));
        //The last word inserted: known; The number of words processed before: 135642
        //The last word inserted: faltering; The number of words processed before: 14345
        //The last word inserted: disfigurement; The number of words processed before: 4578
    }
}
