package chapter4.section1;

/**
 * Takes a source vertex s as argument and computes paths from s to
 * each vertex connected to s.
 */
public interface Paths {

    /**
     * Is there a path from s to v?
     */
    boolean hasPathTo(int v);

    /**
     * Path from s to v; null if no such path
     */
    Iterable<Integer> pathTo(int v);
}
