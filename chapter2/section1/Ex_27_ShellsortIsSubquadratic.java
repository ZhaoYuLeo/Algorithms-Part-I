package chapter2.section1;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Use SortCompare to compare shellsort with insertion sort and selection
 * sort on your computer. Use array sizes that increasing powers of 2,
 * starting at 128.
 */
public class Ex_27_ShellsortIsSubquadratic {
    private static final int initialSize = 128;

    public static double time(Sort alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        alg.sort(a);
        return timer.elapsedTime();
    }

    public static ArrayList<Double> timeExpInput(Sort alg, int T, int N) {
        ArrayList<Double> time = new ArrayList<>();

        for (int size = initialSize; size < Math.pow(2, N); size += size) {
            Double total = 0.0;
            Double[] a = new Double[size];
            for (int t = 0; t < T; t += 1) {
                for (int i = 0; i < size; i += 1) {
                    a[i] = StdRandom.uniform();
                }
                total += time(alg, a);
            }
            time.add(total / T);
        }
        return time;
    }

    public static void main(String[] args) {
        Sort alg1 = new Shellsort();
        Sort alg2 = new InsertionSort();
        Sort alg3 = new SelectionSort();

        int T = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        ArrayList<Double> times1 = timeExpInput(alg1, T, N); // total for Shellsort
        ArrayList<Double> times2 = timeExpInput(alg2, T, N); // total for Insertion sort 
        ArrayList<Double> times3 = timeExpInput(alg3, T, N); // total for Selection sort 

        int size = initialSize;

        for (int i = 0; i < times1.size(); i += 1) {
            Double t1 = times1.get(i);
            Double t2 = times2.get(i);

            StdOut.printf("For %d random Doubles\n %s is", size, alg1.getName());
            StdOut.printf(" %.1f times faster than %s\n", t2/t1, alg2.getName());

            size += size;
        }

        StdOut.println();

        size = initialSize;
         
        for (int i = 0; i < times1.size(); i += 1) {
            Double t1 = times1.get(i);
            Double t3 = times3.get(i);

            StdOut.printf("For %d random Doubles\n %s is", size, alg1.getName());
            StdOut.printf(" %.1f times faster than %s\n", t3/t1, alg3.getName());

            size += size;
        }

    }
}
