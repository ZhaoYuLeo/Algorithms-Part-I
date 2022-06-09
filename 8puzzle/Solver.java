/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private final Queue<Board> solution = new Queue<Board>();
    private SearchNode goal = null;
    private boolean solved = false;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode prev;
        private final int priority;

        public SearchNode(Board board, int moves, SearchNode prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.priority = this.board.manhattan() + moves;
        }

        public Board getBoard() {
            return this.board;
        }

        public boolean isGoal() {
            return this.board.isGoal();
        }

        public int compareTo(SearchNode node) {
            return Integer.compare(this.priority, node.priority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Please enter a non-empty Board instance.");
        }
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();

        SearchNode searchNode = new SearchNode(initial, 0, null);
        SearchNode searchNodeTwin = new SearchNode(initial.twin(), 0, null);

        pq.insert(searchNode);
        pqTwin.insert(searchNodeTwin);

        SearchNode min;
        SearchNode minTwin;

        // one of these must have a solution
        while (!this.solved) {
            min = pq.delMin();
            this.solution.enqueue(min.board);
            if (min.isGoal()) {
                this.solved = true;
                this.goal = min;
                break;
            }
            for (Board n : min.board.neighbors()) {
                if (min.prev == null || !n.equals(min.prev.board)) {
                    SearchNode neighborNode = new SearchNode(n, min.moves + 1, min);
                    pq.insert(neighborNode);
                }
            }

            minTwin = pqTwin.delMin();
            if (minTwin.isGoal()) {
                break;
            }
            for (Board nT : minTwin.board.neighbors()) {
                if (minTwin.prev == null || !nT.equals(min.prev.board)) {
                    SearchNode neighborNodeTwin = new SearchNode(nT, minTwin.moves + 1, minTwin);
                    pqTwin.insert(neighborNodeTwin);
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return this.solved;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!this.solved) {
            return -1;
        }
        return this.goal.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!this.solved) {
            return null;
        }
        return this.solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println(
                    "No solution possible. move = " + solver.moves() + " solution = " + solver
                            .solution());
        }
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
