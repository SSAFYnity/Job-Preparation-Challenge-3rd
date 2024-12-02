import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 배열복원하기_안창호 {
    static int H, W, X, Y;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        H = Integer.parseInt(str[0]);
        W = Integer.parseInt(str[1]);
        X = Integer.parseInt(str[2]);
        Y = Integer.parseInt(str[3]);

        int[][] B = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < W + Y; j++) {
                B[i][j] = Integer.parseInt(str[j]);
            }
        }
        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (isIntersect(i, j)) {
                    A[i][j] = B[i][j] - A[i - X][j - Y];
                } else {
                    A[i][j] = B[i][j];
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static boolean isIntersect(int i, int j) {
        return (X <= i) && (Y <= j);
    }
}
