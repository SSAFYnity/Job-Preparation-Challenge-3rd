import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int stairsCnt = Integer.parseInt(br.readLine());
        int[] stairArr = new int[stairsCnt + 1];
        for (int i = 1; i <= stairsCnt; i++) {
            stairArr[i] = Integer.parseInt(br.readLine());
        }
        if (stairsCnt == 1)
            System.out.println(stairArr[1]);
        else if (stairsCnt == 2)
            System.out.println(stairArr[1] + stairArr[2]);
        else {
            int[] dp = new int[stairsCnt + 1];
            dp[1] = stairArr[1];
            dp[2] = stairArr[1] + stairArr[2];
            for (int i = 3; i <= stairsCnt; i++) {
                dp[i] = Math.max(dp[i - 2] + stairArr[i], dp[i - 3] + stairArr[i - 1] + stairArr[i]);
            }
            System.out.println(dp[stairsCnt]);
        }
    }
}