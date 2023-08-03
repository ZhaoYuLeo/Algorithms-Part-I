package chapter2.section1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import chapter1.section3.Ex_32_Steque;

/**
 * Dequeue sort. Explain how you would sort a deck of cards, with the restriction that the only allowed
 * operations are to look at the values of the top two cards, to exchange the top two cards, and to move
 * the top card to the bottom of the deck.
 */
public class Ex_14 {
    public static Ex_32_Steque<Integer> shuffleCards() {
        // Use range(13) to represent cards of the same suit which can be replace by any set comparable objects.
        int N = 13;
        int[] cs = new int[N];
        for (int i = 0; i < N; i += 1) {
            cs[i] = i;
        }

        // shuffle
        for (int i = 0; i < N; i += 1) {
            // Exchange cs[i] with random element in cs[i..N-1]
            int r = i + StdRandom.uniform(N - i);
            int temp = cs[i];
            cs[i] = cs[r];
            cs[r] = temp;
        } 

        // We can only pop top two cards, exchange the top two cards, and to move the top card to the bottom.
        Ex_32_Steque<Integer> cards = new Ex_32_Steque<>();
        for (int i = 0; i < N; i += 1) {
            cards.push(cs[i]);
        }

        return cards;
    }

    // Selection sort
    public static void sort(Ex_32_Steque<Integer> a) {
        int N = a.size();
        for (int i = 0; i < N - 1; i += 1) {
            // find maximum each time
            for (int j = i; j < N - 1; j += 1) {
                Integer top = a.pop();
                Integer next = a.pop();

                Integer min, max;
                if (top < next) {
                    min = top;
                    max = next;
                } else {
                    min = next;
                    max = top;
                }

                a.push(max);
                a.enqueue(min);

            }
            // move the sorted off the top
            for (int g = 0; g <= i; g += 1) {
                a.enqueue(a.pop());
            }
            show(a);
        }
    }

    public static void sort2(Ex_32_Steque<Integer> a) {
        int N = a.size();
        while () {
            Integer top = a.pop();
            Integer next = a.pop();

            
        }
    }

    public static void show(Ex_32_Steque<Integer> a) {
        for (int i : a) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        Ex_32_Steque<Integer> cards = shuffleCards();
        show(cards);
        sort(cards);
        show(cards);
    }
}
