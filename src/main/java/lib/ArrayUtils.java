package lib;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {

    public int[][][] create3dIntArray(int d1, int d2, int d3, int defaultValue) {
        int results[][][] = new int[d1][d2][d3];
        fill(results, defaultValue);
        return results;
    }

    public static void fill(int arr[][][], int value) {
        for (int d1[][] : arr) {
            for (int d2[] : d1) {
                Arrays.fill(d2, value);
            }
        }
    }

    public List<Integer> toList(int a[]) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public int[][] create2dIntArray(int d1, int d2, int defaultValue) {
        int results[][] = new int[d1][d2];
        fill(results, defaultValue);
        return results;
    }

    public static void fill(int arr[][], int value) {
        for (int d1[] : arr) {
            Arrays.fill(d1, value);
        }
    }


    public static void main(String args[]) {
        Arrays.fill(new int[][]{{1, 2}}, 6);
    }
}
