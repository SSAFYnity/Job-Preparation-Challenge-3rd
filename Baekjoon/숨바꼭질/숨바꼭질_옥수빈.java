import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                if (x == K) {
                    System.out.println(cnt);
                    return;
                }
                if (x - 1 >= 0) {
                    if (!visited[x - 1]) {
                        queue.add(x - 1);
                        visited[x - 1] = true;
                    }
                }
                if (x < K) {
                    if (x + 1 <= 100000) {
                        if (!visited[x + 1]) {
                            queue.add(x + 1);
                            visited[x + 1] = true;
                        }
                    }
                    if (2 * x <= 100000) {
                        if (!visited[2 * x]) {
                            queue.add(2 * x);
                            visited[2 * x] = true;
                        }
                    }
                }
            }
            cnt++;
        }
    }
}