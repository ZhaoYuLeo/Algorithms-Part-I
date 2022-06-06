/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // Corner cases: argument is null
        if (points == null) {
            throw new IllegalArgumentException(
                    "The argument you entered is null. Please enter an array which collects unrepeated points with coordinates between 0 and 32,767.");
        }

        int scatterSize = points.length;
        Point[] scatter = new Point[scatterSize];

        // Corner cases: point is null
        // copy parameters
        for (int i = 0; i < scatterSize; i++) {
            // when would scatter been destroyed
            if (points[i] == null) {
                throw new IllegalArgumentException(
                        "Null point has been entered. Try again without null point.");
            }
            scatter[i] = points[i];
        }

        // Corner cases: point is repeated
        Arrays.sort(scatter);
        for (int i = 0; i < scatterSize - 1; i++) {
            if (scatter[i] == scatter[i + 1]) {
                throw new IllegalArgumentException(
                        "Repeated points have been entered. Try again with all different.");
            }
        }

        // less than 4 points
        if (scatterSize < 4) {
            return;
        }

        Point[] pendingList = Arrays.copyOf(scatter, scatter.length);

        for (int p = 0; p < scatterSize; p++) {
            Arrays.sort(pendingList, scatter[p].slopeOrder());
            double slope = Double.NaN;
            int equalSlopesCount = 0;
            int startIndex = 0;
            int endIndex = 0;
            // first element in pendingList is itself. no need to compare.
            for (int i = 1; i < pendingList.length; i++) {
                double adjacentSlope = scatter[p].slopeTo(pendingList[i]);
                if ((Double.compare(adjacentSlope, slope) != 0) || (i == pendingList.length - 1)) {
                    endIndex = ((Double.compare(adjacentSlope, slope) == 0) && (i
                            == pendingList.length - 1)) ? i : (i - 1);
                    // when scatter[p] is larger, the line has been included.
                    // optimize: delete these collinear points from pending list.
                    if (equalSlopesCount > 1 && scatter[p].compareTo(pendingList[startIndex]) < 0) {
                        LineSegment segment = new LineSegment(scatter[p], pendingList[endIndex]);
                        segments.add(segment);
                    }
                    slope = adjacentSlope;
                    startIndex = i;
                    equalSlopesCount = 0;
                }
                equalSlopesCount++;
            }
            pendingList = Arrays.copyOf(scatter, scatter.length);
        }

    }

    // the number of line segments
    public int numberOfSegments() {
        return this.segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        // convert ArrayList into Array
        LineSegment[] segmentsReturn = new LineSegment[this.segments.size()];
        for (int i = 0; i < this.segments.size(); i++) {
            segmentsReturn[i] = this.segments.get(i);
        }
        return segmentsReturn;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
