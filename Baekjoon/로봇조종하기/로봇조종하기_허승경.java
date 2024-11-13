package Nov_2024;

import java.io.*;
import java.util.*;

public class BOJ_2169_1113 {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][] arr = new int[n][m];
        long [][] mars = new long[n+1][m+1];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0: 왼, 1:아래, 2: 오
        mars[1][1] = arr[0][0];

        int [] dx = {0, -1, 0};  // 왼, 아래, 오
        int [] dy = {-1, 0, 1};
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){
                long tmp = 0;
                for(int k = 0; k < 3; k++){
                    long tmp_sum = 0;
                    int tx = i + dx[k];
                    int ty = j + dy[k];

                    if(tx < 1 || tx > n || ty < 1 || ty > m) continue;
                    tmp_sum += mars[tx][ty] + arr[i-1][j-1];
                    tmp = Math.max(tmp_sum, tmp);

                }
                mars[i][j] = Math.max(tmp, mars[i][j]);
            }
        }

        System.out.println(mars[n][m]);
    }
}
