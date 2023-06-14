package chapter1.section3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 */
public class Ex_34_RandomBag<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 2;

    private Item[] bag; // bag entries
    private int N;      // size

    public Ex_34_RandomBag() {
        bag = (Item[]) new Object[INIT_SIZE];
        N = 0;
    }

    /**
     * is the bag empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * number of items in the bag 
     */
    public int size() {
        return N;
    }

    /**
     * resize the underlying array
     */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i += 1) {
            copy[i] = bag[i];
        }
        bag = copy;
    }

    public void add(Item item) {
        if (N == bag.length) {
            resize(2 * N);
        }
        bag[N++] = item;
    }

    /**
     * provide the items in random order (all N! permutations equally likely,
     * for each iterator.)
     */
    public Iterator<Item> iterator() {
        return new RandomBagIterator();
    }

    private class RandomBagIterator implements Iterator<Item> {
        private int count = 0;
        private Item[] a = shuffle(Arrays.copyOf(bag, N));

        public boolean hasNext() {
            return count < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[count++];
        }

        /**
         * randomly shuffle the elements in an array and return it back.
         */
        private Item[] shuffle(Item[] a) {
            int N = a.length;
            for (int i = 0; i < N; i += 1) {
                // Exchange a[i] with random element in a[i..N-1]
                int r = i + StdRandom.uniform(N - i);
                Item temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
            return a;
        }
    }

    public static void main(String[] args) {
        Ex_34_RandomBag<Integer> bag = new Ex_34_RandomBag<>();
        for (int i = 0; i < 10; i += 1) {
            bag.add(i);
        }

        StdOut.println("size is " + bag.size());
        for (Integer s : bag) {
            StdOut.print(s + " ");
        }
        StdOut.println();
    }
}
