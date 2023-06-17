package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * buffer in a text editor
 */
public class Ex_44_TextEditorBuffer implements Iterable<Character> {
    private Stack<Character> left;
    private Stack<Character> right;


    public Ex_44_TextEditorBuffer() {
        left = new Stack<>();
        right = new Stack<>();
    }

    /**
     * is the buffer empty?
     */
    public boolean isEmpty() {
        return left.isEmpty() && right.isEmpty();
    }


    /**
     * number of characters in the buffer
     */
    public int size() {
        return left.size() + right.size();
    }

    /**
     * insert c at the cursor position
     */
    public void insert(char c) {
        //--> - 
        //    - 
        //    - 
        left.push(c);
    }

    /**
     * delete and return the character at the cursor
     */
    public char delete() {
        //--> -
        //    -
        return left.pop();
    }

    /**
     * move the cursor k positions to the left
     */
    public void left(int k) {
        //    -
        //--> -
        //    -
        while(k > 0) {
            right.push(left.pop());
            k -= 1;
        }
    }

    /**
     * move the cursor k positions to the right
     */
    public void right(int k) {
        //--> -
        //    -
        //    -
        //    -
        while(k > 0) {
            left.push(right.pop());
            k -= 1;
        }
    }

    public Iterator<Character> iterator() {
        return new EditorBufferIterator();
    }

    private class EditorBufferIterator implements Iterator<Character> {
        private Iterator<Character> leftIter = left.iterator();
        private Iterator<Character> rightIter = right.iterator();


        public boolean hasNext() {
            return leftIter.hasNext() || rightIter.hasNext();
        }

        // Not good enough. The content in the buffer is not clear.
        public Character next() {
            if (leftIter.hasNext()) {
                return leftIter.next();
            } else if (rightIter.hasNext()) {
                return rightIter.next();
            } else {
                throw new NoSuchElementException();
            }
        }
    }


    public static void main(String[] args) {
        Ex_44_TextEditorBuffer buffer = new Ex_44_TextEditorBuffer();
        buffer.insert('b');
        buffer.insert('k');
        buffer.insert('s');
        buffer.insert('e');
        buffer.left(3);
        buffer.insert('o');
        buffer.insert('o');
        buffer.right(3);
        buffer.delete();
        buffer.insert('.');


        for (Character c : buffer) {
            StdOut.print(c);
        }

        StdOut.println("\nsize is " + buffer.size());
    }
}
