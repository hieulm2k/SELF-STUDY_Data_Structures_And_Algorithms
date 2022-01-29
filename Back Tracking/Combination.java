import java.util.Scanner;

public class Combination {
    public static int[] a;
    public static int n, k;

    public static void printResult() {
        System.out.printf("{");
        for (int i = 1; i <= k - 1; ++i) {
            System.out.printf("%d, ", a[i]);
        }
        System.out.printf("%d}", a[k]);
        System.out.println();
    }

    public static void tryValue(int i) {
        for (int j = a[i - 1] + 1; j <= n - k + i; ++j) {
            a[i] = j;
            if (i == k)
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

        a = new int[n + 1];
        a[0] = 0;
        tryValue(1);
    }
}