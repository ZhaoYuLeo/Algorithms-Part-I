package chapter1.section4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Suppose you only have two eggs, and your cost model is the number of throws.
 * the number of throws is at most 2√N,
 * reduce the cost to ~c √F

 * This is analogous to a situation where search hits (egg intact) are much cheaper
 * than misses (egg broken).
 */
public class Ex_25_ThrowTwoEggs {
    /**
     * Given a boolean array to represent building. The floor is
     * the index. If the egg is broken, building[floor] == true.
     * √N + √N + ... + √N = N
     * 2√N
     */
    public static int throwTwoEggs(boolean[] building) {
        int interval = (int)Math.sqrt(building.length);
        int ceiling = 0;
        while (ceiling < building.length && !building[ceiling]) {
            ceiling += interval;
        }
        return oneStepUp(building, ceiling, interval);
    }

    /**
     * 1 + 2 + 3 + ... + k ≌ k^2/2 ≈ F 
     * 2k = c √F
     */
    public static int throwTwoEggs2(boolean[] building) {
        int interval = 0;
        int ceiling = 0;
        while (ceiling < building.length && !building[ceiling]) {
            interval += 1;
            ceiling += interval;
        }
        return oneStepUp(building, ceiling, interval);
    }

    private static int oneStepUp(boolean[] building, int ceiling, int interval) {
        // step by step until broken
        int lo = Math.max(ceiling - interval, 0);
        int hi = Math.min(building.length - 1, ceiling);
        while (lo <= hi) {
            int mid = Math.min(lo + 1, hi);
            if (building[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        // never broken
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
            int f1 = throwTwoEggs(building);
            int f2 = throwTwoEggs2(building);
            if (f1 != f_t) {
                StdOut.println("error1 occur in " + i + "th building");
            }
            if (f2 != f_t) {
                StdOut.println("error2 occur in " + i + "th building");
            }
        }
    }
}
