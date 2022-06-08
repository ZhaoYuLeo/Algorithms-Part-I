/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[] board;
    private final int dimension;
    private final int boardSize;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        int[] arrage = { 2, 128 };
        if (tiles.length > arrage[1] || tiles.length < arrage[0]) {
            throw new IllegalArgumentException("The size of array is between 2 and 128.");
        }
        dimension = tiles.length;
        boardSize = dimension * dimension;
        board = new int[boardSize];
        int maxBorder = boardSize - 1;
        int minBorder = 0;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].length != dimension) {
                throw new IllegalArgumentException("Please enter an n-by-n array.");
            }
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] > maxBorder || tiles[i][j] < minBorder) {
                    throw new IllegalArgumentException(
                            "Please enter an array containing integers between 0 and n^2 - 1.");
                }
                board[(i * dimension) + j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder boardStringBuf = new StringBuilder();
        boardStringBuf.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boardStringBuf.append(" ");
                boardStringBuf.append(board[i * dimension + j]);
            }
            boardStringBuf.append("\n");
        }
        String boardString = boardStringBuf.toString();
        return boardString;
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int hammingDis = 0;
        for (int i = 0; i < boardSize; i++) {
            if (board[i] != 0 && (board[i] != (i + 1))) {
                hammingDis++;
            }
        }
        return hammingDis;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDis = 0;
        for (int i = 0; i < boardSize; ++i) {
            int convert = (board[i] != 0) ? Math.abs(board[i] - 1) : board[i];
            // manhattan distance is the sum of the vertical and horizontal
            // distance from the tiles to their goal position.
            manhattanDis += Math.abs(convert / dimension - i / dimension)
                    + Math.abs(convert % dimension - i % dimension);
        }
        return manhattanDis;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        return this.toString().equals(y.toString());
    }

    // // all neighboring boards
    // public Iterable<Board> neighbors()


    // // a board that is obtained by exchanging any pair of tiles
    // public Board twin()

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // print board to standard output
        StdOut.println(initial.toString());

        // print hamming distance to standard output
        StdOut.println(
                "Hamming distance: " + initial.hamming());

        // print Manhattan distance to standard output
        StdOut.println(
                "Manhattan distance: " + initial.manhattan());

        // print whether the board is the goal board to standard output
        StdOut.println("Board has reached goal: " + initial.isGoal());

        // compare two boards for equality and print the result to standard output
        tiles[0][n - 1] = n * n - 1;
        Board board2 = new Board(tiles);
        StdOut.println("Same Board with \n" + board2.toString() + "\n" + initial.equals(board2));
    }
}
