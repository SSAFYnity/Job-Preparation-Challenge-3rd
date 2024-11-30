#include <string>
#include <vector>
#include <unordered_map>
#include <map>

using namespace std;

unordered_map<string, string>relation;
map<string, int>mp;

void dfs(string name, int money) {

    int remain = money / 10; // 절사금액
    mp[name] += money - remain;

    if (name == "-" || remain < 1) return;
    dfs(relation[name], remain);
}

vector<int> solution(vector<string> enroll, vector<string> referral, vector<string> seller, vector<int> amount) {
    vector<int> answer;

    for (int i = 0; i < enroll.size(); i++) {
        relation[enroll[i]] = referral[i];
    }

    for (int i = 0; i < seller.size(); i++) {
        dfs(seller[i], amount[i] * 100);
    }

    for (int i = 0; i < enroll.size(); i++) {
        answer.push_back(mp[enroll[i]]);
    }

    return answer;
}