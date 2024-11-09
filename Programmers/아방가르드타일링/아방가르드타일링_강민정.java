class Solution {
    public int solution(int n) {
        long[] dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;

        // n이 6보다 클 때에만 dp를 계산
        if(n > 6) {
            for (int i = 7; i <= n; i++) {
                // dp[i]를 계산하는 부분
                dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 6 + dp[i - 4] - dp[i - 6]) % 1000000007;
                dp[i] = (dp[i] + 1000000007) % 1000000007;
            }
        }

        // 결과값을 반환할 때는 dp[n]을 1000000007로 나눈 나머지를 반환
        return (int) dp[n];
    }
}
