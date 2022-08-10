package Exercises;

import java.util.Scanner;

/**
 * Viết chương trình liệt kê các chỉnh hợp lặp chập k của n phần tử NOTE: Bài
 * này khác bài chỉnh hợp không lặp ở chỗ không cần đánh dấu lại phần tử đã
 * duyệt (có thể trùng cho các lựa chọn sau)
 */
public class Bai2 {
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

    public static void tryValue(int i) {
        for (int j = 1; j <= n; ++j) {
            x[i] = j;
            if (i == k - 1)
                printResult();
            else
                tryValue(i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        scanner.close();

        x = new int[k];
        tryValue(0);
    }
}