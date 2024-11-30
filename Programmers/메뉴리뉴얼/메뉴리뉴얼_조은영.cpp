#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

map<string, int>mp;

void dfs(string& o, int len, string temp, int idx) {
    if (temp.length() == len) {
        mp[temp]++;
        return;
    }

    for (int i = idx; i < o.length(); i++) {
        dfs(o, len, temp + o[i], i + 1);
    }
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;

    for (int len : course) {
        for (string order : orders) {
            sort(order.begin(), order.end());
            dfs(order, len, "", 0);
        }

        int maxCnt = 0;
        // 가장 많은 횟수의 string만 저장 (2회 이상)

        for (auto it : mp) {
            maxCnt = max(it.second, maxCnt);
        }

        for (auto it : mp) {
            if (it.second >= 2 && maxCnt == mp[it.first]) answer.push_back(it.first);
        }
        mp.clear();
    }

    sort(answer.begin(), answer.end());

    return answer;
}