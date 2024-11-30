import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 개수
        map = new int[N][2]; // 회의실 값
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(map, (o1, o2) -> {
            return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
        });

        int result = 1;
        int pre = map[0][1];
        for (int i = 1; i < N; i++) {
            if (map[i][0] < pre) continue;
            pre = map[i][1];
            result++;
        }

        System.out.println(result);

    }
}
