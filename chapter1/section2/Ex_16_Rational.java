package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

public class Ex_16_Rational {
    private long numerator;
    private long denominator;

    public Ex_16_Rational(long numerator, long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be zero");
        }
        long factor = gcb(numerator, denominator);
        this.numerator = numerator / factor;
        this.denominator = denominator / factor;
    }

    public long num() {
        return this.numerator;
    }

    public long den() {
        return this.denominator;
    }

    public Ex_16_Rational plus(Ex_16_Rational b) {
        long num = this.num() * b.den() + this.den() * b.num();
        long den = this.den() * b.den();
        return new Ex_16_Rational(num, den);
    }

    public Ex_16_Rational minus(Ex_16_Rational b) {
        long num = this.num() * b.den() - this.den() * b.num();
        long den = this.den() * b.den();
        return new Ex_16_Rational(num, den);
    }

    public Ex_16_Rational times(Ex_16_Rational b) {
        return new Ex_16_Rational(this.num() * b.num(), this.den() * b.den());
    }

    public Ex_16_Rational divides(Ex_16_Rational b) {
        return new Ex_16_Rational(this.num() * b.den(), this.den() * b.num()); 
    }

    public int compareTo(Ex_16_Rational that) {
        long result = this.num() * that.den() - this.den() * that.num();
        if (result > 0) {
            return 1;
        }
        if (result < 0) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }
        if (b == this) {
            return true;
        }
        if (b.getClass() != this.getClass()) {
            return false;
        }
        Ex_16_Rational that = (Ex_16_Rational) b;
        if (this.num() * that.den() == this.den() * that.num()) {
            return true;
        }
        return false;
    }

    private static long gcb(long p, long q) {
        if (q == 0) {
            return p;
        }
        return gcb(q, p % q);
    }

    public String toString() {
        if (num() == 0) {
            return String.valueOf(0);
        }
        if (Math.abs(num() * den()) == 1) {
            return String.valueOf(num() * den());
        }
        if (den() < 0) {
            return (-1 * num()) + "/" + (-1 * den());
        }
        return num() + "/" + den();
    }

    public static void main(String[] args) {
        Ex_16_Rational r1  = new Ex_16_Rational(-4, -6);
        Ex_16_Rational r2  = new Ex_16_Rational(6, -9);
        StdOut.println("arithmetic operation between " + r1 + " and " + r2);

        StdOut.println(" + : " + (r1.plus(r2)));
        StdOut.println(" - : " + (r1.minus(r2)));
        StdOut.println(" * : " + (r1.times(r2)));
        StdOut.println(" / : " + (r1.divides(r2)));

        StdOut.println("Are they equal? " + (r1.equals(r2)) + " " + (r2.equals(r1)));
    }
}
