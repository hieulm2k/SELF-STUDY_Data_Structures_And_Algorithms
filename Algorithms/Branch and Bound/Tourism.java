import java.util.Arrays;
import java.util.Scanner;

public class Tourism {
    private static int MAX = 20;
    private static int MAXC = 20 * 100 + 1; // + inf
    private static int m;
    private static int n;
    private static int minSpending;

    private static int[][] costs;
    private static int[] x;
    private static int[] bestWay;
    private static int[] t;
    private static boolean[] free;

    public static void main(String[] args) {
        enter();
        init();
        tryValue(1);
        printResult();
    }

    public static void enter() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        costs = new int[MAX][MAX];
        x = new int[MAX];
        t = new int[MAX];
        bestWay = new int[MAX];
        free = new boolean[MAX];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    costs[i][j] = 0;
                } else {
                    costs[i][j] = MAXC;
                }
            }
        }

        for (int k = 0; k < m; ++k) {
            int i = scanner.nextInt() - 1;
            int j = scanner.nextInt() - 1;
            costs[i][j] = scanner.nextInt();
            costs[j][i] = costs[i][j];
        }

        scanner.close();
    }

    public static void init() {
        for (int i = 0; i < n; ++i) {
            free[i] = true;
        }

        free[0] = false;
        x[0] = 0;
        t[0] = 0;
        minSpending = MAXC;
    }

    public static void tryValue(int i) {
        for (int j = 1; j < n; ++j) {
            if (free[j]) {
                x[i] = j;
                t[i] = t[i - 1] + costs[x[i - 1]][j];

                if (t[i] < minSpending) {
                    if (i < n - 1) {
                        free[j] = false;
                        tryValue(i + 1);
                        free[j] = true;
                    } else if (t[n - 1] + costs[x[n - 1]][0] < minSpending) {
                        minSpending = t[n - 1] + costs[x[n - 1]][0];
                        bestWay = Arrays.copyOf(x, MAX);
                    }
                }
            }
        }
    }

    public static void printResult() {
        if (minSpending == MAXC) {
            System.out.println("NO SOLUTION");
            return;
        }

        for (int i = 0; i < n; ++i) {
            System.out.print(bestWay[i] + 1 + " -> ");
        }
        System.out.println(1);
        System.out.println();
        System.out.println("Cost: " + minSpending);
    }
}