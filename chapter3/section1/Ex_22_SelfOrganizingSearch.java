package chapter3.section1;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * A self-organizing search algorithm is one that rearranges items to make those that are accessed frequently
 * likely to be found early in the search. Modify your search implementation for Exercise 3.1.2 to perform the
 * following action on every search hit: move the key-value pair found to the beginning of the list, moving
 * all pairs between the beginning of the list and the vacated position to the right one position. This procedure 
 * is called the move-to-front heuristic.
 */
public class Ex_22_SelfOrganizingSearch<Key, Value> implements ST<Key, Value> {
    private static final int INIT_SIZE = 8;

    private Key[] keys;     // keys
    private Value[] vals;   // values
    private int N = 0;      // size

    public Ex_22_SelfOrganizingSearch() {
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

    // The number of elements less than key
    private int rank(Key key) {
        for (int i = 0; i < N; i += 1) {
            if (key.equals(keys[i])) {
                return i;
            }
        }
        return -1;
    }

    // move key-value pair at the given index to the beginning of the list and all others to the right one position
    private void moveToFront(int index) {
        Key   tempKey = keys[index];
        Value tempVal = vals[index];

        for (int j = index; j > 0; j -= 1) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[0] = tempKey;
        vals[0] = tempVal;
    }

    public void put(Key key, Value val) {
        if (key == null || val == null) {
            return; // not support
        }
        // Or find the key and replace it like behaved in SequentialSearchST
        int index = rank(key);

        if (index != -1) {
            // search hit
            moveToFront(index);
            vals[0] = val;
            return;
        }

        if (keys.length == N) {
            resize(2 * N);
        }

        keys[N] = key;
        vals[N] = val;
        N += 1;
    }

    public Value get(Key key) {
        int index = rank(key);
        if (index != -1) {
            // search hit
            moveToFront(index);
            return vals[0];
        }
        // search miss
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
        int index = rank(key);
        if (index != -1) {
            // search hit
            moveToFront(index);
            return true;
        } else {
            // search miss
            return false;
        }
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
        Ex_22_SelfOrganizingSearch<String, Integer> st = new Ex_22_SelfOrganizingSearch<>();
        for (int i = 0; !StdIn.isEmpty(); i += 1) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
