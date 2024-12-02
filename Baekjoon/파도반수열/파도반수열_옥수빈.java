import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        for (int i = 0; i < 3; i++) {
            dp[i] = 1;
        }
        for (int i = 3; i <= 100; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n - 1]);
        }
    }
}