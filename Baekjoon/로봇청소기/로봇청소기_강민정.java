import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int[][] arr;
    static int n;
    static int m;
    static int answer;
    static int x;
    static int y;
    static int d;

    // 상우하좌
    final static int[] dx = {-1, 0, 1, 0};
    final static int[] dy = {0, 1, 0, -1};

    public static int getBackDirection(int d) {
        switch(d) {
            case 0:     // 상
                return 2;       // 하
            case 1:     // 우
                return 3;       // 좌
            case 2:     // 하
                return 0;       // 상
        }
        return 1;       // 좌일 때 우
    }

    public static int getRotateDirection(int d) {
        switch(d) {
            case 0:     // 상
                return 3;   // 좌
            case 1:     // 우
                return 0;   // 상
            case 2:     // 하
                return 1;   // 우
        }
        return 2;   // 좌일 때
    }

    public static void rotate() {
        d = getRotateDirection(d);      // 반시계 방향으로 회전
        // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (!isValid(nx, ny) || arr[nx][ny] != 0) {
            return;
        }
        arr[nx][ny] = 2;
        x = nx;
        y = ny;
        answer++;
    }

    public static boolean backMove() {
        // 바라보는 방향을 유지한 채로 한 칸 후진
        int backD = getBackDirection(d);
        int nx = x + dx[backD];
        int ny = y + dy[backD];

        if (isValid(nx, ny) && arr[nx][ny] != 1) { // 벽이 아닌 곳으로 후진
            x = nx;
            y = ny;
            return true;
        }
        return false;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean isClean() {
        // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있으면 false
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isValid(nx, ny) && arr[nx][ny] == 0) {
                return false;
            }
        }
        return true; // 주위 네 칸 모두 청소가 되어있다면
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        // 로봇 청소기 입력받기
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());   // 0: 청소되지않은 빈 칸, 1: 벽
            }
        }

        x = r;
        y = c;

        while (true) {
            // 현재 칸을 청소
            if (arr[x][y] == 0) {
                arr[x][y] = 2; // 청소된 칸으로 변경
                answer++;
            }

            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는지 확인
            if (isClean()) {
                if (!backMove()) {
                    break; // 후진이 불가능하면 종료
                }
            } else {
                rotate(); // 청소되지 않은 칸이 있으면 회전 후 청소
            }
        }

        System.out.println(answer);
    }
}
