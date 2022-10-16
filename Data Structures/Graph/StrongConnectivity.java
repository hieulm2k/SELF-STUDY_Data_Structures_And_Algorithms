public class StrongConnectivity {
    private static final int max = 101;
    private static boolean[][] a = new boolean[max][max];
    private static boolean[] free = new boolean[max];
    private static int[] number = new int[max];
    private static int[] low = new int[max];
    private static int[] stack = new int[max];
    private static int n = 11;
    private static int count = 0;
    private static int componentCount = 0;
    private static int top = 0;

    public static void init() {
        for (int i = 0; i <= n; ++i) {
            free[i] = true;
        }
    }

    public static void push(int v) {
        stack[++top] = v;
    }

    public static int pop() {
        return stack[top--];
    }

    public static void visit(int u) {
        number[u] = ++count;
        low[u] = number[u];
        push(u);
        for (int v = 1; v <= n; ++v) {
            if (free[v] && a[u][v]) {
                if (number[v] != 0) {
                    low[u] = Math.min(low[u], number[v]);
                } else {
                    visit(v);
                    low[u] = Math.min(low[u], low[v]);
                }
            }
        }

        if (number[u] == low[u]) {
            System.out.println("Component " + ++componentCount + ":");
            int v;
            do {
                v = pop();
                System.out.print(v + ", ");
                free[v] = false;
            } while (v != u);
            System.out.println();
        }
    }

    public static void solve() {
        for (int u = 1; u <= n; ++u) {
            if (number[u] == 0) {
                visit(u);
            }
        }
    }

    public static void main(String[] args) {
        a[1][2] = true;
        a[1][8] = true;
        a[2][3] = true;
        a[3][4] = true;
        a[4][2] = true;
        a[4][5] = true;
        a[5][6] = true;
        a[6][7] = true;
        a[7][5] = true;
        a[8][9] = true;
        a[9][4] = true;
        a[9][10] = true;
        a[10][8] = true;
        a[10][11] = true;
        a[11][8] = true;

        init();
        solve();
    }
}
