public class SubSequence {
    private static void subSequence(int[] a, int k) {
        int[][] f = new int[a.length + 1][k];

        for (int t = 0; t < k; ++t) {
            f[0][t] = 255;
        }
        f[0][0] = 0;

        for (int i = 1; i <= a.length; ++i) {
            for (int t = 0; t < k; ++t) {
                f[i][t] = Math.min(f[i - 1][t], f[i - 1][sub(t, a[i - 1], k)] + 1);
            }
        }

        result(a, f, k);
    }

    private static int sub(int x, int y, int k) {
        int temp = (x - y) % k;
        if (temp >= 0) return temp;
        return temp + k;
    }

    private static void result(int[] a, int[][] f, int k) {
        int sumAll = 0;
        for (int i = 0; i < a.length; ++i) {
            sumAll += a[i];
        }

        System.out.println(a.length - f[a.length][sumAll % k]);

        int sum = 0;
        int t = sumAll % k;
        for (int i = a.length; i >= 1; --i) {
            if (f[i][t] == f[i - 1][t]) {
                System.out.printf("a[%d] = %d\n", i, a[i - 1]);
                sum += a[i - 1];
            } else {
                t = sub(t, a[i - 1], k);
            }
        }
        System.out.println("Sum = " + sum);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 6, 11, 5, 10, 15, 20, 2, 4, 9};
        int k = 5;
        subSequence(a, k);
    }
}
