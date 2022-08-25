import java.util.Scanner;

public class KnightTour {
    private static int n;
    private static int[][] ans;
    private static int x[] = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    private static int y[] = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    public static boolean tryValue(int startX, int startY, int step) {
        if (step == n * n) {
            return true;
        }

        for (int j = 0; j < 8; ++j) {
            int nextX = startX + x[j];
            int nextY = startY + y[j];

            if (nextX >= 0 && nextY >= 0 && nextX < n && nextY < n && ans[nextX][nextY] == -1) {
                ans[nextX][nextY] = step;
                if (tryValue(nextX, nextY, step + 1)) {
                    return true;
                } else {
                    ans[nextX][nextY] = -1;
                }
            }
        }
        return false;
    }

    public static void printResult() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (ans[i][j] / 10 == 0) {
                    System.out.print(ans[i][j] + "    ");
                } else {
                    System.out.print(ans[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.close();

        ans = new int[n][n];
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                ans[x][y] = -1;
        ans[0][0] = 0;

        if (tryValue(0, 0, 1)) {
            printResult();
        } else {
            System.out.println("Solution does not exist");
        }
    }
}
