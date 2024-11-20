import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 도로 수
        int X = Integer.parseInt(st.nextToken());   // 파티가 열리는 마을 번호

        Map<Integer, List<Point>> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(new Point(e, d));
        }

        int[][] distanceArr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distanceArr[i], Integer.MAX_VALUE);
            distanceArr[i][i] = 0;
        }
        for (int i = 1; i <= 1000; i++) {
            PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> o1.y - o2.y);
            pq.add(new Point(i, 0));
            while (!pq.isEmpty()) {
                Point point = pq.poll();
                if (map.get(point.x) == null)
                    continue;
                for (Point next : map.get(point.x)) {
                    int d = point.y + next.y;
                    if (distanceArr[i][next.x] > d) {
                        distanceArr[i][next.x] = d;
                        pq.add(new Point(next.x, d));
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, distanceArr[i][X] + distanceArr[X][i]);
        }
        System.out.println(answer);
    }
}