package chapter1.section1;

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.In;

public class Wget {
    public static void main(String[] args) {

        // read in data from URL
        String url = args[0];
        In in = new In(url);
        String data = in.readAll();

        // write data to a file
        String filename = url.substring(url.lastIndexOf('/') + 1);
        Out out = new Out(filename);
        out.println(data);
        out.close();
    }
}
