package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex_11_EvaluatePostfix {
    public static int evaluatePostfix(String postfix) {
        String[] expr = postfix.replaceAll("\\s+", "").split("\\s+");

        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        for (String e : expr) {
            if (e.equals("(")) {
            } else if (e.equals("+")
                        || e.equals("-")
                        || e.equals("*")
                        || e.equals("/")
                        || e.equals("sqrt")) {
                ops.push(e);
            } else if (e.equals(")")) {
                if (vals.isEmpty() || ops.isEmpty()) {
                    throw new IllegalArgumentException("The postfix expression is not what we expected.");
                }
                String op = ops.pop();
                Integer v = vals.pop();
                if (op.equals("sqrt")) {
                    vals.push((int)Math.sqrt(v));
                } else if (op.equals("+")) {
                    vals.push(vals.pop() + v);
                } else if (op.equals("-")) {
                    vals.push(vals.pop() - v);
                } else if (op.equals("*")) {
                    vals.push(vals.pop() * v);
                } else if (op.equals("/")) {
                    vals.push(vals.pop() / v);
                }
            } else {
                vals.push(Integer.parseInt(e));
            }
        }
        return vals.pop();

    }

    public static void main(String[] args) {
        String postfix = "(1 ((2 3 +) (4 5 *) *) +)";
        StdOut.println(evaluatePostfix(postfix));
    }
}
