import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[] isNotPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        isNotPrime = new boolean[N + 1];
        isNotPrime[0] = isNotPrime[1] = true;

        isPrimeNumber(N);
        StringBuilder sb = new StringBuilder();
        for (int i = M; i <= N; i++) {
            if (!isNotPrime[i]) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void isPrimeNumber(int maxNum) {
        for (int i = 2; i * i <= maxNum; i++) {
            for (int j = 2; j * i <= maxNum; j++) {
                isNotPrime[i * j] = true;
            }
        }
    }
}