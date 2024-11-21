#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
typedef pair<int, int> pii;
vector<pii> v;

bool comp(pii a, pii b) {
    if (a.second < b.second) {
        return true;
    }
    else if (a.second == b.second && a.first < b.first) {
        return true;
    }
    else {
        return false;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N;
    cin >> N;

    while (N-- > 0) {
        int s,e;
        cin >> s >> e;
        v.push_back(make_pair(s,e));
    }

    sort(v.begin(), v.end(), comp);

    int ans = 0;
    int end = 0;

    for (pii p : v) {
        if (p.first >= end) {
            ans++;
            end = p.second;
        }
    }
    
    cout << ans;

    return 0;
}
