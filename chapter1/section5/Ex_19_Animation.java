package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import chapter1.section5.Ex_18_RandomGridGenerator.*;
import chapter1.section5.Ex_03;
import chapter1.section5.UF;

/**
 * Write a RandomGrid client (see EXERCISE 1.5.18) that uses UnionFind as in our development
 * client to check connectivity and uses StdDraw to draw the connections as they are processed.
 */
public class Ex_19_Animation {

    public static void randomGrid(int N) {
        int side = (int)Math.ceil(Math.sqrt(N));
        drawBackground(side);
        UF uf = new Ex_03 (N);
        Connection[] connections = Ex_18_RandomGridGenerator.generate(N);
        for (Connection c : connections) {
            if (uf.connected(c.p, c.q)) continue;
            uf.union(c.p, c.q);
            drawConnection(c.p, c.q, side);
        }
    }

    public static void drawBackground(int side) {
        StdDraw.setXscale(-2, side + 2);
        StdDraw.setYscale(-2, side + 2);
        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < side; i += 1) {
            for (int j = 0; j < side; j += 1) {
                StdDraw.point(j, i);
                StdDraw.textRight(j, i, Integer.toString(j + i * side));
            }
        }
    }

    public static void drawConnection(int p, int q, int side) {
        StdDraw.setPenRadius(0.005);
        int x0 = p % side;
        int y0 = p / side;
        int x1 = q % side;
        int y1 = q / side;
        StdDraw.line(x0, y0, x1, y1);
        sleep();
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        randomGrid(9);
    }
}
