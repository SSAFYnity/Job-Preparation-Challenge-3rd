import java.util.*;
import java.io.*;

public class 소수구하기_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int M = Integer.parseInt(input[0]), N = Integer.parseInt(input[1]);
        for (int i = M; i <= N; i++) {
            if (i == 1) {
                continue;
            } else if (i == 2) {
                System.out.println(2);
                continue;
            }
            double temp = Math.sqrt(i);
            boolean flag = false;
            for (int j = 2; j < temp + 1; j++) {
                if (i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(i);
            }
        }
    }
}

