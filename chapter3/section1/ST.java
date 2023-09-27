/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package chapter3.section1;

/**
 * @param <Key>
 * @param <Value>
 */
public interface ST<Key, Value> {

    /** 
     * put key-value pair into the table
     * (remove key from table if value is null)
     */
    void put(Key key, Value val);

    /** value paired with key (null if key is absent) */
    Value get(Key key);

    /** remove key (and its value) from table */
    void delete(Key key);

    /** number of key-value pairs in the table */
    int size();

    /** all the keys in the table */
    Iterable<Key> keys();

    /** is there a value paired with key? */
    default boolean contains(Key key) {
        return get(key) != null;
    }

    /** is the table empty? */
    default boolean isEmpty() {
        return size() == 0;
    }
}

