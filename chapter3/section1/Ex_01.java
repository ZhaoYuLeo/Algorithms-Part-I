package chapter3.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Write a client that creates a symbol table mapping letter grades to numerical scores,
 * as in the table below, then reads from standard input a list of letter grades and computes 
 * and prints the GPA (the average of the numbers corresponding to the grades).
 * | A+   | A    | A-   | B+   | B    | B-   | C+   | C    | C-   | D    | F    |
 * | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
 * | 4.33 | 4.00 | 3.67 | 3.33 | 3.00 | 2.67 | 2.33 | 2.00 | 1.67 | 1.00 | 0.00 |
 */
public class Ex_01 {
    public static void main(String[] args) {
        // create a symbol table mapping letter grades to numerical scores.
        ST<String, Double> grades = new Ex_05_SequentialSearchST<>();
        grades.put("A+", 4.33);
        grades.put("A", 4.00);
        grades.put("A-", 3.67);
        grades.put("B+", 3.33);
        grades.put("B", 3.00);
        grades.put("B-", 2.67);
        grades.put("C-", 2.33);
        grades.put("C-", 2.00);
        grades.put("C+", 1.67);
        grades.put("D", 1.00);
        grades.put("F", 0.00);

        // read letter grades from standard input and computes and prints the GPA.
        int n;
        double total = 0.0;
        for (n = 0; !StdIn.isEmpty(); n += 1) {
            String key = StdIn.readString();
            total += grades.get(key);
        }

        StdOut.println("GPA= " + (total / n));

    }
}


