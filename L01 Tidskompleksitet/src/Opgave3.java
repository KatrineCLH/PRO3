package Opgaver;

import java.util.Arrays;

public class Opgave3 {
    public static void main(String[] args) {
        int[] list = {5, 10, 5, 6, 4, 9, 8};

        System.out.println(Arrays.toString(prefixAverage(list)));
    }

    public static double[] prefixAverage(int[] inputTal) {
        double[] result = new double[inputTal.length];
        double sum = 0;
        for (int i = 0; i < inputTal.length; i++) {
            sum += inputTal[i];
            result[i] = sum / (i + 1);
        }
        return result;
    }
    }
