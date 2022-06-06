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

public class BruteCollinearPoints {
    
    private final ArrayList<LineSegment> segments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
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

        // can't handle five or more collinear points. won't pass input6.txt
        // overlapping segments need to be merged
        for (int i = 0; i < scatterSize - 3; i++) {
            for (int j = i + 1; j < scatterSize - 2; j++) {
                // slopes between p and q
                double slopePQ = scatter[i].slopeTo(scatter[j]);
                for (int k = j + 1; k < scatterSize - 1; k++) {
                    // slopes between p and r
                    double slopePR = scatter[i].slopeTo(scatter[k]);
                    if (Double.compare(slopePQ, slopePR) != 0) {
                        // three points are not collinear. get another k.
                        continue;
                    }
                    for (int m = k + 1; m < scatterSize; m++) {
                        // slopes between p and s
                        double slopePS = scatter[i].slopeTo(scatter[m]);
                        if ((Double.compare(slopePQ, slopePS) == 0)) {
                            LineSegment segment = new LineSegment(scatter[i], scatter[m]);
                            segments.add(segment);
                            // no more m would be found with this k. jump out of the last loop.
                            break;
                        }
                    }
                }
            }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
