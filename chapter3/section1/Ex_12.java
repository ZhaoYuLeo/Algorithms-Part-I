package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Modify BinarySearchST to maintain one array of Item objects that contain
 * keys and values, rather than two parallel arrays. Add a constructor that
 * takes an array of Item values as argument and uses mergesort to sort the
 * array.
 */
public class Ex_12<Key extends Comparable<Key>, Value> {
    private static final int INIT_SIZE = 8;
    private Item[] items;
    private int N = 0;      // the number of elements stored in the ST

    private class Item implements Comparable<Item> {
        Key key;
        Value val;

        public Item(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public Value getValue() {
            return this.val;
        }

        public Key getKey() {
            return this.key;
        }

        public int compareToKey(Key key) {
            return this.key.compareTo(key);
        }

        @Override
        public int compareTo(Item that) {
            return this.key.compareTo(that.key);
        }
    }

    public Ex_12() {
        items = (Item[]) new Ex_12.Item[INIT_SIZE];
    }

    public Ex_12(Item[] items) {
        sort(items);
        this.items = items;
        N = items.length;
    }

    private static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k += 1) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k += 1) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private void resize(int capacity) {
        Item[] itemTemp = (Item[]) new Ex_12.Item[capacity];
        for (int i = 0; i < N; i += 1) {
            itemTemp[i] = items[i];
        }
        items = itemTemp;
    }

    // The number of elements less than key
    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = items[mid].compareToKey(key);
            if (cmp < 0) {
                lo = mid + 1;
            } else if (cmp > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val) {
        if (key == null || val == null) {
            // not support null key and null value(not exist)
            return;
        }
        int i = rank(key);
        if (i < N && items[i].compareToKey(key) == 0) {
            items[i] = new Item(key, val);
            return;
        }
        if (N == items.length) {
            resize(2 * N);
        }
        for (int j = N; j > i; j -= 1) {
            items[j] = items[j - 1];
        }
        items[i] = new Item(key, val);
        N += 1;
    }

    public Value get(Key key) {
        if (key == null || isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && items[i].compareToKey(key) == 0) {
            return items[i].getValue();
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null || isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i >= N || items[i].compareToKey(key) != 0) {
            // the key not exist
            return;
        }
        for (int j = i; j < N; j += 1) {
            items[j] = items[j + 1];
        }
        items[--N] = null;
        if (N > 1 && N == items.length / 4) {
            resize(2 * N);
        }
    } 

    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return items[0].getKey();
    }

    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return items[N - 1].getKey();
    }

    public Key select(int k) {
        if (isEmpty() || k >= N) {
            throw new IllegalArgumentException("not exist");
        }
        return items[k].getKey();
    }

    public Key ceiling(Key key) {
        int i = rank(key);
        return items[i].getKey();
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i += 1) {
            q.enqueue(items[i].getKey());
        }
        // can be avoided
        if (contains(hi)) {
            q.enqueue(items[rank(hi)].getKey());
        }
        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        Ex_12<String, Integer> st = new Ex_12<>();
        String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < str.length; i += 1) {
            st.put(str[i], i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("The size is: " + st.size());
    }
}

