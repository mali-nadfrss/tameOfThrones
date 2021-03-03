package com.geektrust.family;

public class CodeTest {

    /**
     * 27%26 --> 1
     * 53%26 --> 1
     * 53/26 --> 2
     *
     * @param n
     * @return
     */
    static String alphabetConversion(int n) {

        String result = "";
        char c = 'A' - 1;
        while (n > 0) {
            int percentile = n % 26;
            if (percentile == 0) {
                n = n - 1;
                percentile = percentile + 26;
            }
            n = n / 26;
            char temp = (char) (c + percentile);
            result = temp + result;
        }
        return result;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            System.out.println(i + " " + alphabetConversion(i));
        }

    }
    // min1, min2, min3 ...... max3, max2 , max1

    int findMinProduct(int[] arr) {

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > min3 && arr[i] < max2) {
            } else {
                if (arr[i] > max2 && arr[i] < max1) {
                    max2 = arr[i];
                    continue;
                }
                if (arr[i] > max2 && arr[i] > max1) {
                    max2 = max1;
                    max1 = arr[i];
                    continue;
                }
                if (arr[i] < min3 && arr[i] > min2) {
                    min3 = arr[i];
                    continue;
                }
                if (arr[i] < min2 && arr[i] > min1) {
                    min3 = min2;
                    min2 = arr[i];
                } else {
                    min3 = min2;
                    min2 = min1;
                    min1 = arr[i];
                }

            }
        }

        int prod1 = min1 * min2 * min3;
        int prod2 = min1 * max2 * max1;
        return Integer.min(prod1, prod2);

    }

}
