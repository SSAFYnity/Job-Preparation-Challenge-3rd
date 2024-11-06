import java.util.*;
import java.io.*;

public class 배열복원하기_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");
        int H = Integer.parseInt(numbers[0]);
        int W = Integer.parseInt(numbers[1]);
        int X = Integer.parseInt(numbers[2]);
        int Y = Integer.parseInt(numbers[3]);

        int[][] arrB = new int[H+X][W+Y];

        for (int i = 0; i < H + X; i++) {
            String[] input = br.readLine().split(" ");
            int[] line = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            arrB[i] = line;
        }

        int[][] arrA = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                arrA[i][j] = arrB[i][j];
            }
        }

        for (int i = X; i < H; i++) {
            for (int j = Y; j < W; j++) {
                arrA[i][j] = arrB[i][j] - arrA[i-X][j-Y];
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(arrA[i][j] + " ");
            }
            System.out.println();
        }
    }
}
