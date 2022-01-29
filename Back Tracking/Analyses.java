import java.util.Scanner;

public class Analyses {
    public static int[] a, sum;
    public static int n, k = 0;

    public static void printResult(int i) {
        System.out.printf("%d = ", n);
        for (int j = 1; j < i; ++j) {
            System.out.printf("%d + ", a[j]);
        }
        System.out.printf("%d", a[i]);
        System.out.println();
    }

    public static void tryValue(int i) {
        for (int j = a[i - 1]; j <= (n - sum[i - 1]) / 2; ++j) {
            a[i] = j;
            sum[i] = sum[i - 1] + a[i];
            tryValue(i + 1);
        }
        a[i] = n - sum[i - 1];
        printResult(i);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();

        a = new int[n + 1];
        sum = new int[n + 1];
        a[0] = 1;
        sum[0] = 0;
        tryValue(1);
    }
}