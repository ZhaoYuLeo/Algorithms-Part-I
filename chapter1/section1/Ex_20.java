package chapter1.section1;

public class Ex_20 {
    public static double ln(int N) {
        if (N <= 0) {
            return 0;
        } else {
            return Math.log(N) + ln(N - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("ln(1!) is: " + ln(1));
        System.out.println("ln(0!) is: " + ln(0));
        System.out.println("ln(10) is: " + ln(10));
    }
}
