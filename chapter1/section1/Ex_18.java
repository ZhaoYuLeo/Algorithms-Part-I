package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_18 {
    // product
    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a+a, b/2);
        return mystery(a+a, b/2) + a;
    }

    // pow
    public static int mystery1(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery1(a*a, b/2);
        return mystery1(a*a, b/2) * a;
    }

    public static void main(String[] args) {
        StdOut.println(mystery(2, 25)); //2 + (b=12, 4, b=6, 8, b=3) + 16 + 32 = 50
        StdOut.println(mystery(3, 11)); //3 + 6 + (b=2, 12) + 24 = 33
        StdOut.println(mystery(3, 10));
        StdOut.println(mystery1(2, 25)); //2 * 256 * 256 * 256 = 33554432
        StdOut.println(mystery1(3, 11)); //3 * 9 * 81 * 81 = 177147
    }
}
