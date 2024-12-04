#include <iostream>
#include <vector>
#include <string>

using namespace std;

vector<long long> dp(101, 0);

long long func(int n) {
    if (dp[n] == 0) {
        dp[n] = func(n - 1) + func(n - 5);
    }
    return dp[n];
}

int main() {
    int T;
    cin >> T;

    for (int i = 1; i <= 3; i++) {
        dp[i] = 1;
    }

    for (int i = 4; i <= 5; i++) {
        dp[i] = 2;
    }

    string result;

    while (T-- > 0) {
        int n;
        cin >> n;
        result += to_string(func(n)) + "\n";
    }

    cout << result;

    return 0;
}
