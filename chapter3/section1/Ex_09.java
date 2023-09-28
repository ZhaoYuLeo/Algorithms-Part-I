package chapter3.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Add code to FrequencyCounter to keep track of the last call to put(). Print the last
 * word inserted and the number of words that were processed in the input stream prior
 * to this insertion. Run your program for tale.txt with length cutoffs 1, 8, and 10.
 */
public class Ex_09 {
    public static void frequencyCounter(int minLen) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        int count = 0;
        String last = null;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) {
                continue;
            }
            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
            count += 1;
            last = word;
        }
        StdOut.println("The last word inserted: " + last + "; The number of words processed before: " + (count - 1));
    }

    public static void main(String[] args) {
        frequencyCounter(Integer.parseInt(args[0]));
        //The last word inserted: known; The number of words processed before: 135642
        //The last word inserted: faltering; The number of words processed before: 14345
        //The last word inserted: disfigurement; The number of words processed before: 4578
    }
}
