import java.util.*;
import java.io.*;
public class 배열복원하기_강정훈 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int[][] B = new int[H+X][W+Y];
        for (int row = 0; row < H+X; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < W+Y; column++) {
                B[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        // 배열 B의 조건
        // (i, j)가 두 배열 모두에 포함되지 않으면 Bi,j = 0
        // (i, j)가 두 배열 모두에 포함되면, Bi,j = A
        int[][] A = new int[H][W];
        for (int row = 0; row < H; row++) {
            for (int column = 0; column < W; column++) {
                // 두 배열 모두 포함되는 경우
                // row == 2, column == 1, 2
                if (X <= row && row < X + (H-X) && Y <= column && column < Y + (W-Y) ) {
                    A[row][column] = B[row][column] - A[row-X][column-Y];
                } else {
                    A[row][column] = B[row][column];
                }
            }
        }
        for (int row = 0; row < H; row++) {
            StringBuilder sb = new StringBuilder();
            for (int column = 0; column < W; column++) {
                sb.append(A[row][column]+" ");
            }
            System.out.println(sb);
        }
    }
}
