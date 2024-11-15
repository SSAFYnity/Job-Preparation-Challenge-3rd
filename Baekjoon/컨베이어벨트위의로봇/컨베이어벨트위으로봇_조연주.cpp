#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int N, K, cnt;
vector<int> belt;
deque<bool> robot;

void rotate() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    // 벨트 회전
    int temp = belt.back();
    for (int i = belt.size() - 1; i > 0; i--) {
        belt[i] = belt[i - 1];
    }
    belt[0] = temp;

    // 로봇 회전
    robot.push_front(robot.back());
    robot.pop_back();
    robot[N - 1] = false; // 로봇 내리기
}

void moveRobot() {
    for (int i = N - 2; i >= 0; i--) {
        if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
            robot[i] = false;
            robot[i + 1] = true;
            belt[i + 1]--;
        }
    }
    robot[N - 1] = false; // 로봇 내리기
}

void addRobot() {
    if (belt[0] > 0) {
        robot[0] = true; // 로봇 올리기
        belt[0]--; // 내구도 감소
    }
}

bool isOver() {
    int zeroCount = 0;
    for (int durability : belt) {
        if (durability == 0) {
            zeroCount++;
            if (zeroCount >= K) return true;
        }
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> K;
    belt.resize(2 * N);
    robot.resize(N, false);

    for (int i = 0; i < 2 * N; i++) {
        cin >> belt[i];
    }

    do {
        cnt++;
        rotate();
        moveRobot();
        addRobot();
    } while (!isOver());

    cout << cnt << '\n';

    return 0;
}
