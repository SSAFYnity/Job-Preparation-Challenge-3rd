import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        if (N < 2) {
            System.out.println(data[1]);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = data[1];
        dp[2] = data[1] + data[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + data[i], dp[i - 3] + data[i - 1] + data[i]);
        }

        System.out.println(dp[N]);
    }
}
