import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 소수구하기_이호찬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		boolean[] isPrime = new boolean[1_000_001];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		for (int i = 2; i <= Math.sqrt(1_000_000); i++) {
			if (!isPrime[i]) {
				continue;
			}
			for (int j = i * i; j <= 1_000_000; j += i) {
				if (isPrime[j]) {
					isPrime[j] = false;
				}
			}
		}

		for (int i = n; i <= m; i++) {
			if (isPrime[i]) {
				bw.write(i + "\n");
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
