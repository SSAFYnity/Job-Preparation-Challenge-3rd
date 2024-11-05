import java.util.*;
import java.io.*;

public class Main {

    static int n,m,l;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 트럭 갯수
        m = Integer.parseInt(st.nextToken()); // 다리 길이, 다리 위 트럭 갯수 제한
        l = Integer.parseInt(st.nextToken()); // 최대 하중

        int[] arr = new int[n];
        int[] last = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int p = 0;
        int start = 0;
        int ing = 0;
        int wsum = 0;

        int time = 0;
        while (time++ <= 1000000) {
            for (int i = start; i < Math.min(p, n); i++) {
                if (last[i]++ >= m) {
                    start++;
                    ing--;
                    wsum -= arr[i];
                }
            }
            if (p < n && ing < m && wsum + arr[p] <= l) {
                ing++;
                last[p]++;
                wsum += arr[p];
                p++;

            }
            if (start >= n) {
                break;
            }
        }

        System.out.println(time);
    }
}

