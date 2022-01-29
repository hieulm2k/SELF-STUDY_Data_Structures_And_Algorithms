import java.util.Scanner;

public class NQueens {
    public static int[] x;
    public static boolean[] a, b, c;
    public static int n;

    public static void printResult() {
        for (int i = 0; i < n; ++i) {
            System.out.printf("{%d,%d}", i + 1, x[i] + 1);
        }
        System.out.println();
    }

    public static void tryValue(int i) {
        for (int j = 0; j < n; ++j) {
            if (a[j] == false && b[i + j] == false && c[i - j - 1 + n] == false) {
                x[i] = j;
                if (i == n - 1)
                    printResult();
                else {
                    a[j] = true;
                    b[i + j] = true;
                    c[i - j - 1 + n] = true;
                    tryValue(i + 1);
                    a[j] = false;
                    b[i + j] = false;
                    c[i - j - 1 + n] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();

        x = new int[n];
        a = new boolean[n];
        b = new boolean[2 * n - 1];
        c = new boolean[2 * n - 1];
        tryValue(0);
    }
}