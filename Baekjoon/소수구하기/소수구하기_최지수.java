import java.util.*;
import java.io.*;

public class Main {

    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[] sosu = new boolean[m+1];
        Arrays.fill(sosu, true);
        sosu[0] = sosu[1] = false;


        for (int i = 2; i < Math.sqrt(m+1) + 1; i++) {
            if (!sosu[i]) {continue;}
            for (int j = i*i; j < m+1; j += i) {
                sosu[j] = false;
            }
        }

        for (int i = n; i < m+1; i++) {
            if (sosu[i]) System.out.println(i);
        }
    }
}

