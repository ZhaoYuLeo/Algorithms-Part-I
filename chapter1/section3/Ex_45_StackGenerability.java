package chapter1.section3;


import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Suppose that we have a sequence of inermixed push and pop operations like "0 - 1 - 2 3 4 - - -":
 * 1. determines whether the intermixed sequence causes the stack to underflow. 
 * (space independent of N - you cannot store the integers in a data structure.)
 * as with our test stack client:
 * 2. determines whether a given permutation can by generated as output by our test client.
 */
public class Ex_45_StackGenerability {

    /**
     * Solution: The stack does not overﬂow unless there exists an integer k such that the ﬁrst
     * k pop operations occur before the ﬁrst k push operations.
     */
    public static boolean willUnderflow(String [] ops) {
        int count = 0;
        for (String o : ops) {
            if (o.equals("-")) {
                count -= 1;
            } else {
                count += 1;
            }
        }
        return count < 0;
    }

    /**
     * Solution: If a given permutation can be generated, it is uniquely generated as follows:
     * if the next integer in the output permutation is in the top of the stack, pop it;
     * otherwise, push it onto the stack.
     * Prove that a permutation can be generated by a stack (as in the previous question) if and
     * only if it has no forbidden triple (a, b, c). (possibly with other intervening integers 
     * between c and a and between a and b).
     * Partial solution: Suppose that there is a forbidden triple (a, b, c).
     * Item c is popped before a and b, but a and b are pushed before c. Thus, when c is pushed, 
     * both a and b are on the stack. Therefore, a cannot be popped before b.
     */
    // permutation: 1 2 3 4 5 6 9 8 7 0
    // permutation: 0 4 6 5 3 8 1 7 2 9
    public static boolean canGenerated(String [] permutation) {
        // Assume each integer in [0,...,N-1] exists and only exists once since it's a permutation.

        int biggest = Integer.parseInt(permutation[0]);
        int ceiling = biggest; // top of the stack

        for (String s : permutation) {
            int v = Integer.parseInt(s);
            // adding to the stack
            if (v > biggest) {
                biggest = v;
                ceiling = v;
            } else {
                // If we are popping out, we should pop item below the ceiling 
                if (v > ceiling) {
                    return false;
                } else {
                    ceiling = v;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] ops = {"0 1 2 - - 4 5 - 6 - - -",
                        "0 1 2 3 - - - - 4 - - - 5 - 6 - - 7 8",
                        "0 - 1 2 - - 4 5 - 6 - 7 8 - -"};

        for (String s : ops) {
            StdOut.println(s + " will underflow: " + willUnderflow(s.split("\\s+")));
        }

        String[] permutations = {"4 3 2 1 0 9 8 7 6 5",
                                 "4 6 8 7 5 3 2 9 0 1",
                                 "2 5 6 7 4 8 9 3 1 0",
                                 "4 3 2 1 0 5 6 7 8 9",
                                 "1 2 3 4 5 6 9 8 7 0",
                                 "0 4 6 5 3 8 1 7 2 9",
                                 "1 4 7 9 8 6 5 3 0 2",
                                 "2 1 4 3 6 5 8 7 9 0"};
        for (String s : permutations) {
            StdOut.println(s + " can be generated: " + canGenerated(s.split("\\s+")));
        }
    }
}