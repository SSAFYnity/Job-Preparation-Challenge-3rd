#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>

using namespace std;

struct Node {
    int v, cost;
    Node(int v, int cost) : v(v), cost(cost) {}
};

struct Compare {
    bool operator()(const Node& a, const Node& b) {
        return a.cost > b.cost;
    }
};

const int INF = 1234567890;
int N, M, X, ans = 0;
vector<vector<Node>> graph;

int dijkstra(int start, int end) {
    vector<int> dist(N + 1, INF);
    vector<bool> visited(N + 1, false);
    priority_queue<Node, vector<Node>, Compare> pq;

    dist[start] = 0;
    pq.push(Node(start, 0));

    while (!pq.empty()) {
        Node curNode = pq.top();
        pq.pop();

        if (visited[curNode.v]) continue;
        visited[curNode.v] = true;

        for (const Node& nextNode : graph[curNode.v]) {
            if (dist[nextNode.v] > dist[curNode.v] + nextNode.cost) {
                dist[nextNode.v] = dist[curNode.v] + nextNode.cost;
                pq.push(Node(nextNode.v, dist[nextNode.v]));
            }
        }
    }

    return dist[end];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M >> X;
    graph.resize(N + 1);

    for (int i = 0; i < M; i++) {
        int start, end, cost;
        cin >> start >> end >> cost;
        graph[start].emplace_back(end, cost);
    }

    for (int i = 1; i <= N; i++) {
        ans = max(ans, dijkstra(i, X) + dijkstra(X, i));
    }

    cout << ans;

    return 0;
}
