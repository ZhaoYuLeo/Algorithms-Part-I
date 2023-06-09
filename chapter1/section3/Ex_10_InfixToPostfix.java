package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Converts an arithmetic expression from infix to postfix.
 */
public class Ex_10_InfixToPostfix {
    public static String infixToPostfix(String infix) {
        String[] expr = infix.replaceAll("\\s+", "").split("");
        Stack<String> op = new Stack<>();
        Stack<String> vals = new Stack<>();
        for (String e : expr) {
            if (e.equals("(")) {
            } else if (e.equals("+")
                    || e.equals("-")
                    || e.equals("*")
                    || e.equals("/")) {
                op.push(e);
            } else if (e.equals(")")) {
                if (vals.isEmpty() || op.isEmpty()) {
                    // we can't pop anything out, must occurred error.
                } else {
                    String o = op.pop();
                    String v1 = vals.pop();
                    String v2 = vals.pop();
                    String post = "(" + v2 + " " + v1 + " " + o + ")";
                    vals.push(post);
                }
            } else {
                vals.push(e);
            }
        }
        return vals.pop();
    } 
    public static void main(String[] args) {
        String infix = "(1 + ((2 + 3) * (4 * 5)))";
        StdOut.println(infixToPostfix(infix));
        String postfix = "(1 ((2 3 +) (4 5 *) *) +)";
    }
}
