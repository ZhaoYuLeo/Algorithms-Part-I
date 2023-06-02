package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

public class Ex_06 {
    /*
     * A string s is a circular rotation of a string t if it matches when 
     * the characters are circularly shifted by any number of positions;
     */
    public static boolean circularRotation(String a, String b) {
        return (a.length() == b.length() && (a + a).indexOf(b) > -1);
    }

    public static void main(String[] args) {
        String a = "ACTGACG";
        String b = "TGACGAC";
        StdOut.println(a + " and " + b + " is circular rotation: " + circularRotation(a, b));
    }
}
