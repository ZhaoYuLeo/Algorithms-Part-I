package chapter2.section2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Given three lists of N names each, devise a linearithmic algorithm to determine if there is
 * any name common to all three lists, and if so, return the first such name.
 */
public class Ex_21_Triplicates {

    public static String common(List<String> a, List<String> b, List<String> c) {
        assert a.size() == b.size() && b.size() == c.size();
        // I hope I can get some help from the order.

        int N = a.size();

        // O(Nlog(N)).
        Collections.sort(a); 
        Collections.sort(b); 
        Collections.sort(c);

        // 3-sum
        int i = 0, j = 0, k = 0;
        while (i < N && j < N && k < N) {
            String an = a.get(i);
            String bn = b.get(j);
            String cn = c.get(k);
            
            int ab = an.compareTo(bn);
            int bc = bn.compareTo(cn);
            int ac = an.compareTo(cn);

            if (ab == 0 && bc == 0) {
                return an;
            }

            if (ab <= 0 && ac <= 0) {
                i += 1;
            }

            if (ab >= 0 && bc <= 0) {
                j += 1;
            } 

            if (bc >= 0 && ac >= 0) {
                k += 1;
            }
        }

        //while (i < N && j < N) {
        //    String an = a.get(i);
        //    String bn = b.get(j);

        //    if (less(an, bn)) {
        //        i += 1;
        //    } else if (less(bn, an)) {
        //        j += 1;
        //    } else {
        //        // an is equal with bn
        //        // int k = binarySearch(c, bn);
        //        return c.get(k); 
        //    }
        //}
        return null;
    }

    public static boolean less(String a, String b) {
        return a.compareTo(b) < 0;
    }

    public static void show(List<String> a) {
        for (String i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static String brute(List<String> a, List<String> b, List<String> c) {
        for (String an : a) {
            for (String bn : b) {
                for (String cn : c) {
                    if (an.compareTo(bn) == 0 && an.compareTo(cn) == 0) {
                        return an;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Smith", "Sarah", "Jenny", "French", "William", "Nancy", "David", "Edward", "Herbert"};
        // generate three lists of N name each
        int N = Integer.parseInt(args[0]);
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        List<String> c = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            a.add(names[StdRandom.uniform(names.length)]);
            b.add(names[StdRandom.uniform(names.length)]);
            c.add(names[StdRandom.uniform(names.length)]);
        } 

        show(a);
        show(b);
        show(c);

        String commonName = common(a, b, c);
        StdOut.println(commonName);

        String bruteName = brute(a, b, c);
        StdOut.println(bruteName);
    }
}
