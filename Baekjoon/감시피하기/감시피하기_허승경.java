import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map;
    static List<int[]> empty_space = new ArrayList<>();     // 빈 공간
    static List<int[]> teachers = new ArrayList<>();        // 선생님 공간
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'X') empty_space.add(new int[]{i, j}); 
                if (map[i][j] == 'T') teachers.add(new int[]{i, j});   
            }
        }

        if (placeWalls(0, 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean placeWalls(int count, int start) {
        if (count == 3) { // 3개의 벽을 다 설치했으면 감시 확인

            if(checkRoom()) return false;   // 감시 걸림
            else return true;               // 감시 통과
        }

        for (int i = start; i < empty_space.size(); i++) {
            int[] space = empty_space.get(i);
            map[space[0]][space[1]] = 'O'; // 벽 설치
            if (placeWalls(count + 1, i + 1)) return true;
            map[space[0]][space[1]] = 'X'; // 벽 제거
        }
        return false;
    }

    static boolean checkRoom() {
        for (int[] teacher : teachers) {
            int x = teacher[0];
            int y = teacher[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x;
                int ny = y;
                while (true) {
                    nx += dx[dir];
                    ny += dy[dir];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 'O') break;
                    if (map[nx][ny] == 'S') return true; // 학생이 감시에 걸림
                }
            }
        }
        return false;
    }
}
