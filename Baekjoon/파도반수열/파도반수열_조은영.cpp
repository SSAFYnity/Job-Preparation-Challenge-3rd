#include <iostream>
using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int T = 0, N = 0;
	long dp[101];

	fill(dp, dp + 101, 1);

	for (int i = 3; i < 101; i++) {
		dp[i] = dp[i - 3] + dp[i - 2];
	}

	cin >> T;

	while (T--) {
		cin >> N;

		cout << dp[N-1] << "\n";
	}

	return 0;
}