import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static char[][] map;
	static List<Integer[]> teachers;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teachers = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teachers.add(new Integer[] {i, j});
				}
			}
		}
		
		for (int i = 0; i < N * N - 2; i++) {
			int ir = i / N;
			int ic = i % N;
			if (map[ir][ic] != 'X') continue;
			map[ir][ic] = 'O';
			for (int j = i + 1; j < N * N - 1; j++) {
				int jr = j / N;
				int jc = j % N;
				if (map[jr][jc] != 'X') continue;
				map[jr][jc] = 'O';
				for (int k = j + 1; k < N * N; k++) {
					int kr = k / N;
					int kc = k % N;
					if (map[kr][kc] != 'X') continue;
					map[kr][kc] = 'O';
					if (check()) {
						System.out.println("YES");
						return;
					}
					map[kr][kc] = 'X';
				}
				map[jr][jc] = 'X';
			}
			map[ir][ic] = 'X';
		}
		
		System.out.println("NO");
	}
	
	static boolean check() {
		for (int i = 0; i < teachers.size(); i++) {
			Integer[] t = teachers.get(i);
			int nr, nc;
			for (int j = 0; j < 4; j++) {
				nr = t[0] + dr[j];
				nc = t[1] + dc[j];
				if (0 <= nr && nr < N && 0 <= nc && nc < N) {
					if (!dfs(nr, nc, j)) return false;
				}
			}
		}
		return true;
	}
	
	static boolean dfs(int r, int c, int dir) {
		if (map[r][c] == 'S') { // 감시 못피함
			return false;
		}
		if (map[r][c] == 'O') { // 감시 피함
			return true;
		}
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if (0 <= nr && nr < N && 0 <= nc && nc < N) {
			return dfs(nr, nc, dir);
		}
		return true;
	}
}
