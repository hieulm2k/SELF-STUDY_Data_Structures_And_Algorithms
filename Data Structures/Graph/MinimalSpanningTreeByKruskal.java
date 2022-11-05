public class MinimalSpanningTreeByKruskal {
    private static final int maxV = 101;
    private static final int maxE = (maxV - 1) * maxV / 2;
    private static TEdge[] e = new TEdge[maxE];
    private static int[] lab = new int[maxV];
    private static final int n = 6;
    private static final int m = 9;
    private static boolean connected;

    private static class TEdge {
        private final int u;
        private final int v;
        private final int c;
        private boolean mark;

        public TEdge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    private static void loadGraph() {
        e[1] = new TEdge(1, 2, 1);
        e[2] = new TEdge(1, 3, 1);
        e[3] = new TEdge(2, 4, 1);
        e[4] = new TEdge(2, 3, 2);
        e[5] = new TEdge(2, 5, 1);
        e[6] = new TEdge(3, 5, 1);
        e[7] = new TEdge(3, 6, 1);
        e[8] = new TEdge(4, 5, 2);
        e[9] = new TEdge(5, 6, 2);
    }

    private static void init() {
        for (int i = 1; i <= n; ++i) {
            lab[i] = -1;
        }

        for (int i = 1; i <= m; ++i) {
            e[i].mark = false;
        }
    }

    private static int getRoot(int v) {
        while (lab[v] > 0) {
            v = lab[v];
        }
        return v;
    }

    private static void union(int r1, int r2) {
        int x = lab[r1] + lab[r2];
        if (lab[r1] > lab[r2]) {
            lab[r1] = r2;
            lab[r2] = x;
        } else {
            lab[r1] = x;
            lab[r2] = r1;
        }
    }

    private static void adjustHeap(int root, int last) {
        TEdge key = e[root];
        while (root * 2 <= last) {
            int child = root * 2;
            if (child < last && e[child + 1].c < e[child].c) {
                child++;
            }
            if (key.c <= e[child].c) break;
            e[root] = e[child];
            root = child;
        }
        e[root] = key;
    }

    private static void kruskal() {
        int count = 0;
        connected = false;
        for (int i = m / 2; i >= 1; --i) {
            adjustHeap(i, m);
        }
        for (int i = m - 1; i >= 0; --i) {
            TEdge tmp = e[1];
            e[1] = e[i + 1];
            e[i + 1] = tmp;
            adjustHeap(1, i);
            int r1 = getRoot(e[i + 1].u);
            int r2 = getRoot(e[i + 1].v);
            if (r1 != r2) {
                e[i + 1].mark = true;
                count++;
                if (count == n - 1) {
                    connected = true;
                    break;
                }
                union(r1, r2);
            }
        }
    }

    private static void printResult() {
        if (!connected) {
            System.out.println("Error: Graph is not connected");
        } else {
            System.out.println("Minimal spanning tree: ");
            int count = 0;
            int w = 0;
            for (int i = 1; i <= m; ++i) {
                if (e[i].mark) {
                    System.out.println("(" + e[i].u + ", " + e[i].v + ") = " + e[i].c);
                    count++;
                    w = w + e[i].c;
                }
                if (count == n - 1) break;
            }
            System.out.println("Weight = " + w);
        }
    }

    public static void main(String[] args) {
        loadGraph();
        init();
        kruskal();
        printResult();
    }
}
