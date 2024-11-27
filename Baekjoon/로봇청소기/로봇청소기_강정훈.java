import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        if (direction == 1) {
            direction = 3;
        } else if (direction == 3) {
            direction = 1;
        }
        int[][] map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        Robot robot = new Robot(new int[]{startR, startC}, direction, map);
        robot.execute();
        System.out.println(robot.count);
    }

}
class Robot {
    int count = 0;
    int[] currentIndex;
    int direction;
    int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    public Robot(int[] currentIndex, int direction, int[][] map) {
        this.currentIndex = currentIndex;
        this.direction = direction;
        this.map = map;
    }
    public void execute() {
        while(true) {
            clean();
            if(checkDirtyPlace()) {
                rotate();
                if(checkFront()) {
                    moveToFront();
                }
            } else {
                int backDirection = calculateBackDirection();
                if (checkBackUp(backDirection)) {
                    moveToBack(backDirection);
                } else {
                    return;
                }
            }
        }
    }
    private void moveToFront() {
        currentIndex[0] = currentIndex[0] + dr[direction];
        currentIndex[1] = currentIndex[1] + dc[direction];
    }
    private void moveToBack(int backDirection) {
        currentIndex[0] = currentIndex[0] + dr[backDirection];
        currentIndex[1] = currentIndex[1] + dc[backDirection];
    }
    private boolean checkFront() {
        int nr = currentIndex[0] + dr[direction];
        int nc = currentIndex[1] + dc[direction];
        return map[nr][nc] == 0;
    }
    private void clean() {
        int r = currentIndex[0];
        int c = currentIndex[1];
        if (map[r][c] == 0) {
            map[r][c] = 2;
            count++;
        }
    }
    private int calculateBackDirection() {
        if (direction == 0 ) {
            return 2;
        } else if (direction == 1) {
            return 3;
        } else if (direction == 2) {
            return 0;
        } else {
            return 1;
        }
    }
    private boolean checkDirtyPlace() {
        for (int index = 0; index < 4; index++) {
            int nr = currentIndex[0] + dr[index];
            int nc = currentIndex[1] + dc[index];
            if (map[nr][nc] == 0) {
                return true;
            }
        }
        return false;
    }
    private boolean checkBackUp(int backDirection) {
        int nr = currentIndex[0] + dr[backDirection];
        int nc = currentIndex[1] + dc[backDirection];
        return map[nr][nc] != 1;
    }
    private void rotate() {
        if (direction == 3) {
            direction = 0;
        } else {
            direction += 1;
        }
    }
}