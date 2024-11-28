import java.util.*;
import java.io.*;

public class Main {

    static int threeMax(int a, int b, int c) { return Math.max(Math.max(a, b), c); }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n];
        for (int i = 0; i < n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {System.out.println(wine[0]); return;}

        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = wine[0];
        dp[0][2] = wine[0];
        dp[1][0] = wine[0];
        dp[1][1] = wine[1];
        dp[1][2] = wine[0] + wine[1];


        for (int i = 2; i < n; i++) {
            dp[i][0] = threeMax(dp[i-1][0], dp[i-1][1], dp[i-1][2]); // 지금 선택 x
            dp[i][1] = dp[i-1][0] + wine[i]; // 앞 선택 x, 지금 선택
            dp[i][2] = dp[i-1][1] + wine[i]; // 앞앞 선택 x, 앞 선택 o, 지금 선택 o
        }

        System.out.println(threeMax(dp[n-1][0], dp[n-1][1], dp[n-1][2]));

    }
}
