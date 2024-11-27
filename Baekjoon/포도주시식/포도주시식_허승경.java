import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] arr = new int[n+1];
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int [] dp = new int[n+1]; // i번째 최대 마실 수 있는 양
        dp[0] = 0;
        dp[1] = arr[1]; // 첫 번째 잔 마심
        if(n >= 2) dp[2] = arr[1]+arr[2];       // 두 번째까지는 연속 선택이 최댓값

        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-1], Math.max(arr[i]+dp[i-2], arr[i]+arr[i-1]+dp[i-3]));
        }

        System.out.println(dp[n]);
    }
}