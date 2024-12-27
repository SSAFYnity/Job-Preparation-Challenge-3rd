import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        boolean[] data = new boolean[M + 1];
        data[1] = true;
        for (int i = 2; i * i <= M; i++) {
            if (!data[i]) {
                for (int j = i * i; j <= M; j += i) {
                    data[j] = true;
                }
            }
        }

        for (int i = N; i <= M; i++) {
            if (!data[i])
                sb.append(i + "\n");
        }

        System.out.println(sb.toString());
    }
}
