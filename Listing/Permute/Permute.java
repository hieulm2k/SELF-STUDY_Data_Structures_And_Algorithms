import java.util.Scanner;

public class Permute {
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

        for (int i = 0; i < n; ++i) {
            a[i] = i + 1;
        }
    }

    public static void solve() {
        int i;
        do {
            printResult();

            i = n - 1;
            while (i >= 1 && a[i] < a[i - 1]) {
                i--;
            }

            if (i >= 1) {
                for (int j = n - 1; j >= i; --j) {
                    if (a[j] > a[i - 1]) {
                        swap(j, i - 1);
                        sortAsc(i);
                        break;
                    }
                }
            }

        } while (i >= 1);
    }

    public static void printResult() {
        for (int i = 0; i < n; ++i) {
            System.out.printf("%d", a[i]);
        }
        System.out.println();
    }

    public static void sortAsc(int startPos) {
        for (int i = startPos; i < n - 1; ++i) {
            for (int j = startPos + 1; j < n; ++j) {
                if (a[j] < a[i]) {
                    swap(i, j);
                }
            }
        }
    }

    public static void swap(int firstIndex, int secondIndex) {
        int temp = a[firstIndex];
        a[firstIndex] = a[secondIndex];
        a[secondIndex] = temp;
    }
}