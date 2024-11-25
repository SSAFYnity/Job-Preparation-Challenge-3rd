#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;
vector<int> arr;
vector<int> dp;
vector<bool> written;

int DP(int idx) {
    if (idx == 0) {
        dp[0] = 0;
        written[0] = true;
        return dp[0];
    }

    if (idx == 1) {
        dp[1] = arr[1];
        written[1] = true;
        return dp[1];
    }

    if (idx == 2) {
        dp[2] = arr[1] + arr[2];
        written[2] = true;
        return dp[2];
    }

    if (!written[idx]) {
        // 포도주를 먹는 경우: 이전 포도주를 먹는 경우 vs 안 먹는 경우
        dp[idx] = max(DP(idx - 3) + arr[idx - 1] + arr[idx], DP(idx - 2) + arr[idx]);

        // 포도주를 먹지 않는 경우 vs 포도주를 먹는 경우
        dp[idx] = max(dp[idx], DP(idx - 1));

        written[idx] = true;
    }

    return dp[idx];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    
    cin >> n; // 포도주 잔 개수

    arr.resize(n + 1);
    dp.resize(n + 1, 0);
    written.resize(n + 1, false);

    // 포도주의 양 입력받기
    for (int i = 1; i <= n; i++) {
        cin >> arr[i];
    }

    cout << DP(n) << endl;

    return 0;
}
