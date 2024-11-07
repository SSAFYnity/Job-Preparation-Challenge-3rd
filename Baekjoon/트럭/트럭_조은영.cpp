#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// sum<=L일때까지 넣을 수 있음 time++
// 근데 꽉차면 빼야함
// 꽉 안찼는데 sum>L이면 0넣어주고 time++

int main() {

	int N = 0, W = 0, L = 0;

	cin >> N >> W >> L;
	vector<int>v(N,0);
	queue<int>q;

	for (int i = 0; i < N; i++) {
		cin >> v[i];
	}

	int sum = 0, time=0;

	// 끝까지 넣어야함
	for (int i = 0; i < N; i++) {

		while (1) {
			if (q.size() == W) {
				sum -= q.front();
				q.pop();
			}
			else if (sum + v[i] > L) {
				q.push(0);
				time++;
			}
			else if(sum+v[i]<=L) break;
		}
		
		sum += v[i];
		time++;
		q.push(v[i]);

	}

	time += W;

	cout << time << "\n";

	return 0;
}