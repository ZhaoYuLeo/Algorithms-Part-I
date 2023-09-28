package chapter3.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

/**
 * Implement ST by keeping the keys and values in parallel arrays. 
 * Keys are kept in an ordered array so we can use indices to reduce the number of
 * compares required for each search. The put() operation moves larger keys one
 * position to the right before growing the table. And we use array resizing so that
 * clients do not have to be concerned with the size of the array. (slow to use with
 * large arrays).
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_SIZE = 8;

    private Key[] keys;
    private Value[] vals;
    private int N = 0;
    
    public BinarySearchST() {
        keys = (Key[])   new Comparable[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }

    private void resize(int capacity) {
        assert capacity > N : "lose data";

        Key[]   keysTemp = (Key[])    new Comparable[capacity];
        Value[] valsTemp = (Value[])  new Object[capacity];

        for (int i = 0; i < N; i += 1) {
            keysTemp[i] = keys[i];
            valsTemp[i] = vals[i];
        }
        keys = keysTemp;
        vals = valsTemp;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = keys[mid].compareTo(key);
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
            // don't support
            return;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if (N == keys.length) {
            resize(2 * N);
        }
        for (int j = N; j > i; j -= 1) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        N += 1;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        if (key == null || isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            // keep the array ordered
            for (int j = i; j < N - 1; j += 1) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            keys[N - 1] = null;
            vals[N - 1] = null;
            N -= 1;
            if (N > 1 && N == keys.length / 4) {
                resize(2 * N);
            }
        }
    }

    public Key min() {
        if (isEmpty()) {
            // we don't need it acutally since the length won't be zero and we clear the items deleted
            return null;
        }
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) {
            // in case
            return null;
        }
        return keys[N - 1];
    }

    public Key select(int k) {
        // happy, always so simply in an array
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i =  rank(key);
        return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i += 1) {
            q.enqueue(keys[i]);
        }
        // can be avoided
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        for (int i = 0; i < str.length; i += 1) {
            st.put(str[i], i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
        StdOut.println("The size is: " + st.size());

        String[] without = {"X", "B", "F", "", "Y", "T", "a"};
        for (int i = 0; i < without.length; i += 1) {
            StdOut.println("The ceiling of " + without[i] +" is: " + st.ceiling(without[i]));
            StdOut.println("The floor of " + without[i] +" is: " + st.floor(without[i]));
        }

        for (int i = 0; i < 3; i += 1) {
            String key = str[StdRandom.uniform(str.length)];
            st.delete(key);
            StdOut.println("Randomly delete key: " + key);
        }
        StdOut.println("The size is: " + st.size());

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
