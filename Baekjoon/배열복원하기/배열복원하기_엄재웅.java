import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] arr = new int[H + X][W + Y];
        for(int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        int[][] answer = new int[H][W];
        for(int y = 0; y < H; y++) {
            for(int x = 0; x < W; x++) {
                int nx = x - Y;
                int ny = y - X;

                if(nx < 0 || ny < 0) {
                    answer[y][x] = arr[y][x];
                } else {
                    answer[y][x] = arr[y][x] - answer[ny][nx];
                }
                sb.append(answer[y][x]).append(' ');
            }
            System.out.println(sb);
            sb.setLength(0);
        }
    }
}
