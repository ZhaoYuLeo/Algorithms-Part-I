package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Takes from standard input an expression without left parentheses and 
 * prints the equivalent inﬁx expression with the parentheses inserted. 
 * For example, given the input:
 * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * should print:
 * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) )
 */
public class Ex_09 {
    public static String parenthese(String noLeftParentheses) {
        // 1 + 2 + 3) * 4) means (1 + (2 + 3) * 4) or ((1 + 2 + 3) * 4). 
        //Assume this was a fully parenthesized arithmetic expressions.
        String[] expression = noLeftParentheses.split("\\s+");

        Stack<String> ops = new Stack<>();
        Stack<String> vals = new Stack<>();

        for (String e : expression) {
            if (e.equals("(")) {
                // do nothing
            } else if (e.equals("+")
                    || e.equals("-") 
                    || e.equals("*") 
                    || e.equals("/") 
                    || e.equals("sqrt")) {
                ops.push(e);
            } else if (e.equals(")")) {
                String op = ops.pop();
                String v = vals.pop();
                if (op.equals("+")
                    || op.equals("-")
                    || op.equals("*")
                    || op.equals("/")) {
                    v = String.format("(%s %s %s)",vals.pop(), op,  v);
                } else if (op.equals("sqrt")) {
                    v = String.format("(%s %s)", op, v);
                }
                vals.push(v);
            } else {
                vals.push(e);
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        String noLeftParentheses = "1 +  ( 2 + 3 ) *  4 * 5 ) ) )";
        StdOut.println(parenthese(noLeftParentheses));
    }
}
