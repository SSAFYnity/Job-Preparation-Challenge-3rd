import java.util.Scanner;

class Main {
    static long[] dp = new long[101];

    public static void cal() {
        for(int i=11; i<=100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // DP 초기화
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;
        dp[7] = 4;
        dp[8] = 5;
        dp[9] = 7;
        dp[10] = 9;

        cal();

        int tc = sc.nextInt();
        while(tc-- > 0) {
            System.out.println(dp[sc.nextInt()]);
        }
    }
}