import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N+1];
        stairs[0] = 0;
        int[][] dp = new int[N+1][2];
        dp[0] = new int[]{0, 0};
        for (int index = 1; index < N+1; index++) {
            stairs[index] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[index], 0);
        }
        if (N==1) {
            System.out.println(stairs[1]);
            return;
        }
        dp[1] = new int[]{stairs[1], 0};
        dp[2] = new int[]{stairs[2], stairs[2] + stairs[1]};
        for (int index = 3; index < N+1; index++) {
            dp[index][0] = Math.max(dp[index-2][0], dp[index-2][1]) + stairs[index];
            dp[index][1] = dp[index-1][0] + stairs[index];
        }
        int answer = Math.max(dp[N][0], dp[N][1]);
        System.out.println(answer);
    }
}