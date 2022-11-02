public class ShortestPathByFloyd {
    private static final int max = 101;
    private static final int maxC = 10001;
    private static int[][] c = new int[max][max];
    private static int[][] trace = new int[max][max];
    private static int n = 6;
    private static int s = 1;
    private static int f = 4;

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

        c[1][2] = 1;
        c[1][6] = 20;
        c[2][3] = 2;
        c[3][6] = 3;
        c[3][4] = 20;
        c[5][4] = 5;
        c[6][5] = 4;
    }

    private static void floyd() {
        for (int u = 1; u <= n; ++u) {
            for (int v = 1; v <= n; ++v) {
                trace[u][v] = v;
            }
        }

        for (int k = 1; k <= n; ++k) {
            for (int u = 1; u <= n; ++u) {
                for (int v = 1; v <= n; ++v) {
                    if (c[u][v] > c[u][k] + c[k][v]) {
                        c[u][v] = c[u][k] + c[k][v];
                        trace[u][v] = trace[u][k];
                    }
                }
            }
        }
    }

    private static void printResult() {
        if (c[s][f] == maxC) {
            System.out.println("There is no path from " + s + " to " + f);
        } else {
            System.out.println("Distance from " + s + " to " + f + ": " + c[s][f]);
            while (f != s) {
                System.out.print(s + "<-");
                s = trace[s][f];
            }
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        loadGraph();
        floyd();
        printResult();
    }
}
