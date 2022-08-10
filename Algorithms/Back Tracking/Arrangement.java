import java.util.Scanner;

public class Arrangement {
    public static int[] a, check;
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
        for (int j = 1; j <= n; ++j) {
            if (check[j - 1] == 0) {
                a[i] = j;
                if (i == k)
                    printResult();
                else {
                    check[j - 1] = 1;
                    tryValue(i + 1);
                    check[j - 1] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        scanner.close();

        a = new int[n];
        check = new int[n];
        tryValue(1);
    }
}