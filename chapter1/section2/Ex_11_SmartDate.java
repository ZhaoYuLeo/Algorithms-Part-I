package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

public class Ex_11_SmartDate {
    private final int month;
    private final int day;
    private final int year;

    public Ex_11_SmartDate(int m, int d, int y) {
        valid(m, d, y);

        month = m;
        day = d;
        year = y;
    }

    private static boolean isLeapYear(int y) {
        if (((y % 4) == 0 && (y % 100) != 0) || (y % 400) == 0) {
            return true;
        }
        return false;
    }

    public static void valid(int m, int d, int y) {
        int[] monthDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (y < 0 || m < 1 || d < 1 || m > 12 || d > monthDays[m - 1]) {
            throw new IllegalArgumentException("Please provide legal date: months are between 1 and 12,...");
        }
        if (m == 2 && !isLeapYear(y) && (d > 28)) {
            throw new IllegalArgumentException("Common year doesn't have February 29.");
        }
    }

    // abbreviate other methods
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        StdOut.println(new Ex_11_SmartDate(m, d, y));
    }
}
