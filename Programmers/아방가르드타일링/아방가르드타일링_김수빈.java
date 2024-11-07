import java.util.*;

class Solution {
    public int solution(int n) {
        int MOD = 1000000007; 
        long[] dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;

        if (n >= 4) {
            for (int i = 4; i <= n; i++) {
                dp[i] = (dp[i-1] + (dp[i-2] * 2 % MOD) + (dp[i-3] * 5 % MOD)) % MOD;
            }
        }
        
        
        return (int) dp[n];
    }
}
