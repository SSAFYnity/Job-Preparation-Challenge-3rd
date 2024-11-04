import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[][] map;
    static Point[] selected = new Point[3];
    static ArrayList<Point> blankList = new ArrayList<>();
    static ArrayList<Point> teacherList = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean resultFlag = false;
    static boolean[][] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        isSelected = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char c = st.nextToken().charAt(0);
                map[i][j] = c;
                if (c == 'X') blankList.add(new Point(i, j));
                else if(c=='T') teacherList.add(new Point(i,j));
            }
        }

        comb(0, 0);

        System.out.println(resultFlag?"YES" : "NO");
    }

    public static void comb(int cnt, int start) {
        if (resultFlag) return;

        if (cnt == 3) {
            // 종료조건
            if (isPossible()) resultFlag = true;
            return;
        }

        for (int i = start; i < blankList.size(); i++) {
            map[blankList.get(i).x][blankList.get(i).y] = 'O';
            comb(cnt + 1, i + 1);
            map[blankList.get(i).x][blankList.get(i).y] = 'X';
        }
    }

    public static boolean isPossible() {

        //선생님 입장에서 학생들을 발결할 수 있는지 확인
        for (int i = 0; i < teacherList.size(); i++) {
            Point t = teacherList.get(i);

            //4방탐색
            for (int j = 0; j < 4; j++) {
                //근데 4방 탐색을 벽 혹은 장애물이 나올 때 까지 진행할 수 있다.
                int tmp = 1;
                while (true) {
                    int nx = t.x + (dx[j] * tmp);
                    int ny = t.y + (dy[j] * tmp);

                    if (!inRange(nx, ny)) break;    //범위를 벗어나면 못 본다.
                    if (map[nx][ny] == 'O') break;  //장애물이 있으면 더이상 못 본다.

                    if (map[nx][ny] == 'S') return false;

                    tmp += 1;
                }
            }
        }

        return true;
    }


    public static boolean inRange(int x, int y) {
        if (x < 0 || y < 0 || x > N - 1 || y > N - 1) return false;
        return true;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}