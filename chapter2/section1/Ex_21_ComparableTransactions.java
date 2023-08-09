package chapter1.section2;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

public class Ex_21_ComparableTransactions {
    private final String who;
    private final Date when;
    private final double amount;

    public Ex_13_Transaction(String who, Date when, double amount) {
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public Ex_13_Transaction(String transaction) {
        String[] s = transaction.split("\\s+");
        this.who = s[0];
        this.when = new Date(s[1]);
        this.amount = Double.parseDouble(s[2]);
    }

    public String who() {
        return this.who;
    }

    public Date when() {
        return this.when;
    }

    public double amount() {
        return this.amount;
    }

    public String toString() {
        return who() + "    " + when() + "  " + amount();
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
        Ex_13_Transaction t = (Ex_13_Transaction) that;
        return (t.who().equals(this.who())
                 && t.when().equals(this.when())
                 && t.amount() == this.amount());
    }

    public int compareTo(Ex_13_Transaction that) {
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


    public static void main(String[] args) {
        Ex_13_Transaction t1 = new Ex_13_Transaction("Alice 6/01/2001 678.986");
        Ex_13_Transaction t2 = new Ex_13_Transaction("Alice 6/01/2001 678.986");
        Ex_13_Transaction t3 = new Ex_13_Transaction("Frank 9/19/2101 89708.986");
        StdOut.println(t1);
        StdOut.println(t1.equals(t2));
        StdOut.println(t1.compareTo(t3));
    }
}
