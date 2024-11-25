import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] dr = {{-1,0,1,0}, {0,1,0,-1}};

    static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int answer = 1;
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];
        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit[y][x] = true;

        boolean cb = true;
        while (cb) {
            boolean check = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int w = y + dr[0][d];
                int v = x + dr[1][d];
                if (isOut(w,v) || room[w][v] == 1 || visit[w][v]) continue;
                check = true;
                answer++;
                visit[w][v] = true;
                y = w;
                x = v;
                break;
            }
            if (!check) {
                y += dr[0][(d+2)%4];
                x += dr[1][(d+2)%4];
                if (isOut(y,x) || room[y][x] == 1) {
                    cb = false;
                }
            }
        }

        System.out.println(answer);
    }
}

