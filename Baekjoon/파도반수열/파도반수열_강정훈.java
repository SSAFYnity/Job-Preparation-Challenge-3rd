import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1 1 1 2 2 3 4 5 7 9 12 16 21
        long[] P = new long[101];
        P[1] = 1L;
        P[2] = 1L;
        P[3] = 1L;
        P[4] = 2L;
        P[5] = 2L;
        for (int index = 6; index < 101; index++) {
            P[index] = P[index-1] + P[index-5];
        }
        int T = Integer.parseInt(br.readLine());
        for (int testcase = 0; testcase < T; testcase++) {
            int index = Integer.parseInt(br.readLine());
            System.out.println(P[index]);
        }
    }
}