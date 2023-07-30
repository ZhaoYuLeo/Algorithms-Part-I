package chapter1.section5;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import chapter1.section5.UF;
import chapter1.section5.Ex_03;
import chapter1.section5.Ex_17_RandomConnections;


/**
 * Use your client from EXERCISE 1.5.17 to test the hypothesis that the number of pairs generated
 * to get one component is ~ ½NlnN.
 */
public class Ex_21_ErdosRenyiModel {

    public static void trail(int N, int maxScale) {
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = N; i < maxScale; i += N) {
            int times = 10;
            int avg = 0;
            for (int j = 0; j < times; j += 1) {
                UF uf = new Ex_03(i);
                int pairs = Ex_17_RandomConnections.count(i, uf);
                avg += pairs;
            }
            avg /= times; 
            StdDraw.point(i, avg);
        }
    }

    public static void theory(int N, int maxScale) {
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        for (int i = N; i < maxScale; i += N) {
            double pairs = 0.5 * i * Math.log(i);
            StdDraw.point(i, pairs);
        }
    }

    public static void main(String[] args) {
        int N = 10;
        int maxScale = 1000;
        
        StdDraw.setXscale(-2, maxScale);
        StdDraw.setYscale(-2, maxScale * Math.log(maxScale) * 0.5 + 2);
        StdDraw.setPenRadius(0.01);

        trail(N, maxScale);
        theory(N, maxScale);
    }
}
