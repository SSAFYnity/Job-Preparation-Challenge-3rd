#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    int n;
    cin >> n;

    vector<int> score;
    score.push_back(0);
    for (int i = 0; i < n; i++) {
        int s;
        cin >> s;
        score.push_back(s);
    }

    // dp[현재계단][연속해서밟은수]
    int dp[301][3] = {0,};
    dp[0][1] = score[0];

    for (int i = 1; i <= n; i++) {
        dp[i][1] = dp[i - 1][0] + score[i];
        dp[i][2] = dp[i - 1][1] + score[i];
        dp[i][0] = max(dp[i - 1][1],dp[i - 1][2]);
    }

    cout << max(dp[n][1], dp[n][2]);
 
    return 0;
}
