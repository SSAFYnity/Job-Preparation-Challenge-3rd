#include <iostream>
#include <algorithm>
using namespace std;

int main() {

	int N = 0;
	int arr[100001] = { 0, };
	int dp[100001] = { 0, };

	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	dp[1] = arr[1];
	dp[2] = dp[1] + arr[2];

	for (int i = 3; i <= N; i++) {
		dp[i] = max(dp[i - 3] + arr[i - 1] + arr[i], max(dp[i-1], dp[i - 2] + arr[i]));
	}

	cout << dp[N] << "\n";

	return 0;
}