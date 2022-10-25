public class AllOfHamiltonCircuits {
    private static final int max = 101;
    private static boolean[][] a = new boolean[max][max];
    private static boolean[] free = new boolean[max];
    private static int[] x = new int[max];
    private static int n = 5;

    private static void tryValue(int i) {
        for (int j = 1; j <= n; ++j) {
            if (free[j] && a[x[i - 1]][j]) {
                x[i] = j;
                if (i < n) {
                    free[j] = false;
                    tryValue(i + 1);
                    free[j] = true;
                } else if (a[j][x[1]]) {
                    printResult();
                }
            }
        }
    }

    private static void printResult() {
        for (int i = 1; i <= n; ++i) {
            System.out.print(x[i] + " ");
        }
        System.out.println(x[1]);
    }

    public static void main(String[] args) {
        a[1][2] = a[2][1] = true;
        a[1][3] = a[3][1] = true;
        a[2][4] = a[4][2] = true;
        a[3][5] = a[5][3] = true;
        a[4][1] = a[1][4] = true;
        a[5][2] = a[2][5] = true;

        for (int i = 0; i <= n; ++i) {
            free[i] = true;
        }

        x[1] = 1;
        free[1] = false;
        tryValue(2);
    }
}
