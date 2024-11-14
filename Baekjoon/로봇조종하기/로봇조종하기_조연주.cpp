#include <iostream>
#include <algorithm>
using namespace std;

int MIN_VALUE = -100001;
int N, M;
int map[1001][1001];
int maxValue[1001][1001][3];
bool visited[1001][1001];
int dy[] = {0,-1,0};
int dx[] = {-1,0,1};


void dfs(int y, int x, int dir);

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    fill(&maxValue[0][0][0], &maxValue[1000][1000][3], MIN_VALUE);

    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        for (int k = 0; k < M; k++) {
            cin >> map[i][k];
        }
    }

    for (int i = 0; i < 3; i++) {
        maxValue[0][0][i] = map[0][0];
    }
    dfs(N-1, M-1, 0);

    cout << maxValue[N-1][M-1][0];
}

void dfs(int y, int x, int dir) {
    if (maxValue[y][x][dir] != MIN_VALUE) return;

    int mV = MIN_VALUE;
    visited[y][x] = true;

    for (int d = 0; d < 3; d++) {
        if (y == N - 1 && d == 2) continue;
        int ny = y + dy[d];
        int nx = x + dx[d];
        if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;

        if (!visited[ny][nx]) {
            dfs(ny, nx, d);
            mV = max(mV, maxValue[ny][nx][d]+map[y][x]);
        }
    }

    visited[y][x] = false;
    maxValue[y][x][dir] = mV;
}

