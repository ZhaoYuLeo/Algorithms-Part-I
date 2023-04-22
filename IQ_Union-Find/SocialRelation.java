import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Given a social network containing n members and a log file containing
 * m timestamps at which times pairs of members formed friendships.
 * Determine the earliest time at which all members in the social network
 * are connected.
 * Assume: 1. the log file is sorted by timestamp;
 * 2. the friendship is an equivalence relation.
 * Running time of the algorithm should be m log n or better and use extra
 * space proportional to n.
 */
public class SocialRelation {
    private int membersNum;
    private int friendsNum;
    private WeightedQuickUnionUF network;

    public SocialRelation(int n) {
        membersNum = n;
        friendsNum = 1;
        network = new WeightedQuickUnionUF(n);
    }

    public void connect(int p, int q) {
        if (!network.connected(p, q)) {
            network.union(p, q);
            friendsNum += 1;
        }
    }

    public boolean connected() {
        return friendsNum == membersNum;
    }

    public static void main(String[] args) {
        // our social network contains n members
        int n = StdIn.readInt();
        SocialRelation socialRelation = new SocialRelation(n);
        while (!StdIn.isEmpty()) {
            String date = StdIn.readString();
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            socialRelation.connect(q, p);
            StdOut.println(p + " made friends with: " + q + " at " + date);
            if (socialRelation.connected()) {
                StdOut.println("All members became friends at " + date);
                return;
            }
        }
    }
}
