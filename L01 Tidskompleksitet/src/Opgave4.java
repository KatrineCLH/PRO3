package Opgaver;

public class Opgave4 {
    public static void main(String[] args) {

        System.out.println(fibo(10));

    }

    public static int fibo(int n) {
        int first = 0;
        int second = 1;
        int sum = first + second;

        for (int i = 2; i < n; i++) {
            first = second;
            second = sum;
            sum = first + second;
        }
        return sum;
    }
}
