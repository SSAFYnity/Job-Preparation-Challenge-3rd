import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 가로
        int N = Integer.parseInt(st.nextToken()); // 세로

        int[][] data = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[M][N];
        dp[0][0] = data[0][0];
        for (int i = 1; i < N; i++) {
            dp[0][i] = dp[0][i - 1] + data[0][i];
        }

        for (int i = 1; i < M; i++) {
            // 왼쪽에서 오른쪽
            int[] tmp1 = new int[N];
            tmp1[0] = dp[i - 1][0] + data[i][0];
            for (int j = 1; j < N; j++) {
                tmp1[j] = Math.max(tmp1[j - 1], dp[i - 1][j]) + data[i][j];
            }
            // 오른쪽에서 왼쪽
            int[] tmp2 = new int[N];
            tmp2[N - 1] = dp[i - 1][N - 1] + data[i][N - 1];
            for (int j = N - 2; j >= 0; j--) {
                tmp2[j] = Math.max(tmp2[j + 1], dp[i - 1][j]) + data[i][j];
            }

            for (int j = 0; j < N; j++) {
                dp[i][j] = Math.max(tmp1[j], tmp2[j]);
            }
        }

        System.out.println(dp[M - 1][N - 1]);
    }
}
