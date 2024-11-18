import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        boolean[] check = new boolean[N+1];
        check[1] = true;
        for (int index = 2; index <= Math.sqrt(N); index++) {
            int target = index*2;
            while(target < N+1) {
                check[target] = true;
                target += index;
            }
        }

        for (int index = M; index < N+1; index++) {
            if(!check[index]) {
                System.out.println(index);
            }
        }
    }
}