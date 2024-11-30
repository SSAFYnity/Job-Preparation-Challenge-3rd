import java.io.*;

public class 포도주시식_김수빈 {
    // 조건 1. 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
    // 조건 2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());  // 포도주 개수
        int[] wines = new int[N];  // 포도주 양 배열
        for (int i = 0; i < N; i++) {
            wines[i] = Integer.parseInt(br.readLine());
        }

        // 포도주가 2병 이내일 경우
        if (N == 1) {
            System.out.println(wines[0]);
            return;
        } else if (N == 2) {
            System.out.println(wines[0] + wines[1]);
            return;
        }

        int[] dp = new int[N];
        dp[0] = wines[0];
        dp[1] = wines[0] + wines[1];
        dp[2] = Math.max(dp[1], Math.max(wines[0] + wines[2], wines[1] + wines[2]));

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
        }


        System.out.println(dp[N - 1]);
    }
}
