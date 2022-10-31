public class CriticalPath {
    private static final int max = 101;
    private static final int maxC = 10001;
    private static int[][] c = new int[max][max];
    private static int[] d = new int[max];
    private static int[] trace = new int[max];
    private static int[] list = new int[max];
    private static boolean[] free = new boolean[max];
    private static int n = 6;
    private static int s = 1;
    private static int f = 4;
    private static int count = n;

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

    private static void numbering() {
        for (int i = 0; i <= n; ++i) {
            free[i] = true;
        }
        
        for (int u = 1; u <= n; ++u) {
            if (free[u]) {
                visit(u);
            }
        }
    }

    private static void visit(int u) {
        free[u] = false;
        for (int v = 1; v <= n; ++v) {
            if (free[v] && c[u][v] != maxC) {
                visit(v);
            }
        }
        list[count] = u;
        count--;
    }

    private static void init() {
        for (int i = 0; i <= n; ++i) {
            d[i] = maxC;
        }
        d[s] = 0;
    }

    private static void findPath() {
        for (int i = 1; i <= n - 1; ++i) {
            for (int j = i + 1; j <= n; ++j) {
                int u = list[i];
                int v = list[j];
                if (d[v] > d[u] + c[u][v]) {
                    d[v] = d[u] + c[u][v];
                    trace[v] = u;
                }
            }
        }
    }

    private static void printResult() {
        if (d[f] == maxC) {
            System.out.println("There is no path from " + s + " to " + f);
        } else {
            System.out.println("Distance from " + s + " to " + f + ": " + d[f]);
            while (f != s) {
                System.out.print(f + "<-");
                f = trace[f];
            }
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        loadGraph();
        numbering();
        init();
        findPath();
        printResult();
    }
}
