def solution(n):
    dp = [1,1,3,10,23,62] + [0] * (n-5)
    for i in range(6,n+1):
        dp[i] += (dp[i-1] + dp[i-2]*2 + dp[i-3]*6 + dp[i-4] - dp[i-6]) % 1000000007
    return dp[n] % 1000000007
