public class TheBag {
    private static final int max = 101;

    private static void theBag(int[] w, int[] v, int n, int m) {
        int[][] f = new int[max][max];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j];
                if (w[i - 1] <= j) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }

        traceResult(f, w, n, m);
    }

    private static void traceResult(int[][] f, int[] w, int n, int m) {
        System.out.println("Max value: " + f[n][m]);
        while (n != 0) {
            if (f[n][m] != f[n - 1][m]) {
                System.out.print(n + " ");
                m -= w[n - 1];
            }
            n--;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 11;
        int[] w = new int[]{3, 4, 5, 9, 4};
        int[] v = new int[]{3, 4, 4, 10, 4};
        theBag(w, v, n, m);
    }
}
