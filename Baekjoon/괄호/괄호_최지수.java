import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        for (int t = 0; t < n; t++) {
            String str = br.readLine();
            int cnt = 0;
            boolean ok = true;
            for (int i = 0; i < str.length() && ok; i++) {
                char c = str.charAt(i);
                if (c == '(') {
                    cnt++;
                } else if (cnt >= 1) {
                    cnt--;
                } else {
                    ok = false;
                }
            }
            if (cnt != 0) {
                ok = false;
            }
            sb.append(ok ? "YES" : "NO");
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}

