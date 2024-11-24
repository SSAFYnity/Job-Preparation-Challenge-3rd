import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 포도주시식_이호찬 {

	// 연속으로 3잔을 마실 수 없다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine().trim());
		int[] drink = new int[n];
		int[][] dp = new int[n][3]; // [0]: 연속 1잔, [1]: 연속 2잔, [2]: 누적 최댓값

		for (int i = 0; i < n; i++) {
			drink[i] = Integer.parseInt(br.readLine().trim());
		}

		dp[0][0] = dp[0][2] = drink[0];

		if (n >= 2) {
			dp[1][0] = drink[1];
			dp[1][1] = dp[1][2] = drink[0] + drink[1];

			for (int i = 2; i < n; i++) {
				dp[i][0] = dp[i - 2][2] + drink[i];
				dp[i][1] = dp[i - 1][0] + drink[i];
				dp[i][2] = Math.max(dp[i - 1][2], Math.max(dp[i][0], dp[i][1]));
			}
		}

		bw.write(dp[n - 1][2] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
