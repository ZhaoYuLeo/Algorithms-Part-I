package chapter1.section1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

public class Ex_21 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        List<Integer> a = new ArrayList<Integer>();
        List<Integer> b = new ArrayList<Integer>();
        StdOut.println("Please enter the messages consisting of a series of entries with one name and two integers line by line:");
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            int info1 = StdIn.readInt();
            int info2 = StdIn.readInt();
            names.add(name); 
            a.add(info1);
            b.add(info2);
        }
        StdOut.printf("%10s %8s %8s %8s\n", "Names", "Num1", "Num2", "Result");
        for (int i = 0; i < names.size(); i += 1) {
            StdOut.printf("%10s %8d %8d %8.3f\n", names.get(i), a.get(i), b.get(i), 1.0 * a.get(i) / b.get(i));
        }
    }
}
