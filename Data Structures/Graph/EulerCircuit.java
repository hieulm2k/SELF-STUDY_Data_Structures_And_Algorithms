public class EulerCircuit {
    private static final int max = 101;
    private static int[][] a = new int[max][max];
    private static int n = 5;

    private static boolean canGoBack(int x, int y) {
        int[] queue = new int[max];
        boolean[] free = new boolean[max];
        int front = 1;
        int rear = 1;

        // Try to remove an edge
        a[x][y]--;
        a[y][x]--;

        // Apply BFS to check if y can go back to x
        for (int i = 0; i <= n; ++i) {
            free[i] = true;
        }

        free[y] = false;
        queue[1] = y;

        do {
            int u = queue[front++];
            for (int v = 1; v <= n; ++v) {
                if (free[v] && a[u][v] > 0) {
                    queue[++rear] = v;
                    free[v] = false;
                    if (free[x]) break;
                }
            }
        } while (front <= rear);

        // Recover a removed edge
        a[x][y]++;
        a[y][x]++;
        return !free[x];
    }

    private static void findEulerCircuit() {
        int current = 1;
        int next;
        System.out.print(current + " ");
        do {
            next = 0;
            for (int v = 1; v <= n; ++v) {
                if (a[current][v] > 0) {
                    next = v;
                    if (canGoBack(current, next)) break;
                }
            }

            if (next != 0) {
                a[current][next]--;
                a[next][current]--;
                System.out.print(next + " ");
                current = next;
            }
        } while (next != 0);
    }

    public static void main(String[] args) {
        a[1][2] = a[2][1] = 1;
        a[1][3] = a[3][1] = 2;
        a[1][4] = a[4][1] = 1;
        a[2][3] = a[3][2] = 1;
        a[3][4] = a[4][3] = 1;

        findEulerCircuit();
    }
}
