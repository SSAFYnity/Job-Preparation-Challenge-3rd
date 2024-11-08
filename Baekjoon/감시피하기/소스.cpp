#include <iostream>
#include <vector>
using namespace std;

int N = 0;
bool flag = false;
char arr[6][6] = { ' ' };
vector<pair<int,int>>xPosition;
vector<pair<int, int>>tPosition;
vector<pair<int,int>>picked;

bool calc(vector<pair<int,int>>v) {
	// T가 상하좌우로 빔 쏘는데 S만나면 NO, S만나기 전에 O 만나면 break

	char temp[6][6] = { ' ' };

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			temp[i][j] = arr[i][j];
		}
	}

	for (int i = 0; i < 3; i++) {
		// 장애물 설치
		temp[v[i].first][v[i].second] = 'O';
	}

	for (int i = 0; i < tPosition.size(); i++) {
		int x = tPosition[i].first;
		int y = tPosition[i].second;

		// 상 방향 탐색
		for (int r = x - 1; r >= 0; r--) {
			if (temp[r][y] == 'O') break;
			if (temp[r][y] == 'S') return false;
		}

		// 하 방향 탐색
		for (int r = x + 1; r < N; r++) {
			if (temp[r][y] == 'O') break;
			if (temp[r][y] == 'S') return false;
		}

		// 좌 방향 탐색
		for (int c = y - 1; c >= 0; c--) {
			if (temp[x][c] == 'O') break;
			if (temp[x][c] == 'S') return false;
		}

		// 우 방향 탐색
		for (int c = y + 1; c < N; c++) {
			if (temp[x][c] == 'O') break;
			if (temp[x][c] == 'S') return false;
		}
	}

	return true;
}


void combination(int cnt, int idx) {
	if (cnt == 3) {
		if (calc(picked)) flag = true;
		return;
	}

	for (int i = idx; i < xPosition.size(); i++) {
		picked.push_back(xPosition[i]);
		combination(cnt + 1, idx + 1);
		picked.pop_back();
	}
}


int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 'X') xPosition.push_back({ i, j });
			else if (arr[i][j] == 'T') tPosition.push_back({ i,j });
		}
	}

	combination(0,0);

	if (flag) cout << "YES";
	else cout << "NO";

	return 0;
}