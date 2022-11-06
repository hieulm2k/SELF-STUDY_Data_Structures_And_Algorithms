public class MinimalSpanningTreeByPrim {
    private static final int max = 101;
    private static final int maxC = 10001;
    private static int[][] c = new int[max][max];
    private static int[] d = new int[max];
    private static int[] trace = new int[max];
    private static boolean[] free = new boolean[max];
    private static int n = 6;
    private static int m = 9;
    private static boolean connected;

    private static void loadGraph() {
        for (int u = 1; u <= n; ++u) {
            for (int v = 1; v <= n; ++v) {
                if (u == v) {
                    c[u][v] = 0;
                } else {
                    c[u][v] = maxC;
                }
            }
        }

        c[1][2] = c[2][1] = 1;
        c[1][3] = c[3][1] = 1;
        c[2][4] = c[4][2] = 1;
        c[2][3] = c[3][2] = 2;
        c[2][5] = c[5][2] = 1;
        c[3][5] = c[5][3] = 1;
        c[3][6] = c[6][3] = 1;
        c[4][5] = c[5][4] = 2;
        c[5][6] = c[6][5] = 2;
    }

    private static void init() {
        for (int i = 0; i <= n; ++i) {
            d[i] = maxC;
        }
        d[1] = 0;

        for (int i = 0; i <= n; ++i) {
            free[i] = true;
        }
    }

    private static void prim() {
        connected = true;
        for (int k = 1; k <= n; ++k) {
            int u = 0;
            int min = maxC;
            for (int i = 1; i <= n; ++i) {
                if (free[i] && d[i] < min) {
                    min = d[i];
                    u = i;
                }
            }

            if (u == 0) {
                connected = false;
                break;
            }
            free[u] = false;
            for (int v = 1; v <= n; ++v) {
                if (free[v] && d[v] > c[u][v]) {
                    d[v] = c[u][v];
                    trace[v] = u;
                }
            }
        }
    }

    private static void printResult() {
        if (!connected) {
            System.out.println("Error: Graph is not connected");
        } else {
            System.out.println("Minimal spanning tree: ");
            int w = 0;
            for (int v = 2; v <= n; ++v) {
                System.out.println("(" + trace[v] + ", " + v + ") = " + c[trace[v]][v]);
                w += c[trace[v]][v];
            }
            System.out.println("Weight = " + w);
        }
    }

    public static void main(String[] args) {
        loadGraph();
        init();
        prim();
        printResult();
    }
}
