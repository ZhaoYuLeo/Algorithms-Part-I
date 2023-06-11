package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex_12_StackCopy {
    /**
     * Take a stack of strings as argument and move these stirngs in st to an new stack.
     */
    public static Stack<String> move(Stack<String> st) {
        Stack<String> copy = new Stack<>();
        return helper(st, copy);
    }

    /**
     * Move elements from st1 to st2.
     */
    private static Stack<String> helper(Stack<String> st1, Stack<String> st2) {
        if (st1.isEmpty()) {
            return st2;
        }
        String s = st1.pop();
        helper(st1, st2);
        st2.push(s);
        return st2;
    }

    /**
     * Takes a stack of strings as argument and returns a copy of the stack.
     */
    public static Stack<String> copy(Stack<String> st) {
        Stack<String> copy = new Stack<>();
        Stack<String> temp = new Stack<>();
        for (String s : st) {
            temp.push(s);
        }
        for (String s : temp) {
            copy.push(s);
        }
        return copy;
    }

    public static void main(String[] args) {
        Stack<String> st1 = new Stack<>();
        st1.push("them");
        st1.push("about");
        st1.push("thinking");
        st1.push("without");
        for (String s : copy(st1)) {
            StdOut.println(s);
        }

        for (String s : move(st1)) {
            StdOut.println(s);
        }
    }
}
