package chapter3.section2;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Instrument BST so that you can produce plots like the ones in this section showing the cost of each put()
 * operation during the computation (see EXERCISE 3.1.38)
 */
public class Ex_44_CostPlots {

    private static class BSTCost<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
        private int compares;

        public int getCost() {
            return compares;
        }

        public void put(Key key, Value val) {
            compares = 0;
            super.put(key, val);
        }

        public Node put(Node x, Key key, Value val) {
            if (x == null) {
                return new Node(key, val, 1);
            }
            compares += 1;
            return super.put(x, key, val);
        }
    }

    public static class VisualAccumulator {
        private double total;
        private int N;

        private double max;
        private double last;
        private int t;

        public VisualAccumulator(int trials, double max) {
            t = trials;
            double marginLeft = -1000;
            double marginRight = 1000;
            double marginTop = 5;
            double marginDown = -2;

            double offsetDown = -1.5;
            double offsetLeft = -100;
            StdDraw.setXscale(marginLeft, trials + marginRight);
            StdDraw.setYscale(marginDown, max + marginTop);

            StdDraw.line(offsetLeft, 0, trials, 0);
            StdDraw.line(0, offsetDown / 3, 0, max);
            StdDraw.line(trials, 0, trials, offsetDown / 3);
            StdDraw.line(marginLeft / 3, max, 0, max);

            StdDraw.text(trials, offsetDown, Integer.toString(trials));
            StdDraw.text(marginLeft / 2, max, Double.toString(max));
            StdDraw.text(0, offsetDown, "0");

            StdDraw.setPenRadius(0.005);
        }

        public void addDataValue(double val) {
            if (val > max) {
                max = val;
            }
            N += 1;
            total += val;
            last = total / N;
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.point(N, val);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(N, total / N);
        }

        public void stop() {
            double marginRight = 1000;
            StdDraw.text(t + marginRight / 4, max, Integer.toString((int)max));
            StdDraw.text(t + marginRight / 4, last, Integer.toString((int)last));
        }
    }

    private static void frequencyCounter(int minLen) {
        VisualAccumulator va = new VisualAccumulator(14350, 20);
        BSTCost<String, Integer> st = new BSTCost<String, Integer>();
        int distinct = 0, words = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) {
                continue;
            }
            Integer value = st.get(word);
            if (value == null) {
                st.put(word, 1);
                distinct += 1;
            } else {
                st.put(word, value + 1);
            }
            words += 1;
            va.addDataValue(st.getCost());
        }
        va.stop();

    }

    public static void main(String[] args) {
        frequencyCounter(Integer.parseInt(args[0]));
    }
}
