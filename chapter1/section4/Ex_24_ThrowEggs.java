package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

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
     * 2^k ≈ N
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
        // The last search missess
        if (lo > building.length - 1) {
            return -1;
        }
        return lo;
    }

    /**
     * 2^(k1) ≈ F, 2^(k2) = 2^(k1 - 1), k = k1 + k2 ≈ 2k1 ≈ 2lg F.
     * ~2lg F.
     */
    public static int throwEggs2(boolean[] building) {
        if (building[0]) {
            return 0;
        }
        int floor = 1;
        while (floor < building.length && !building[floor]) {
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
        // The last search missess
        if (lo > building.length - 1) {
            return -1;
        }
        return lo;
    }

    public static void main(String[] args) {
        int scale = 1000;
        // initialize buildings
        boolean[][] buildings = new boolean[scale][];
        int[] fs = new int[scale];
        for (int n = 0; n < buildings.length; n += 1) {
            // n story building
            int length = n + 1;
            buildings[n] = new boolean[length];
            // throwing eggs from F or above will lead it broken
            int F = StdRandom.uniform(scale);
            fs[n] = F;
            for (int i = F; i < length; i += 1) {
                buildings[n][i] = true;
            }
        }
        // run test
        for (int i = 0; i < buildings.length; i += 1) {
            boolean[] building = buildings[i];
            int f_t = fs[i];
            if (f_t > building.length - 1) {
                f_t = -1;
            }
            int f1 = throwEggs(building);
            int f2 = throwEggs2(building);
            if (f1 != f_t) {
                StdOut.println("error1 occur in " + i + "th building");
            }
            if (f2 != f_t) {
                StdOut.println("error2 occur in " + i + "th building");
            }
        }
    }
}
