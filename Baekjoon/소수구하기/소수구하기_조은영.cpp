#include <iostream>
using namespace std;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, M = 0;

	cin >> N >> M;

	for (int i = N; i <= M; i++) {
		int cnt = 0;

		if (i == 1) continue;

		for (int j = 1; j*j <= i; j++) {
			if (i % j == 0) cnt++;
			if (cnt > 1) break;
		}

		if (cnt == 1) cout << i << "\n";
	}

	return 0;
}