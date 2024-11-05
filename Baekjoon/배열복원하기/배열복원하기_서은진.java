import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H;
    static int W;
    static int X;
    static int Y;
    static int[][] mapA;
    static int[][] mapB;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        mapA = new int[H][W];
        mapB = new int[H + X][W + Y];

        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                mapB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < H + X; i++) {
            for (int j = 0; j < W + Y; j++) {
                int n = mapB[i][j];
                if (n == 0) continue;

                if (inRange(i, j) && inRange(i - X, j - Y)) {
                    mapA[i][j] = n - mapA[i - X][j - Y];
                }

                if (inRange(i, j) && !inRange(i - X, j - Y)) {
                    mapA[i][j] = n;
                }

                if (!inRange(i, j) && !inRange(i - X, j - Y)) {
                    mapA[i - X][j - Y] = n;
                }

            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
 
            for (int j = 0; j < W; j++) {
                sb.append(mapA[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static boolean inRange(int x, int y) {
        if (x < 0 || y < 0 || x > H - 1 || y > W - 1) return false;
        return true;
    }
}
