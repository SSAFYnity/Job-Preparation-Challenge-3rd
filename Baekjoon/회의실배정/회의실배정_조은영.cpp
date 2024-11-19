#include <iostream>
#include<vector>
#include <algorithm>
using namespace std;

bool compare(pair<int, int>num1, pair<int, int>num2) {
	if (num1.second == num2.second) {
		return num1.first < num2.first;
	}
	return num1.second < num2.second;
}

int main() {

	int n = 0;
	long long a = 0, b = 0;
	vector<pair<long long, long long>>v;

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> a >> b;
		v.push_back(make_pair(a, b));
	}

	sort(v.begin(), v.end(), compare);

	int cnt = 1;
	long long start = v[0].second;

	for (int i = 1; i < n; i++) {
		if (v[i].first >= start) {
			start = v[i].second;
			cnt++;
		}
	}

	cout << cnt << endl;

	return 0;
}