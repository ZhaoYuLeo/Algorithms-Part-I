package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;

/**
 * Suppose that you have an N-story building and plenty of eggs.
 * Suppose also that an egg is broken if it is thrown off floor F
 * or higher, and unhurt otherwise
 * ~lg N eggs break when using ~lg N throws
 * reduce the cost to ~2lg F.
 *
 * top down or bottom up since eggs don't break in lower floor.
 */
public class Ex_24_ThrowEggs {
    /**
     * Given a boolean array to represent building. The floor is
     * the index. If the egg is broken, building[floor] == true.
     */
    public static int throwEggs(boolean[] building) {
        int lo = 0;
        int hi = building.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (building[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static int throwEggs2(boolean[] building) {
        if (building[0]) {
            return 0;
        }
        int floor = 1;
        while (floor <= building.length && !building[floor]) {
            floor = floor << 1;
        }
        int lo = floor >> 1;
        int hi = Math.min(building.length - 1, floor);
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (building[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        int F = Integer.parseInt(args[0]);
        boolean[] building = new boolean[45];
        for (int i = F; i < building.length; i += 1) {
            building[i] = true;
        }
        StdOut.println(throwEggs2(building));
    }
}
