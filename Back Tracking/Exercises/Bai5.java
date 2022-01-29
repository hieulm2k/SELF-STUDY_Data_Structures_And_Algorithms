package Exercises;

import java.util.Scanner;

/**
 * Liệt kê tất cả các tập con của tập S gồm n số nguyên {S[1], S[2], ... , S[n]}
 * nhập vào từ bàn phím.
 */
public class Bai5 {
    public static int[] x;
    public static int n, k;

    public static void printResult() {
        System.out.printf("{");
        for (int i = 0; i < k - 1; ++i) {
            System.out.printf("%d, ", x[i]);
        }
        System.out.printf("%d}", x[k - 1]);
        System.out.println();
    }

    public static void tryValue(int z) {
        for (int i = 1; i <= n; ++i)
            for (int j = 1; j <= n; ++j) {
                x[z] = j;
                if (z == k - 1) {
                    printResult();
                    k++;
                    x = new int[k];
                } else
                    tryValue(i + 1);
            }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();
        k = 1;
        x = new int[k];
        tryValue(0);
    }
}