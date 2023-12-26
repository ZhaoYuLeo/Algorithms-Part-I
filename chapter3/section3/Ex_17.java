package chapter3.section3;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import chapter3.section2.Ex_38_TreeDrawing;
/**
 * Generate two random 16-node red-black BSTs. Draw them (either by hand or
 * with a program). Compare them with the (unbalanced) BSTs built with the 
 * same keys.
 */
public class Ex_17 {
    public static void main(String[] args) {
        // Generate 16 random characters
        int[] randomItem = new int[16];
        for (int i = 0; i < 15; i += 1) {
            randomItem[i] = i;
        }
        StdRandom.shuffle(randomItem);

        // % java-algs4 chapter3/section3/Ex_17 f

        String showAnimation = args[0];
        if (showAnimation.equals("f")) {
            String item = "";
            RedBlackBST<String, Integer> rbst = new RedBlackBST<>();  
            for (int i = 0; i < 15; i += 1) {
                item = String.valueOf((char)('A' + randomItem[i]));
                rbst.put(item, i);
            }
            rbst.draw(item);
            Ex_38_TreeDrawing<String , Integer> st = new Ex_38_TreeDrawing<>();
            for (int i = 0; i < 15; i += 1) {
                item = String.valueOf((char)('A' + randomItem[i]));
                st.put(item, i);
            }
            st.draw();
        } else {
            StdDraw.enableDoubleBuffering();
            while (true) {
                RedBlackBST<String, Integer> rbst = new RedBlackBST<>();  
                String item = "";
                for (int i = 0; i < 15; i += 1) {
                    item = String.valueOf((char)('A' + randomItem[i]));
                    rbst.put(item, i);
                }
                StdDraw.clear();
                rbst.draw(item);
                StdDraw.show();
                StdDraw.pause(3000);
                Ex_38_TreeDrawing<String , Integer> st = new Ex_38_TreeDrawing<>();
                for (int i = 0; i < 15; i += 1) {
                    item = String.valueOf((char)('A' + randomItem[i]));
                    st.put(item, i);
                }
                StdDraw.clear();
                st.draw();
                StdDraw.show();
                StdDraw.pause(3000);
            }
        }
    }
}
