package chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

public class Ex_7 {
    public static void main(String[] args) {

        StdOut.printf("The value printed by a\n");
        double t = 9.0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        StdOut.printf("%.5f\n", t);

        StdOut.printf("The value printed by b\n");
        int sum1 = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++)
            sum1++;
        StdOut.println(sum1);

        StdOut.printf("The value printed by c\n");
        int sum2 = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < 1000; j++)
            sum2++;
        StdOut.println(sum2);
    }
}
