public class DepthFirstSearch {
    private static final int max = 101;
    private static boolean[][] a = new boolean[max][max];
    private static int[] trace = new int[max];
    private static int s = 6;
    private static int n = 8;
    private static int f = 5;

    private static void dfs(int u) {
        for (int v = 1; v <= n; ++v) {
            if (a[u][v] && trace[v] == 0) {
                trace[v] = u;
                dfs(v);
            }
        }
    }

    private static void result() {
        System.out.println("From " + s + " you can visit:");
        for (int v = 1; v <= n; ++v) {
            if (trace[v] != 0) {
                System.out.println(v);
            }
        }

        System.out.println("The path from " + s + " to " + f + ":");
        if (trace[f] == 0) {
            System.out.println("Not found!");
        } else {
            while (f != s) {
                System.out.print(f + " <- ");
                f = trace[f];
            }
            System.out.print(s);
        }
    }

    public static void main(String[] args) {
        a[1][2] = a[2][1] = true;
        a[1][3] = a[3][1] = true;
        a[2][3] = a[3][2] = true;
        a[2][4] = a[4][2] = true;
        a[3][5] = a[5][3] = true;
        a[4][6] = a[6][4] = true;
        a[7][8] = a[8][7] = true;
        trace[s] = -1;
        dfs(s);
        result();
    }
}
