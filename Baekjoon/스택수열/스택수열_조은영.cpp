#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int n = 0;
	int input = 0;
	int cnt = 1;
	stack<int>s;
	vector<char>v;

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> input;
		while (1) {
			if (!s.empty() && s.top() == input) {
				s.pop();
				/*cout << "-" << endl;*/
				v.push_back('-');
				break;
			}
			if (!s.empty() && s.top() > input) {
				cout << "NO" << endl;
				return 0;
			}

			s.push(cnt);
			cnt++;
			/*cout << "Å¾Àº: " << s.top() << endl;*/
			/*cout << "+" << endl;*/
			v.push_back('+');
		}


	}

	for (auto a : v) cout << a << "\n";


	return 0;
}