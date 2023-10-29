package chapter2.section4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a TopM client that reads points (x, y, z) from standard input, takes a value M from
 * the command line, and prints the M points that are closest to the origin in Euclidean
 * distance. Estimate the running time of your client for N = 10^8 and M = 10^4.
 */
public class Ex_28_SelectionFilter {

    private static class Point implements Comparable<Point> {
        double x, y, z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point(String format) {
            // need verify
            String[] str = format.split("\\s+");
            this.x = Double.valueOf(str[0]);
            this.y = Double.valueOf(str[1]);
            this.z = Double.valueOf(str[2]);
        }

        // Euclidean distance to the origin(0, 0, 0)
        public Double distance() {
            return square(x) + square(y) + square(z);
        }

        private double square(double n) {
            return Math.pow(n, 2);
        }

        // compare Euclidean distance
        public int compareTo(Point that) {
            return distance().compareTo(that.distance());
        }

        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        HeapMaxPQ<Point> pq = new HeapMaxPQ<>(M + 1);
        while (StdIn.hasNextLine()) {
            // TODO: generate N random points
            pq.insert(new Point(StdIn.readLine()));
            if (pq.size() > M) {
                pq.delMax();
            }
        }
        Stack<Point> s = new Stack<>();
        while (!pq.isEmpty()) {
            s.push(pq.delMax());
        }
        for (Point p : s) {
            StdOut.println(p);
        }
    }
}

