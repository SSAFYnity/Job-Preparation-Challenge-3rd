#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// sum<=L�϶����� ���� �� ���� time++
// �ٵ� ������ ������
// �� ��á�µ� sum>L�̸� 0�־��ְ� time++

int main() {

	int N = 0, W = 0, L = 0;

	cin >> N >> W >> L;
	vector<int>v(N,0);
	queue<int>q;

	for (int i = 0; i < N; i++) {
		cin >> v[i];
	}

	int sum = 0, time=0;

	// ������ �־����
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