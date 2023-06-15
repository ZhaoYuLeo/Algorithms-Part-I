package chapter1.section3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 */
public class Ex_35_RandomQueue<Item> implements Iterable<Item> {
    private static final int INIT_SIZE = 2;

    private Item[] a; // queue entries
    private int N;     // size


    public Ex_35_RandomQueue() {
        a = (Item[]) new Object[INIT_SIZE];
        N = 0;
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * number of items in the deque
     */
    public int size() {
        return N;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i += 1) {
            copy[i] = a[i];
        }
        a = copy;
    }

    /**
     * add an item
     */
    public void enqueue(Item item) {
        if (N == a.length) {
            resize(2 * N);
        }
        a[N++] = item;
    }

    /**
     * remove and return a random item
     * (sample without replacement)
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int r = StdRandom.uniform(N);
        N -= 1;
        Item temp = a[r];
        a[r] = a[N];
        a[N] = null;

        if (N == a.length / 4) {
            resize(a.length / 2);
        }
        return temp;
    }

    /**
     * return a random item, but do not remove
     * (sample with replacement)
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int r = StdRandom.uniform(N);
        return a[r];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int count = N;
        private Item[] copy = Arrays.copyOf(a, N);

        public boolean hasNext() {
            return count > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int r = StdRandom.uniform(count);
            count -= 1;
            Item temp = copy[r];
            copy[r] =  copy[count];
            copy[count] = null;
            return temp;
        }
    }

    /**
     * Card for client test
     */
    private static class Card {
        String[] RANKS = {
            "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace"};
        String[] SUITS = {
            "Clubs", "Diamonds", "Hearts", "Spades"};

        int rank;
        int suit;

        public Card (int rank, int suit) {
            assert isValid(rank, suit);
            this.rank = rank;
            this.suit = suit;
        }

        public boolean isValid(int rank, int suit) {
            return (suit >= 0 && suit <= 3 && rank >= 0 && rank <= 12);
        }

        public String toString() {
            return SUITS[suit] + " " + RANKS[rank];
        }
    }


    public static void main(String[] args) {
        Ex_35_RandomQueue<Card> deque = new Ex_35_RandomQueue<>();
        for (int s = 0; s < 2; s += 1) {
            for (int r = 0; r < 13; r += 1) {
                deque.enqueue(new Card(r, s));
            }
        }

        StdOut.println(deque.sample() + " " + "size is " + deque.size());
        for (Card s : deque) {
            StdOut.println(s);
        }
    }
}
