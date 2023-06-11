package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex_11_EvaluatePostfix {
    public static int evaluatePostfix(String postfix) {
        // Assume postfix is an expression without parentheses
        String[] expr = postfix.split("\\s+");

        Stack<Integer> vals = new Stack<>();
        for (String e : expr) {
            if (e.equals("+")
                        || e.equals("-")
                        || e.equals("*")
                        || e.equals("/")
                        || e.equals("sqrt")) {
                if (vals.isEmpty()) {
                    throw new IllegalArgumentException("The postfix expression is not what we expected.");
                }
                Integer v2 = vals.pop();
                if (e.equals("sqrt")) {
                    vals.push((int)Math.sqrt(v2));
                    continue;
                }
                if (vals.isEmpty()) {
                    throw new IllegalArgumentException("The postfix expression is not what we expected.");
                }
                Integer v1 = vals.pop();
                if (e.equals("+")) {
                    vals.push(v1 + v2);
                } else if (e.equals("-")) {
                    vals.push(v1 - v2);
                } else if (e.equals("*")) {
                    vals.push(v1 * v2);
                } else if (e.equals("/")) {
                    vals.push(v1 / v2);
                }
            } else {
                vals.push(Integer.parseInt(e));
            }
        }
        return vals.pop();

    }

    public static void main(String[] args) {
        String postfix = "200 2 / sqrt 2 3 + 4 15 * * +";
        StdOut.println(evaluatePostfix(postfix));
    }
}
