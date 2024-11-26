import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];

        if (N == 1) {
            System.out.println(data[0]);
            return;
        } else if (N == 2) {
            System.out.println(data[0] + data[1]);
            return;
        }

        dp[0] = data[0];
        dp[1] = data[0] + data[1];
        dp[2] = Math.max(dp[1], data[0] + data[2]);
        dp[2] = Math.max(dp[2], data[1] + data[2]);
        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + data[i]);
            dp[i] = Math.max(dp[i], dp[i - 3] + data[i - 1] + data[i]);
        }

        System.out.println(dp[N - 1]);
    }
}
