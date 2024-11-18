import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int M = input(st);
        int N = input(st);

        isPrime = new boolean[N+1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i <= N; i++) {
            if(isPrime[i]){
                primeCheck(i);
            }
        }

        for (int i = M; i <= N; i++) {
            if(isPrime[i]){
                System.out.println(i);
            }
        }
    }

    static void primeCheck(int n){
        for (int i = 2*n; i < isPrime.length; i+=n) {
            isPrime[i] = false;
        }
    }

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }
    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
