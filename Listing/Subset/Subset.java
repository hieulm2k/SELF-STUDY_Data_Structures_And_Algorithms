import java.util.Scanner;

public class Subset {
    public static int[] a;
    public static int n;
    public static int k;

    public static void main(String[] args) {
        inputValues();
        init();
        solve();
    }

    public static void inputValues() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        scanner.close();
    }

    public static void init() {
        a = new int[k];

        for (int i = 0; i < k; ++i) {
            a[i] = i + 1;
        }
    }

    public static void solve() {
        int i;
        do {
            printResult();

            i = k - 1;
            while (i >= 0 && a[i] == n - k + i + 1) {
                i--;
            }

            if (i >= 0) {
                a[i]++;

                for (int j = i + 1; j < k; ++j) {
                    a[j] = a[j - 1] + 1;
                }
            }
        } while (i >= 0);
    }

    public static void printResult() {
        for (int i = 0; i < k; ++i) {
            System.out.printf("%d", a[i]);
        }
        System.out.println();
    }
}