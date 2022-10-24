public class DirectivityBridgesCutVertices {
    private static final int max = 101;
    private static boolean[][] a = new boolean[max][max];
    private static int[] number = new int[max];
    private static int[] low = new int[max];
    private static int[] parent = new int[max];
    private static int n = 12;
    private static int count = 0;

    private static void visit(int u) {
        number[u] = ++count;
        low[u] = n + 1;

        for (int v = 1; v <= n; ++v) {
            if (a[u][v]) {
                a[v][u] = false;
                if (parent[v] == 0) {
                    parent[v] = u;
                    visit(v);
                    low[u] = Math.min(low[v], low[u]);
                } else {
                    low[u] = Math.min(low[u], number[v]);
                }
            }
        }
    }

    private static void solve() {
        for (int u = 1; u <= n; ++u) {
            if (parent[u] == 0) {
                parent[u] = -1;
                visit(u);
            }
        }
    }

    private static void result() {
        int[] nChildren = new int[max];
        boolean[] isCut = new boolean[max];

        for (int v = 1; v <= n; ++v) {
            if (parent[v] != -1) {
                nChildren[parent[v]]++;
            }
        }

        System.out.println("Bridges: ");
        for (int v = 1; v <= n; ++v) {
            int u = parent[v];
            if (u != -1 && low[v] >= number[v]) {
                System.out.println("(" + u + ", " + v + ")");
            }
        }

        System.out.println("\nCut vertices:");
        for (int v = 1; v <= n; ++v) {
            int u = parent[v];
            if (u != -1 && low[v] >= number[u]) {
                isCut[u] = isCut[u] || parent[u] != -1 || nChildren[u] >= 2;
            }
        }

        for (int u = 1; u <= n; ++u) {
            if (isCut[u]) {
                System.out.println(u);
            }
        }
    }

    public static void main(String[] args) {
        a[1][2] = a[2][1] = true;
        a[1][3] = a[3][1] = true;
        a[2][3] = a[3][2] = true;
        a[2][4] = a[4][2] = true;
        a[2][5] = a[5][2] = true;
        a[2][7] = a[7][2] = true;
        a[3][6] = a[6][3] = true;
        a[4][5] = a[5][4] = true;
        a[4][7] = a[7][4] = true;
        a[5][10] = a[10][5] = true;
        a[6][8] = a[8][6] = true;
        a[6][9] = a[9][6] = true;
        a[8][9] = a[9][8] = true;
        a[11][12] = a[12][11] = true;

        solve();
        result();
    }
}
