package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

public class Ex_19 {
    private final int month;
    private final int day;
    private final int year;

    public Ex_19 (int m, int d, int y) {
        valid(m, d, y);

        month = m;
        day = d;
        year = y;
    }

    public Ex_19 (String date) {
        String[] fields = date.split("/");
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
        valid(month, day, year);
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

    /*
     * Returns the week for the date.
     * Assume the dates is in the 21st century.
     * Reference: https://en.wikipedia.org/wiki/Zeller%27s_congruence
     *            https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week
     *
     * Author: Tomohiko Sakamoto
     */
    public String dayOfTheWeek() {
        // Sakamoto's methods copied from wiki[https://en.wikipedia.org/wiki/Determination_of_the_day_of_the_week]
        int t[] = {0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        int m = month; int y = year; int d = day;
        if (m < 3) {
            y -= 1;
        }
        return daysOfWeek[(y + y/4 - y/100 + y/400 + t[m-1] + d) % 7];
    }

    // abbreviate other methods
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public static void main(String[] args) {
        Ex_19 date = new Ex_19(args[0]);
        StdOut.println(date.dayOfTheWeek() + " " + date);
    }
}
