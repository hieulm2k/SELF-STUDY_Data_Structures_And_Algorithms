public class Connectivity {
    private static final int max = 101;
    private static boolean[][] a = new boolean[max][max];
    private static boolean[] free = new boolean[max];
    private static int n = 12;
    private static int count = 0;

    private static void warshall() {
        for (int k = 1; k <= n; ++k) {
            for (int u = 1; u <= n; ++u) {
                for (int v = 1; v <= n; ++v) {
                    a[u][v] = a[u][v] || a[u][k] && a[k][v];
                }
            }
        }
    }

    private static void countConnectivity() {
        for (int i = 1; i <= n; ++i) {
            free[i] = true;
        }

        for (int u = 1; u <= n; ++u) {
            if (free[u]) {
                System.out.println("Connected Component " + count + ":");
                for (int v = 1; v <= n; ++v) {
                    if (a[u][v]) {
                        System.out.print(v + ", ");
                        free[v] = false;
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        a[1][1] = a[2][2] = a[3][3] = a[4][4] = a[5][5] = a[6][6] = a[7][7]
                = a[8][8] = a[9][9] = a[10][10] = a[11][11] = a[12][12] = true;
        a[1][3] = a[3][1] = true;
        a[1][4] = a[4][1] = true;
        a[1][5] = a[5][1] = true;
        a[2][4] = a[4][2] = true;
        a[6][7] = a[7][6] = true;
        a[6][8] = a[8][6] = true;
        a[9][10] = a[10][9] = true;
        a[9][11] = a[11][9] = true;
        a[11][12] = a[12][11] = true;

        warshall();
        countConnectivity();
    }
}
