import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 배열복원하기_이호찬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// h, w, x, y
		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int[][] answer = new int[h][w];
		int[][] board = new int[h + x][w + y];

		for (int i = 0; i < h + x; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w + y; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 배열 복사
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				answer[i][j] = board[i][j];
			}
		}

		// 겹치는 부분 연산
		for (int i = x; i < h; i++) {
			for (int j = y; j < w; j++) {
				answer[i][j] -= answer[i - x][j - y];
			}
		}

		// 출력
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				bw.write(answer[i][j] + " ");
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
