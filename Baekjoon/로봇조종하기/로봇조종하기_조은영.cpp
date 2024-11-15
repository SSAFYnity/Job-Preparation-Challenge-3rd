#include <iostream>
#include <algorithm>
#define MIN -2e9
using namespace std;

// 엥... 1001에서 1002로 늘리니까 답나옴
int arr[1002][1002];
int left_dp[1002][1002];
int right_dp[1002][1002];

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, M = 0;

	cin >> N >> M;

	for (int i = 1; i < N + 1; i++) {
		for (int j = 1; j < M + 1; j++) {
			cin >> arr[i][j];
		}
	}

	fill(&left_dp[0][0], &left_dp[N+1][M + 1], MIN);
	fill(&right_dp[0][0], &right_dp[N+1][M + 1], MIN);


	left_dp[0][1] = 0;
	left_dp[1][0] = 0;
	right_dp[1][0] = 0;
	right_dp[0][1] = 0;

	// 1행은 무조건 왼쪽에서 오른쪽으로 채우기
	for (int j = 1; j < M + 1; j++) {
		left_dp[1][j] = left_dp[1][j - 1] + arr[1][j];
		right_dp[1][j] = right_dp[1][j - 1] + arr[1][j];
	}

	// 2행부터는 왼쪽 오른쪽 위쪽에서 내려오는 것까지 고려
	for (int i = 2; i < N + 1; i++) {

		// 왼쪽에서 오른쪽 방향
		for (int j = 1; j < M + 1; j++) {
			left_dp[i][j] = max(left_dp[i - 1][j], left_dp[i][j - 1]) + arr[i][j];
		}

		// 오른쪽에서 왼쪽 방향
		for (int j = M; j > 0; j--) {
			right_dp[i][j] = max(right_dp[i - 1][j], right_dp[i][j + 1]) + arr[i][j];
		}

		// 둘 중 더 큰값
		for (int j = 1; j < M + 1; j++) {
			left_dp[i][j] = max(left_dp[i][j], right_dp[i][j]);
			right_dp[i][j] = left_dp[i][j];
		}
	}

	cout << left_dp[N][M] << "\n";


	return 0;
}
