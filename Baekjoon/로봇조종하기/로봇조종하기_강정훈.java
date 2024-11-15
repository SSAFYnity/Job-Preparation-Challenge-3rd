import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] maps = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < M; column++) {
                maps[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];
        dp[0][0] = maps[0][0];
        for (int index = 1; index < M; index++) {
            dp[0][index] = dp[0][index-1] + maps[0][index];
        }

        for (int r = 1; r < N; r++) {
            dp[r][0] = dp[r-1][0] + maps[r][0];
            dp[r][M-1] = dp[r-1][M-1] + maps[r][M-1];
            int[] leftToRightArr = new int[M];
            int[] rightToLeftArr = new int[M];
            leftToRightArr[0] = dp[r][0];
            rightToLeftArr[M-1] = dp[r][M-1];
            for (int c = 1; c < M; c++) {
                leftToRightArr[c] = maps[r][c] + Math.max(dp[r-1][c], leftToRightArr[c-1]);
            }

            for (int c = M-2; c >= 0; c--) {
                rightToLeftArr[c] = maps[r][c] + Math.max(dp[r-1][c], rightToLeftArr[c+1]);
            }

            for (int c = 0; c < M; c++) {
                dp[r][c] = Math.max(leftToRightArr[c], rightToLeftArr[c]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}