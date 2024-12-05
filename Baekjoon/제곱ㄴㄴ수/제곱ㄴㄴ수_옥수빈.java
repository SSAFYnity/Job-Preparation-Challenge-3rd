import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        boolean[] arr = new boolean[(int) (max - min + 1)];
        int answer = (int) (max - min + 1);

        for (long i = 2; i <= Math.sqrt(max); i++) {
            for (long j = (min % (i * i) == 0 ? min / (i * i) : min / (i * i) + 1); j <= max / (i * i); j++) {
                int index = (int) (i * i * j - min);
                if (index >= arr.length)
                    continue;
                if (!arr[index]) {
                    arr[index] = true;
                    answer--;
                }
            }
        }
        System.out.println(answer);
    }
}