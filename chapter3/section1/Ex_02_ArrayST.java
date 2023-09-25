package chapter3.section1;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Develop a symbol-table implementation ArrayST that uses an (unordered) array as the underlying data
 * structure to implement our basic symbol-table API.
 */
public class Ex_02_ArrayST<Key, Value> implements ST<Key, Value> {
    private static final int INIT_SIZE = 8;

    private Key[] keys;     // keys
    private Value[] vals;   // values
    private int N = 0;      // size

    public Ex_02_ArrayST() {
        keys = (Key[])   new Object[INIT_SIZE];
        vals = (Value[]) new Object[INIT_SIZE];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int capacity) {
        assert capacity >= N : "Lose data";
        Key[] keysTemp = (Key[])   new Object[capacity];
        Value[] valsTemp = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i += 1) {
            keysTemp[i] = keys[i];
            valsTemp[i] = vals[i];
        }
        keys = keysTemp;
        vals = valsTemp;
    }

    public void put(Key key, Value val) {
        // Or find the key and replace it like behaved in SequentialSearchST
        delete(key);

        if (keys.length == N) {
            resize(2 * N);
        }

        keys[N] = key;
        vals[N] = val;
        N += 1;
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i += 1) {
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        for (int i = 0; i < N; i += 1) {
            if (key.equals(keys[i])) {
                // not keep order
                keys[i] = keys[N - 1];
                vals[i] = vals[N - 1];
                N -= 1;
                keys[N] = null;
                vals[N] = null;
                if (N > 0 && keys.length / 4 == N) {
                    resize(2 * N);
                }
                return;
            }
        }
    }

    public boolean contains(Key key) {
        for (int i = 0; i < N; i += 1) {
            if (key.equals(keys[i])) {
                return true;
            }
        }
        return false;
    }

    public Iterable<Key> keys() {
        return new Keys();
    }

    final class Keys implements Iterable<Key> {
        public Iterator<Key> iterator() {
            return new keysIterator();
        }

        private class keysIterator implements Iterator<Key> {
            private int i = 0;
            public boolean hasNext() {
                return i < N;
            }
            public Key next() {
                return keys[i++];
            }
            public void remove() {
                // prevent
            }
        }
    }


    public static void main(String[] args) {
        Ex_02_ArrayST<String, Integer> st = new Ex_02_ArrayST<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
