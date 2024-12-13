#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> cards) {
    int answer = 0;
    bool visited[101];
    fill(visited, visited + 101, false);
    vector<int>v;

    for (int i = 0; i < cards.size(); i++) {
        int idx = i;
        int cnt = 0;

        while (1) {
            // 다음꺼 방문처리, cnt++
            if (visited[idx]) {
                v.push_back(cnt);
                break;
            }
            visited[idx] = true;
            cnt++;
            idx = cards[idx] - 1;
        }
    }

    sort(v.begin(), v.end(), greater<int>());

    answer = v[0] * v[1];

    return answer;
}