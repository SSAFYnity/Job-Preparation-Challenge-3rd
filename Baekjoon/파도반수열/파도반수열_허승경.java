import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long [] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for(int i = 4; i < 101; i++){
            dp[i] = dp[i-2] + dp[i-3];
        }
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]+"\n");
        }

        System.out.println(sb.toString());
    }
}
