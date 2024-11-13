import java.io.*;
import java.util.*;

public class Main {
    static int [] dx = {0, 1, 0};
    static int [] dy = {-1, 0, 1};
    static int [][] arr;
    static long [][] nums;
    static int n;
    static int m;
    static long sum;
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        nums = new long[n][m];
        sum = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        nums[0][0] = arr[0][0];
        dfs(0, 0);

        System.out.println(sum);
    }

    static void dfs(int x, int y) {
        if(x == n-1 && y == m-1){
            sum = Math.max(sum, nums[x][y]);
            return;
        }

        for(int i = 0; i < 3; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || tx >= n || ty < 0 || ty >= n) continue;
            long tmp = nums[tx][ty];
            nums[tx][ty] = nums[x][y] + arr[tx][ty];
            dfs(x, y);
            nums[tx][ty] = tmp;
        }
    }
}
