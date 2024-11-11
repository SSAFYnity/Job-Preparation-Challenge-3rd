#include <iostream>
#include <queue>
using namespace std;

void check(int R, int C);
void moveUnion(bool(&visited)[101][101], int r, int c, int R, int C);

bool map[101][101] = {false, };
int dr[] = { 1,0,-1,0 };
int dc[] = { 0,1,0,-1 };

int main()
{
    int R, C;
    cin >> R >> C;

    for (int r = 0; r < R; r++) {
        string str;
        cin >> str;
        for (int c = 0; c < C; c++) {
            if (str.at(c) == 'x') {
                map[r][c] = true;
            }
        }
    }

    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        int height;
        cin >> height;
        int hr = R - height;
        if (i % 2 == 0) {
            // left
            for (int c = 0; c < C; c++) {
                if (map[hr][c]) {
                    map[hr][c] = 0;
                    break;
                }
            }
        }
        else {
            // right
            for (int c = C-1; c >= 0; c--) {
                if (map[hr][c]) {
                    map[hr][c] = 0;
                    break;
                }
            }
        }
        check(R, C);
    }

    // print answer
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            if (map[r][c] == 0) {
                cout << ".";
            }
            else {
                cout << "x";
            }
        }
        cout << "\n";
    }

}

void check(int R, int C) {
    bool visited[101][101] = { false, };
    for (int r = 0; r < R; r++) {
        for (int c = 0; c < C; c++) {
            if (!visited[r][c] && map[r][c]) {
                moveUnion(visited, r, c, R, C);
            }
        }
    }
}

// 클러스터 이동
void moveUnion(bool(&visited)[101][101], int r, int c, int R, int C) {
    queue<pair<int, int>> q;
    q.push(make_pair(r, c));
    bool cluster[101][101] = { false, };
    cluster[r][c] = true;
    visited[r][c] = true;
    int root = r;

    // 현재 클러스터 범위
    while (!q.empty()) {
        pair<int, int> node = q.front();
        q.pop();
        for (int d = 0; d < 4; d++) {
            int nr = node.first + dr[d];
            int nc = node.second + dc[d];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (map[nr][nc] && !cluster[nr][nc]) {
                cluster[nr][nc] = true;
                visited[nr][nc] = true;
                q.push(make_pair(nr, nc));
                root = max(root, nr);
            }
        }
    }

    if (root == R - 1) return;

    int gap = R - 1;
    for (int c = 0; c < C; c++) {
        for (int r = 0; r < R; r++) {
            if (cluster[r][c]) {
                for (int i = 1; i <= gap; i++) {
                    int nr = r + i;
                    if (nr >= R) break;
                    if (!cluster[nr][c] && gap >= i) {
                        if (map[nr][c]) {
                            gap = i - 1;
                            break;
                        }
                        else if (nr == R - 1) {
                            gap = i;
                            break;
                        }
                    }
                }
            }
        }
    }

    // gap칸 이동시키기
    for (int r = R - 1; r >= 0; r--) {
        for (int c = C - 1; c >= 0; c--) {
            if (cluster[r][c]) {
                map[r][c] = false;
                visited[r][c] = false;
                map[r + gap][c] = true;
                visited[r + gap][c] = true;
            }
        }
    }
}
