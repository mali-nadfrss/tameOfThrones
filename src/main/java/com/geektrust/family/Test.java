package com.geektrust.family;

public class Test {

    static boolean OverlappingLands(int[][] land1, int[][] land2) {
        if (land1.length != 4 || land2.length != 4) {
            return true;
        }

        //x1, x2 , y1, y2
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            xMin = Integer.min(land1[i][0], xMin);
            yMin = Integer.min(land1[i][1], yMin);
            xMax = Integer.max(land1[i][0], xMax);
            yMax = Integer.max(land1[i][1], yMax);
        }

        for (int i = 0; i < 4; i++) {
            if (land2[i][0] <= xMax && land2[i][0] >= xMin) {
                if (land2[i][1] <= yMax && land2[i][1] >= yMin) {
                    return true;
                }
            }
            if (land2[i][1] <= yMax && land2[i][1] >= yMin) {
                if (land2[i][0] <= xMax && land2[i][0] >= xMin) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] land1 = {{0, 0}, {4, 0}, {0, 4}, {4, 4}};
        int[][] land2 = {{0, 6}, {3, 6}, {0, 10}, {3, 10}};
        System.out.println(OverlappingLands(land1, land2));
    }
}
