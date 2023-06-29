package chapter1.section4;

import java.util.ArrayList;
import java.util.Collections;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Implement a deque with three stacks so that each deque operation takes
 * a constant amortized number of stack operations.
 */
public class Ex_31_DequeWithThreeStacks<Item> {
    Stack<Item> left;
    Stack<Item> temp;
    Stack<Item> right;
    //TODO: not good. once you call a method inside another one, you will get an wrong answer.
    int cost; // cost per operation

    public Ex_31_DequeWithThreeStacks() {
        left = new Stack<>();
        temp = new Stack<>();
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

    // TODO: rightToLeft and leftToRight achieve the same goal. U don't need two.
    private void rightToLeft() {
        // Assume left stack and temp stack are both empty
        assert left.isEmpty() && temp.isEmpty();
        int count = right.size();
        for (int i = 0; i < count / 2; i += 1) {
            this.cost += 2;
            temp.push(right.pop());
        }
        for (int i = count / 2; i < count; i += 1) {
            this.cost += 2;
            left.push(right.pop());
        }
        for (int i = 0; i < count / 2; i += 1) {
            this.cost += 2;
            right.push(temp.pop());
        }
    }

    private void leftToRight() {
        // Assume right stack and temp stack are both empty
        assert right.isEmpty() && temp.isEmpty();
        int count = left.size();
        for (int i = 0; i < count / 2; i += 1) {
            this.cost += 2;
            temp.push(left.pop());
        }
        for (int i = count / 2; i < count; i += 1) {
            this.cost += 2;
            right.push(left.pop());
        }
        for (int i = 0; i < count / 2; i += 1) {
            this.cost += 2;
            left.push(temp.pop());
        }
    }

    public int cost() {
        return this.cost;
    }

    public static void main(String[] args) {
        // Test the behaviors of methods.
        Ex_31_DequeWithThreeStacks<Integer> d = new Ex_31_DequeWithThreeStacks<>();
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
        // TODO: duplicate code. maybe you can move it into another class.
        int scale = 10000;
        int opNum = 0;
        double totalCost = 0;
        ArrayList<Integer> singleCost = new ArrayList<>();
        ArrayList<Double> averageCost = new ArrayList<>();
        Ex_31_DequeWithThreeStacks<Integer> rd = new Ex_31_DequeWithThreeStacks<>();
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
