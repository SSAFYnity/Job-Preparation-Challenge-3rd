import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시피하기_이호찬 {

	// NxN 크기의 격자 칸(복도)에 3개의 장애물 설치
	// 장애물에 막히기 전까지, T(선생님)가 있는 상하좌우 라인은 감시를 피할 수 없다.
	// 장애물을 설치하여 감시를 피할 수 있는 방법이 존재할 경우 "YES", 없을 경우 "NO" 출력

	private static int n;
	private static boolean isPossible;
	private static final int[] dRow = {-1, 1, 0, 0}, dCol = {0, 0, -1, 1};
	private static int[] selectedNum;
	private static List<int[]> teachers;
	private static char[][] board;

	private static void combination(int depth, int start) {
		if (isPossible) {
			return;
		}

		if (depth == 3) {
			// 장애물 설치
			for (int i = 0; i < 3; i++) {
				board[selectedNum[i] / n][selectedNum[i] % n] = 'O';
			}
			simulation();
			// 장애물 제거
			for (int i = 0; i < 3; i++) {
				board[selectedNum[i] / n][selectedNum[i] % n] = 'X';
			}
			return;
		}

		for (int i = start; i < n * n; i++) {
			if (board[i / n][i % n] != 'X') {
				continue;
			}
			selectedNum[depth] = i;
			combination(depth + 1, i + 1);
		}
	}

	private static void simulation() {
		// T를 기준으로 상하좌우 탐색 (장애물 이전까지)
		for (int i = 0; i < teachers.size(); i++) {
			int[] cur = teachers.get(i);
			for (int k = 0; k < 4; k++) {
				int r = cur[0];
				int c = cur[1];
				while (true) {
					r += dRow[k];
					c += dCol[k];
					// 복도 범위 확인
					if (r < 0 || c < 0 || r >= n || c >= n) {
						break;
					}
					// 학생 발견 시, 실패
					if (board[r][c] == 'S') {
						return;
					}
					// 장애물 발견 시, Stop
					if (board[r][c] == 'O') {
						break;
					}
				}
			}
		}
		isPossible = true;
	}

	private static void init() {
		board = new char[n][n];
		selectedNum = new int[3];
		teachers = new ArrayList<>();
		isPossible = false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine().trim());
		init();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = st.nextToken().charAt(0);
				// 선생님 위치 저장
				if (board[i][j] == 'T') {
					teachers.add(new int[] {i, j});
				}
			}
		}

		combination(0, 0);

		if (isPossible) {
			bw.write("YES\n");
		} else {
			bw.write("NO\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}