/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] siteStatus;
    private int openSites;
    private int virtualTop;
    private int virtualBottom;
    private int gridSide;
    // once virtualTop is connected to virtualBottom, uf cannot find out if the site is full.
    private WeightedQuickUnionUF ufFull;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        gridSide = n;
        int gridSquare = n * n;
        siteStatus = new boolean[gridSquare];
        openSites = 0;
        virtualTop = gridSquare;
        virtualBottom = gridSquare + 1;
        ufFull = new WeightedQuickUnionUF(gridSquare + 1);
        uf = new WeightedQuickUnionUF(gridSquare + 2);
    }

    private int getIndex(int row, int col) {
        return gridSide * (row - 1) + col - 1;
    }

    private void validateSite(int row, int col) {
        if (!siteOnGrid(row, col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    private boolean siteOnGrid(int row, int col) {
        return (row > 0 && col > 0 && row <= gridSide && col <= gridSide);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateSite(row, col);

        if (isOpen(row, col)) {
            return;
        }

        int index = getIndex(row, col);

        if (row == 1) {
            uf.union(virtualTop, index);
            ufFull.union(virtualTop, index);
        }

        if (row == gridSide) {
            uf.union(virtualBottom, index);
        }

        if (siteOnGrid(row - 1, col) && isOpen(row - 1, col)) {
            uf.union(getIndex(row - 1, col), index);
            ufFull.union(getIndex(row - 1, col), index);
        }

        if (siteOnGrid(row + 1, col) && isOpen(row + 1, col)) {
            uf.union(getIndex(row + 1, col), index);
            ufFull.union(getIndex(row + 1, col), index);
        }

        if (siteOnGrid(row, col - 1) && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col - 1), index);
            ufFull.union(getIndex(row, col - 1), index);
        }

        if (siteOnGrid(row, col + 1) && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col + 1), index);
            ufFull.union(getIndex(row, col + 1), index);
        }

        siteStatus[index] = true;
        openSites++;
    }

    // is the site (row, col) open
    public boolean isOpen(int row, int col) {
        validateSite(row, col);
        return siteStatus[getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateSite(row, col);
        return ufFull.find(getIndex(row, col)) == ufFull.find(virtualTop);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(virtualTop) == uf.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int size = StdIn.readInt();
        Percolation percolation = new Percolation(size);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            percolation.open(p, q);
            boolean result = percolation.percolates();
            String resultString = result ? "The System percolates" : "Does not percolate";
            StdOut.println(p + " ," + q + " is opened;\n" + resultString);
        }
    }
}
