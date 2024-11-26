#include <iostream>
#include <vector>
using namespace std;

struct Robo {
    int x, y, dir;
    Robo(int x, int y, int dir) : x(x), y(y), dir(dir) {}
};

// 전역변수
Robo* robo;
int N, M;
vector<vector<int>> room;
int dx[] = { 0, 1, 0, -1 };
int dy[] = { -1, 0, 1, 0 };

// 함수
void clean();
bool dirty(int x, int y);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> N >> M;
    room.assign(N, vector<int>(M));

    int Y, X, dir;
    cin >> Y >> X >> dir;
    robo = new Robo(X, Y, dir);

    for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
            cin >> room[y][x];
        }
    }

    // 청소
    clean();

    // 청소된 칸 개수 세기
    int ans = 0;
    for (const auto& row : room) {
        for (int cell : row) {
            if (cell == -1) {
                ans++;
            }
        }
    }

    cout << ans;
    delete robo;
    return 0;
}

void clean() {
    while (true) {
        // 1. 현재 칸이 0인 경우
        if (room[robo->y][robo->x] == 0) {
            room[robo->y][robo->x] = -1; // 청소
        }

        // 2. 동서남북에 0이 없는 경우
        else if (!dirty(robo->x, robo->y)) {
            // 1) 방향을 유지한 채로 후진 가능
            int nx = robo->x + dx[(robo->dir + 2) % 4];
            int ny = robo->y + dy[(robo->dir + 2) % 4];
            if (room[ny][nx] != 1) {
                robo->x = nx;
                robo->y = ny;
            }
            // 2) 뒤쪽 칸이 벽인 경우
            else {
                return;
            }
        }

        // 3. 동서남북에 0이 있는 경우
        else {
            // 1) 반시계 회전
            robo->dir = (robo->dir + 3) % 4;

            // 2) 청소되지 않은 빈칸인 경우 한칸 전진
            int nx = robo->x + dx[robo->dir];
            int ny = robo->y + dy[robo->dir];
            if (room[ny][nx] == 0) {
                robo->x = nx;
                robo->y = ny;
            }
        }
    }
}

// 사방탐색
bool dirty(int x, int y) {
    for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (room[ny][nx] == 0) {
            return true;
        }
    }
    return false;
}
