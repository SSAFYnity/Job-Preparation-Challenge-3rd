import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 포도주 잔의 개수 <= 10,000
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        // 포도주의 양을 입력받기
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        dp[1] = arr[1];
        if(n > 1) {
            dp[2] = arr[1] + arr[2];
        }

        // 최대로 마실 수 있는 포도주의 양을 출력
        for(int i=3; i<=n; i++) {
            // (현재 포도주를 마시지 않기), (현재 포도주와 이전 포도주를 마시기), (현재 포도주와 이전의 그 이전 포도주 마시기) 중에서 최대값
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], arr[i] + dp[i - 3] + arr[i - 1]));
        }

        System.out.println(dp[n]);
    }
}