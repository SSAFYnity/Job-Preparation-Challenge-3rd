import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 계단의 개수
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for(int i=1; i<=n; i++) {
            arr[i] = sc.nextInt();
        }

        dp[1] = arr[1];     // 첫 번째 계단 밟기
        if(n >= 2) {
            dp[2] = arr[1] + arr[2];        // 연속으로 두 계단(첫 번째, 두 번째) 밟기
        }

        if(n >= 3) {
            for (int i = 3; i <= n; i++) {
                // 이전 계단을 밟고 현재 계단 밟기 VS 다음 다음 계단 밟기
                dp[i] = Math.max(arr[i] + dp[i - 3] + arr[i - 1], arr[i] + dp[i - 2]);
            }
        }
        System.out.println(dp[n]);
    }
}