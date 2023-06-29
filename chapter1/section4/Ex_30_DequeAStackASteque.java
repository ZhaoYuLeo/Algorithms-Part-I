package chapter1.section4;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Implement a deque with a stack and a steque.  Each deque operation takes
 * a constant amortized number of stack and steque operations.
 */
public class Ex_30_DequeAStackASteque<Item> {
    Ex_29_StequeWithTwoStacks<Item> left;
    Stack<Item> right;
    int cost;

    public Ex_30_DequeAStackASteque() {
        left = new Ex_29_StequeWithTwoStacks<>();
        right = new Stack<>();
    }

    public boolean isEmpty() {
        this.cost = 2;
        return left.isEmpty() && right.isEmpty();
    }

    public int size() {
        this.cost = 2;
        return left.size() + right.size();
    }

    public void pushLeft(Item item) {
        this.cost = 1;
        left.push(item);
    }

    public void pushRight(Item item) {
        this.cost = 1;
        right.push(item);
    }

    public Item popLeft() {
        if (isEmpty()) {
            throw new RuntimeException("no element to pop out.");
        }
        this.cost = 1;
        if (left.isEmpty()) {
            rightToLeft();
        }
        return left.pop();
    }

    public Item popRight() {
        if (isEmpty()) {
            throw new RuntimeException("no element to pop out.");
        }
        this.cost = 1;
        if (right.isEmpty()) {
            leftToRight();
        }
        return right.pop();
    }

    private void rightToLeft() {
        // Assume left steque is empty
        while(!right.isEmpty()) {
            this.cost += 2;
            left.push(right.pop());
        }
    }

    // popLeft, popRight, popLeft, popRight...., 'half' would be better.
    private void leftToRight() {
        // Assume right stack is empty
        int count = left.size();
        for (int i = 0; i < count / 2; i += 1) {
            this.cost += 2;
            left.enqueue(left.pop());
        }
        for (int i = count / 2; i < count; i += 1) {
            this.cost += 2;
            right.push(left.pop());
        }
    }

    public int cost() {
        return cost;
    }

    public static void main(String[] args) {
        // Test the behaviors of methods.
        Ex_30_DequeAStackASteque<Integer> d = new Ex_30_DequeAStackASteque<>();
        d.pushLeft(1);
        d.pushLeft(2);
        d.pushLeft(3);
        d.pushLeft(4);
        StdOut.println(d.popRight());
        StdOut.println(d.popRight());
        StdOut.println(d.popLeft());
        StdOut.println(d.popLeft());
        d.pushRight(5);
        d.pushRight(6);
        d.pushRight(7);
        d.pushRight(8);
        StdOut.println(d.popLeft());
        StdOut.println(d.popRight());
        StdOut.println(d.popLeft());
        StdOut.println(d.popRight());
        // Random Test and Draw Cost.
        int scale = 10000;
        int opNum = 0;
        double totalCost = 0;
        ArrayList<Integer> singleCost = new ArrayList<>(); 
        ArrayList<Double> averageCost = new ArrayList<>(); 
        Ex_30_DequeAStackASteque<Integer> rd = new Ex_30_DequeAStackASteque<>();
        for (int i = 0; i < scale; i += 1) {
            int op = StdRandom.uniform(4);
            try {
                if (op == 0) {
                    rd.pushLeft(0);
                }
                if (op == 1) {
                    rd.pushRight(1);
                }
                if (op == 2) {
                    rd.popLeft();
                }
                if (op == 3) {
                    rd.popRight();
                }
                int sc = rd.cost();
                singleCost.add(sc);
                // Side effect. The order in which expressions are evaluated is unspecified. You may get random result.
                // double avg = (sc + (averageCost.get(averageCost.size() - 1) * opNum)) / (++opNum);
                totalCost += sc;
                opNum += 1;
                averageCost.add(totalCost / opNum);
            } catch (Exception e) {
                // do nothing.
            }
        }
        // Draw cost
        StdDraw.setXscale(0, opNum);
        StdDraw.setYscale(-1, Collections.max(singleCost) + 1);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < opNum; i += 1) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(i, singleCost.get(i));
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(i, averageCost.get(i));
        }
    }
}
