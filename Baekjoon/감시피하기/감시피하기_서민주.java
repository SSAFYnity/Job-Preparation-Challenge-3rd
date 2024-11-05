import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int y, x;
        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static char[][] arr;
    static char[][] copy;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        for (int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j<n; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }
        dfs(0);
        System.out.println("NO");

    }

    public static void dfs(int level) {
        if (level==3) {
            if (bfs()) {
                System.out.println("YES");
                System.exit(0);
            }
        return;
        }
        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                if (arr[i][j]=='X') {
                    arr[i][j] = 'O';
                    dfs(level+1);
                    arr[i][j] = 'X';
                }
            }
        }
    }

    public static boolean bfs() {
        copy = new char[n][n];
        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                copy[i][j] = arr[i][j];
                if (copy[i][j]=='T') {
                    q.offer(new Pair(i, j));
                }
            }
        }
        int[] dy = new int[]{0, 1, 0, -1};
        int[] dx = new int[]{1, 0, -1, 0};

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int y = curr.y, x = curr.x;


            for (int i = 0; i<4; i++) {
                int ny = y;
                int nx = x;

                while (true) {
                    ny += dy[i];
                    nx += dx[i];
                    if (ny < 0 || ny >= n || nx < 0 || nx >= n) break;
                    if (copy[ny][nx] == 'O') break;
                    if (copy[ny][nx] == 'S') return false;
                }
            }
        }
        return true;
    }
}