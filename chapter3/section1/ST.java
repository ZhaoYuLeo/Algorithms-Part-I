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

    void put(Key key, Value val);

    Value get(Key key);

//    void delete(Key key);
//
//    boolean contains(Key key);
//
//    boolean isEmpty();
//
//    int size();
//
//    Iterable<Key> keys();
}
