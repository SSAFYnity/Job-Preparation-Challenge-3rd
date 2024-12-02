import java.util.*;
import java.io.*;
public class 소수구하기_안창호{
    static boolean[] notPrime;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        notPrime = new boolean[N + 1];
        notPrime[0] = true;
        notPrime[1] = true;

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (!notPrime[i]) { // i가 소수라면
                for (int j = i * i; j <= N; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        for (int i = M; i <= N; i++) {
            if (!notPrime[i]) {
                System.out.println(i);
            }
        }
    }
}
