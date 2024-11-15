#include <iostream>
#include <algorithm>
#define MIN -2e9
using namespace std;

// ��... 1001���� 1002�� �ø��ϱ� �䳪��
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

	// 1���� ������ ���ʿ��� ���������� ä���
	for (int j = 1; j < M + 1; j++) {
		left_dp[1][j] = left_dp[1][j - 1] + arr[1][j];
		right_dp[1][j] = right_dp[1][j - 1] + arr[1][j];
	}

	// 2����ʹ� ���� ������ ���ʿ��� �������� �ͱ��� ���
	for (int i = 2; i < N + 1; i++) {

		// ���ʿ��� ������ ����
		for (int j = 1; j < M + 1; j++) {
			left_dp[i][j] = max(left_dp[i - 1][j], left_dp[i][j - 1]) + arr[i][j];
		}

		// �����ʿ��� ���� ����
		for (int j = M; j > 0; j--) {
			right_dp[i][j] = max(right_dp[i - 1][j], right_dp[i][j + 1]) + arr[i][j];
		}

		// �� �� �� ū��
		for (int j = 1; j < M + 1; j++) {
			left_dp[i][j] = max(left_dp[i][j], right_dp[i][j]);
			right_dp[i][j] = left_dp[i][j];
		}
	}

	cout << left_dp[N][M] << "\n";


	return 0;
}
