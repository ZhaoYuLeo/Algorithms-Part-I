/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

/**
 * The union-find data type models a collection of sets containing
 * n elements, with each element in exactly one set.The elements
 * are named 0 through n-1.
 * Initially, there are n sets, with each element in its own set.
 * The canonical element of a set (also known as the root, identifier,
 * leader, or set representative) is one distinguished element in the
 * set.
 */
public interface UF {

    /**
     * Merges the set containing element p with the set containing
     * element q. That is, if p and q are in different sets, replace
     * these two sets with a new set that is the union of the two.
     *
     * @param p one element in set A of the collection
     * @param q one element in set B of the collection
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    void union(int p, int q);

    /**
     * Returns the same value for two elements if and only if they are
     * in the same set. Actually it returns the canonical element of
     * the set containing.
     *
     * @param p an element in set
     * @return the canonical element in set containing p
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    int find(int p);


    /**
     * Returns true if the two elements are in the same set.
     *
     * @param p one element
     * @param q one element
     * @return true if p and q are in the same set; false otherwise
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    boolean connected(int p, int q);


    /**
     * Returns the number of sets.
     *
     * @return the number of sets (between 1 and n)
     */
    int count();
}
