package chapter1.section2;

import edu.princeton.cs.algs4.StdOut;

/**
 * Rational numbers. Use assertions to be immune to overflow.
 * https://en.wikipedia.org/wiki/Exclusive_or
 */
public class Ex_17_RobustRational {
    private long numerator;
    private long denominator;

    public Ex_17_RobustRational(long numerator, long denominator) {
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

    // The following four functions are copied from https://github.com/openjdk/jdk
    public static long add(long x, long y) {
        long r = x + y;
        assert (((x ^ r) & (y ^ r)) >= 0) : "long overflow";
        return r;
    }


    public static long sub(long x, long y) {
        long r = x - y;
        assert (((x ^ y) & (x ^ r)) >= 0) : "long overflow";
        return r;
    }

    public static long multiply(long x, long y) {
        long r = x * y;
        long ax = Math.abs(x);
        long ay = Math.abs(y);
        if (((ax | ay) >>> 31 != 0)) {
            // Some bits greater than 2^31 that might cause overflow
            // Check the result using the divide operator
            // and check for the special case of Long.MIN_VALUE * -1
            // StdOut.println(((y == 0) || (r / y == x)) && (x != Long.MIN_VALUE || y != -1));
            // assert (((y == 0) || (r / y == x)) && (x != Long.MIN_VALUE || y != -1)) : "long overflow";
            //  if (((y != 0) && (r / y != x)) ||
            //      (x == Long.MIN_VALUE && y == -1)) {
            //       throw new ArithmeticException("long overflow");
            //  }
            assert (((y == 0) || (r / y == x)) &&
                   (x != Long.MIN_VALUE || y != -1)) : "long overflow";
        }
        return r;
    }

    public static long divide(long x, long y) {
        long q = x / y;
        assert ((x & y & q) >= 0) : "long overflow";
        return q;
    }

    public Ex_17_RobustRational plus(Ex_17_RobustRational b) {
        long num = Ex_17_RobustRational.add(Ex_17_RobustRational.multiply(this.num(), b.den()),
                                            Ex_17_RobustRational.multiply(this.den(), b.num()));
        long den = Ex_17_RobustRational.multiply(this.den(), b.den());
        return new Ex_17_RobustRational(num, den);
    }

    public Ex_17_RobustRational minus(Ex_17_RobustRational b) {
        long num = Ex_17_RobustRational.divide(Ex_17_RobustRational.multiply(this.num(), b.den()),
                                               Ex_17_RobustRational.multiply(this.den(), b.num()));
        long den = Ex_17_RobustRational.multiply(this.den(), b.den());
        return new Ex_17_RobustRational(num, den);
    }

    public Ex_17_RobustRational times(Ex_17_RobustRational b) {
        return new Ex_17_RobustRational(Ex_17_RobustRational.multiply(this.num(), b.num()),
                                        Ex_17_RobustRational.multiply(this.den(), b.den()));
    }

    public Ex_17_RobustRational divides(Ex_17_RobustRational b) {
        return new Ex_17_RobustRational(Ex_17_RobustRational.multiply(this.num(), b.den()),
                                        Ex_17_RobustRational.multiply(this.den(), b.num()));
    }

    public int compareTo(Ex_17_RobustRational that) {
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
        Ex_17_RobustRational that = (Ex_17_RobustRational) b;
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
        StdOut.println(Long.MAX_VALUE);
        StdOut.println(Long.MIN_VALUE);
        Ex_17_RobustRational r0  = new Ex_17_RobustRational(Long.MAX_VALUE, 1);
        Ex_17_RobustRational r1  = new Ex_17_RobustRational(-4, -6);
        Ex_17_RobustRational r2  = new Ex_17_RobustRational(6, -9);
        StdOut.println("arithmetic operation between " + r0 + " and " + r2);

        // StdOut.println(" + : " + (r1.plus(r0)));
        // StdOut.println(" - : " + (r0.minus(r1)));
        // TODO: In this implementation, Neither numerator nor denominator can exceed MAX_VALUE.
        // The max/min value which Rational can represent seems to be inconsistent.
        // I mean, Long.MAX_VALUE/1 is legal, but (Long.MAX_VALUE + 1)/30000 is illegal.
        // although the second one is smaller than the first one which make it kind of confusing.
        // Maybe it will be better if we represent it as a mixed fraction like 2 + 3/4.
        StdOut.println(" * : " + (r0.times(r1)));
        StdOut.println(" / : " + (r0.divides(r1)));

        StdOut.println("Are they equal? " + (r1.equals(r2)) + " " + (r2.equals(r1)));
    }
}
