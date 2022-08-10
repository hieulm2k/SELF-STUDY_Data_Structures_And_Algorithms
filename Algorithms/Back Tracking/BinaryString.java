import java.util.Scanner;

public class BinaryString {
    public static int[] a;
    public static int n;

    public static void printResult() {
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d", a[i]);
        }
        System.out.println();
    }

    public static void tryValue(int i) {
        for (int j = 0; j <= 1; ++j) {
            a[i] = j;
            if (i == n - 1)
                printResult();
            else
                tryValue(i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();
        a = new int[n];
        tryValue(0);
    }
}