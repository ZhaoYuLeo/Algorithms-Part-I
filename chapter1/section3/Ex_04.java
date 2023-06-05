package chapter1.section3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex_04 {
    public static boolean parentheses(String text) {
        char[] ps = text.toCharArray();
        Stack<Character> s = new Stack<>();
        for (char p : ps) {
            switch(p) {
                case '[':
                case '(':
                case '{':
                    s.push(p);
                    break;
            }
            if (s.isEmpty()) {
                return false;
            }
            char val;
            switch(p) {
                case ']':
                    val = s.pop();
                    if (val != '[') return false;
                    break;
                case ')':
                    val = s.pop();
                    if (val != '(') return false;
                    break;
                case '}':
                    val = s.pop();
                    if (val != '{') return false;
                    break;
            }
        }
        return s.isEmpty();
    }
    public static void main(String[] args) {
        String text = ")]{}{[()()]()}";
        StdOut.println(parentheses(text));
    }
}
