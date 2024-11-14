import java.util.*;
import java.io.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] mars = new int[n][m];
        int[][][] dp = new int[n][m][2]; 
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mars[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -100_000_000);
            }
        }

        dp[0][0][0] = mars[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i][0] = dp[0][i-1][0] + mars[0][i];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0][0] = dp[i-1][0][0] + mars[i][0];
            for (int j = 1; j < m; j++) {
                dp[i][j][0] = Math.max(dp[i][j-1][0], dp[i-1][j][0]) + mars[i][j];
            }
            dp[i][m-1][1] = dp[i-1][m-1][0] + mars[i][m-1];
            dp[i][m-1][0] = Math.max(dp[i][m-1][0], dp[i][m-1][1]);
            for (int j = m-2; j >= 0; j--) {
                dp[i][j][1] = Math.max(dp[i][j+1][1], dp[i-1][j][0]) + mars[i][j];
                dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j][1]);
            }
        }

        System.out.println(dp[n-1][m-1][0]);

    }
}

