import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cleanedCell = 0;
    static int N, M, x, y, d;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        for (int c = 0; c < N; c++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 0; r < M; r++) {
                map[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            clean(); // 현재 위치 청소
            boolean hasDirty = false;

            // 주변 4칸 확인
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;
                if (map[ny][nx] == 0) { // 청소되지 않은 빈 칸
                    hasDirty = true;
                    break;
                }
            }

            if (!hasDirty) { // 주변에 청소할 공간이 없는 경우
                if (!goBack()) // 후진 불가능하면 종료
                    break;
            } else { // 청소할 공간이 있는 경우
                rotateAndMove();
            }
        }

        System.out.println(cleanedCell);
    }

    // 현재 위치를 청소
    public static void clean() {
        if (map[y][x] == 0) { // 청소되지 않은 경우에만
            map[y][x] = 2; // 청소 완료 표시
            cleanedCell++;
        }
    }

    // 후진
    public static boolean goBack() {
        int nd = (d + 2) % 4; // 후진 방향
        int nx = x + dx[nd];
        int ny = y + dy[nd];
        if (nx < 0 || ny < 0 || nx >= M || ny >= N || map[ny][nx] == 1) // 벽이면 후진 불가
            return false;
        x = nx;
        y = ny;
        return true;
    }

    // 왼쪽으로 회전하며 이동
    public static void rotateAndMove() {
        for (int i = 1; i <= 4; i++) {
            int nd = (d - i + 4) % 4; // 왼쪽 방향
            int nx = x + dx[nd];
            int ny = y + dy[nd];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N) // 범위를 벗어나면 스킵
                continue;
            if (map[ny][nx] == 0) { // 청소되지 않은 빈 칸인 경우
                x = nx;
                y = ny;
                d = nd; // 방향 갱신
                return;
            }
        }
    }
}