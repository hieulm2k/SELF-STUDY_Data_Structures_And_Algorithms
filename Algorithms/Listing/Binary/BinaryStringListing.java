import java.util.Scanner;

public class BinaryStringListing {
    public static int[] a;
    public static int n;

    public static void main(String[] args) {
        inputValues();
        init();
        solve();
    }

    public static void inputValues() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();
    }

    public static void init() {
        a = new int[n];
    }

    public static void solve() {
        int i;

        do {
            printResult();

            i = n - 1;
            while (i >= 0 && a[i] == 1) {
                i--;
            }

            if (i >= 0) {
                a[i] = 1;

                for (int j = i + 1; j < n; ++j) {
                    a[j] = 0;
                }
            }
        } while (i >= 0);
    }

    public static void printResult() {
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d", a[i]);
        }
        System.out.println();
    }
}