#include <iostream>
#include <vector>
#include <queue>
#define INF 1e9
using namespace std;

vector<pair<int, int>>v[2][1001];
vector<int>dist[2];

void dijkstra(int k, int x) {
	dist[k][x] = 0; // �̰� �ڲ� ��Ծ�...
	
	priority_queue<pair<int, int>>pq;

	pq.push({ 0, x }); // �Ÿ�, ���� ���

	while (!pq.empty()) {
		int cost = -pq.top().first;
		int node = pq.top().second;

		pq.pop();

		if (dist[k][node] < cost) continue;

		for (int i = 0; i < v[k][node].size(); i++) {

			int nextNode = v[k][node][i].first;
			int nextCost = v[k][node][i].second + cost;

			if (dist[k][nextNode] > nextCost) {
				dist[k][nextNode] = nextCost;
				pq.push({ -nextCost, nextNode });
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int N = 0, M = 0, X = 0, from=0, to=0, cost=0;

	cin >> N >> M >> X;

	dist[0].assign(N + 1, INF);
	dist[1].assign(N + 1, INF);

	for (int i = 0; i < M; i++) {
		cin >> from >> to >> cost;

		// �ƴ� �� �ٺ��ΰ� �ϳ��� �ݴ�� �Է��ؾߵǴµ� �Ȱ��� ����
		v[0][from].push_back({ to, cost });
		v[1][to].push_back({ from, cost });
	}

	dijkstra(0, X);
	dijkstra(1, X);

	// Ž���ϸ� �ִ밪 ����
	int ans = 0;

	for (int i = 1; i <= N; i++) {
		ans = max(ans, dist[0][i] + dist[1][i]);
	}

	cout << ans << "\n";

	return 0;
}