package lib;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayUtils {

    public static int[][][] create3dIntArray(int d1, int d2, int d3, int defaultValue) {
        int results[][][] = new int[d1][d2][d3];
        fill(results, defaultValue);
        return results;
    }

    public static short[][][] create3dShortArray(int d1, int d2, int d3, short defaultValue) {
        short results[][][] = new short[d1][d2][d3];
        fill(results, defaultValue);
        return results;
    }

    public static short[][] create3dShortArray(int d1, int d2, short defaultValue) {
        short results[][] = new short[d1][d2];
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

    public static void fill(long arr[][][], long value) {
        for (long d1[][] : arr) {
            for (long d2[] : d1) {
                Arrays.fill(d2, value);
            }
        }
    }

    public static void fill(long arr[][], long value) {
        for (long d2[] : arr) {
            Arrays.fill(d2, value);
        }

    }
    public static void fill(short arr[][][], short value) {
        for (short d1[][] : arr) {
            for (short d2[] : d1) {
                Arrays.fill(d2, value);
            }
        }
    }

    public static void fill(short arr[][], short value) {
        for (short d1[] : arr) {
            Arrays.fill(d1, value);
        }
    }

    public static List<Integer> toList(int a[]) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public static List<Long> toList(long a[]) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    public static int[][] create2dIntArray(int d1, int d2, int defaultValue) {
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

    public static int[][] create2DIntArray(int d1, int d2, int defaultValue) {
        int ret[][] = new int[d1][d2];
        fill(ret, defaultValue);
        return ret;
    }

    public static int[] reverse(int[] a) {
        int ra[] = new int[a.length];
        int index = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            ra[index++] = a[i];
        }
        return ra;
    }

    public static long[] reverse(long[] a) {
        long ra[] = new long[a.length];
        int index = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            ra[index++] = a[i];
        }
        return ra;
    }

    public static Integer[] toBoxedArray(int a[]) {
        Integer[] result = new Integer[a.length];
        for (int i = 0;i < a.length; i++) {
            result[i] = a[i];
        }
        return result;
    }


    public static char[] reverse(char[] a) {
        char ra[] = new char[a.length];
        int index = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            ra[index++] = a[i];
        }
        return ra;
    }

    public static long[][] create2DLongArray(int d1, int d2, long defaultValue) {
        long ret[][] = new long[d1][d2];
        fill(ret, defaultValue);
        return ret;
    }

    public static void shuffle(long[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            long tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }
    }

    public static void shuffle(int[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            int tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }
    }

    public static void swap(int i, int j, int a[]) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
