#include <iostream>
#include <stack>
using namespace std;

string st = "";

bool isVPS(string st, stack<char>s) {
	for (int j = 0; j < st.length(); j++) {
		if (st[j] == '(') s.push(st[j]);
		else {
			if (!s.empty()) s.pop();
			else return false;
		}
	}
	if (s.empty()) return true;
	else return false;
}

int main() {

	int N = 0;

	cin >> N;

	for (int i = 0; i < N; i++) {
		stack<char>s;
		cin >> st;

		if (isVPS(st, s)) cout << "YES" << "\n";
		else cout << "NO" << "\n";
	}

	return 0;
}