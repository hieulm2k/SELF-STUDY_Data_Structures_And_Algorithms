public class StrOpt {
    private static final int max = 101;

    private static void strOpt(String x, String y) {
        int[][] f = new int[max][max];
        int m = x.length();
        int n = y.length();

        for (int i = 0; i <= n; ++i) {
            f[0][i] = i;
        }

        for (int i = 1; i <= m; ++i) {
            f[i][0] = i;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = min3(f[i - 1][j], f[i - 1][j - 1], f[i][j - 1]) + 1;
                }
            }
        }

        traceResult(x, y, m, n, f);
    }

    private static void traceResult(String x, String y, int m, int n, int[][] f) {
        System.out.println(f[m][n]);

        while (m != 0 || n != 0) {
            if (m >= 1 && n >= 1 && x.charAt(m - 1) == y.charAt(n - 1)) {
                m--;
                n--;
            } else {
                System.out.print(x + " -> ");
                if (f[m][n] == f[m][n - 1] + 1) {
                    System.out.printf("Insert(%d, %s)", m, y.charAt(n - 1));
                    x = x.substring(0, m) + y.charAt(n - 1) + x.substring(m);
                    n--;
                } else if (f[m][n] == f[m - 1][n] + 1) {
                    System.out.printf("Delete(%d)", m);
                    x = x.substring(0, m - 1) + x.substring(m);
                    m--;
                } else if (f[m][n] == f[m - 1][n - 1] + 1) {
                    System.out.printf("Replace(%d, %s)", m, y.charAt(n - 1));
                    x = x.substring(0, m - 1) + y.charAt(n - 1) + x.substring(m);
                    n--;
                    m--;
                }
                System.out.println(" -> " + x);
            }
        }

    }

    private static int min3(int x, int y, int z) {
        int t = Math.min(x, y);
        return Math.min(t, z);
    }

    public static void main(String[] args) {
        String x = "PBBCEFATZ";
        String y = "QABCDABEFA";
        strOpt(x, y);
    }
}