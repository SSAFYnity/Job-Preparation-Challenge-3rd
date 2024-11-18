import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true); // 모든 값 true로 초기화
        isPrime[0] = isPrime[1] = false; // 0과 1은 소수가 아니니깐 false

        for(int i = 2; i <= Math.sqrt(n); i++){ // m부터 n의 제곱근까지의 모든 수 확인
            if(isPrime[i]){ // 소수 -> 해당 수를 제외한 배수들 모두 false
                for(int j = i*i; j<= n; j += i){// 해당 수 이하는 검사 완료 -> i*i부터 시작
                    isPrime[j] = false;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = m; i <= n; i++){
            if(isPrime[i]) sb.append(i+"\n");
        }

        System.out.println(sb);
    }
}
