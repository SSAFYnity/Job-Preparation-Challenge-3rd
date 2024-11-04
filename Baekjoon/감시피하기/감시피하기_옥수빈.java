import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static List<Point> wallList = new ArrayList<>();
    static List<Point> teacherList = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean answer = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().replace(" ", "").toCharArray();
        }

        teacherList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char c = map[i][j];
                if (c == 'T') {
                    teacherList.add(new Point(i, j));
                }
            }
        }

        makeWall(N, 0, new ArrayList<>());
        System.out.println(answer ? "YES" : "NO");
    }

    private static void makeWall(int N, int idx, List<Point> list) {
        if (list.size() == 3) {
            if (isFindStudent(N, list))
                answer = true;
            return;
        }

        for (int i = idx; i < N * N; i++) {
            int r = i / N;
            int c = i % N;
            if (map[r][c] == 'X') {
                map[r][c] = 'O';
                list.add(new Point(r, c));
                makeWall(N, i + 1, list);
                map[r][c] = 'X';
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isFindStudent(int n, List<Point> list) {
        for (Point teacher : teacherList) {
            int r = teacher.x;
            int c = teacher.y;
            for (int i = 0; i < 4; i++) {
                int nr = r;
                int nc = c;
                while (true) {
                    nr += dr[i];
                    nc += dc[i];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n || map[nr][nc] == 'O') break;
                    if (map[nr][nc] == 'S') return false;
                }
            }
        }
        return true;
    }
}