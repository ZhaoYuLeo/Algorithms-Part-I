package chapter3.section1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * What is the average number of distinct keys that FrequencyCounter will find among N
 * random nonnegative integers less than 1,000, for N = 10, 10^2, 10^3, 10^4, 10^5, and
 * 10^6?
 */
public class Ex_07 {
    public static int frequencyCounter(int[] array) {
        BinarySearchST<Integer, Integer> st = new BinarySearchST<>();
        for (int i = 0; i < array.length; i += 1) {
            int item = array[i];
            Integer val = st.get(item);
            if (val == null) {
                st.put(item, 1);
            } else {
                st.put(item, val + 1);
            }
        }
        return st.size();
    }

    public static int[] generateRandomArray(int size, int maxNumber) {
        int[] array = new int[size];
        for (int i = 0; i < size; i += 1) {
            array[i] = StdRandom.uniform(maxNumber);
        }
        return array;
    }

    public static void main(String[] args) {
        int MAX = 1000;
        int count = 10;
        StdOut.println("The average number of distinct keys:");
        for (int N = 10; N <= Math.pow(10, 6); N *= 10) {
            int total = 0;
            for (int i = 0; i < count; i += 1) {
                int[] array = generateRandomArray(N, MAX);
                int distinct = frequencyCounter(array);
                total += distinct;
            }
            StdOut.println("among " + N + " less than 1,000 is: " + total / count);
        }
        // The average number of distinct keys:
        // among 10 less than 1,000 is: 9
        // among 100 less than 1,000 is: 95
        // among 1000 less than 1,000 is: 631
        // among 10000 less than 1,000 is: 1000
        // among 100000 less than 1,000 is: 1000
        // among 1000000 less than 1,000 is: 1000
    }
}
