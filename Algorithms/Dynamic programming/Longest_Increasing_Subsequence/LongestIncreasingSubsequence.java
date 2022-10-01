package Longest_Increasing_Subsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    private static int m;

    private static void longestIncreasingSubsequence(int[] a) {
        int n = a.length;
        int[] l = new int[n + 2];
        int[] t = new int[n + 2];
        int[] startOf = new int[n + 2];

        int[] temp = Arrays.copyOf(a, n);
        a = new int[n + 2];
        a[0] = Integer.MIN_VALUE;
        a[n + 1] = Integer.MAX_VALUE;

        System.arraycopy(temp, 0, a, 1, n);

        m = 1;
        l[n + 1] = 1;
        startOf[1] = n + 1;

        solve_bottom_up(a, l, t, startOf);
        printResult(a, t);
    }

    private static void solve_bottom_up(int[] a, int[] l, int[] t, int[] startOf) {
        for (int i = a.length - 2; i >= 0; --i) {
            int j = find(i, a, startOf);
            int k = l[j] + 1;

            if (k > m) {
                m = k;
                startOf[k] = i;
            } else {
                if (a[startOf[k]] < a[i]) {
                    startOf[k] = i;
                }
            }

            l[i] = k;
            t[i] = j;
        }
    }

    private static int find(int index, int[] a, int[] startOf) {
        int inf = 1;
        int sup = m + 1;

        do {
            int median = (inf + sup) / 2;
            int j = startOf[median];

            if (a[j] > a[index]) {
                inf = median;
            } else {
                sup = median;
            }
        } while (inf + 1 != sup);

        return startOf[inf];
    }

    private static void printResult(int[] a, int[] t) {
        System.out.println("Longest length: " + (m - 2));
        int index = t[0];

        while (index != a.length - 1) {
            System.out.printf("a[%d] = %d\n", index, a[index]);
            index = t[index];
        }
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 8, 9, 4, 5, 6, 20, 9, 10};
        longestIncreasingSubsequence(a);
    }
}