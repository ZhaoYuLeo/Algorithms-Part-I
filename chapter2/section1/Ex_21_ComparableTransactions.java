package chapter2.section1;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

public class Ex_21_ComparableTransactions implements Comparable<Ex_21_ComparableTransactions> {
    private final String    who;    // customer
    private final Date      when;   // date
    private final double    amount; // amount

    public Ex_21_ComparableTransactions(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        }
        this.who    = who;
        this.when   = when;
        this.amount = amount;
    }

    public Ex_21_ComparableTransactions(String transaction) {
        String[] s = transaction.split("\\s+");
        who    = s[0];
        when   = new Date(s[1]);
        amount = Double.parseDouble(s[2]);
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        }
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-10s %10s %8.2f", who, when, amount);
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        Ex_21_ComparableTransactions t = (Ex_21_ComparableTransactions) that;
        return (t.who().equals(this.who())
                 && t.when().equals(this.when())
                 && t.amount() == this.amount());
    }

    public int compareTo(Ex_21_ComparableTransactions that) {
        // return Double.compare(this.amount(), that.amount());
        if (this.amount() > that.amount()) {
            return 1;
        } else if (this.amount() < that.amount()) {
            return -1;
        } else {
            return 0;
        }
    }


    public int hashCode() {
        int result = 17;
        result = 31 * result + who().hashCode();
        result = 31 * result + when().hashCode();
        result = 31 * result + ((Double)amount()).hashCode();
        return result;
    }


    public static class WhoOrder implements Comparator<Ex_21_ComparableTransactions> {
        @Override
        public int compare(Ex_21_ComparableTransactions v, Ex_21_ComparableTransactions w) {
            return v.who().compareTo(w.who());
        }
    }

    public static class WhenOrder implements Comparator<Ex_21_ComparableTransactions> {
        @Override
        public int compare(Ex_21_ComparableTransactions v, Ex_21_ComparableTransactions w) {
            return v.when().compareTo(w.when());
        }
    }

    public static class HowMuchOrder implements Comparator<Ex_21_ComparableTransactions> {
        @Override
        public int compare(Ex_21_ComparableTransactions v, Ex_21_ComparableTransactions w) {
            return Double.compare(v.amount(), w.amount());
        }
    }

    public static void main(String[] args) {
        Ex_21_ComparableTransactions[] a = new Ex_21_ComparableTransactions[4];
        a[0] = new Ex_21_ComparableTransactions("Alice 6/01/2007 4136.012");
        a[1] = new Ex_21_ComparableTransactions("Frank 9/19/2101 89708.986");
        a[2] = new Ex_21_ComparableTransactions("Alice 6/01/2001 678.986");
        a[3] = new Ex_21_ComparableTransactions("Smith 8/09/2011 2673.16");

        StdOut.println("Unsorted");
        for (Ex_21_ComparableTransactions i : a) {
            StdOut.println(i);
        }

        StdOut.println("Sort by customer");
        Arrays.sort(a, new Ex_21_ComparableTransactions.WhoOrder());
        for (Ex_21_ComparableTransactions i : a) {
            StdOut.println(i);
        }

        StdOut.println("Sort by date");
        Arrays.sort(a, new Ex_21_ComparableTransactions.WhenOrder());
        for (Ex_21_ComparableTransactions i : a) {
            StdOut.println(i);
        }

        StdOut.println("Sort by amount");
        Arrays.sort(a, new Ex_21_ComparableTransactions.HowMuchOrder());
        for (Ex_21_ComparableTransactions i : a) {
            StdOut.println(i);
        }

    }
}
