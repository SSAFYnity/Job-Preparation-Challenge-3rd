#include <string>
#include <vector>

using namespace std;

long long solution(vector<int> weights) {
    long long answer = 0;
    int len = weights.size();
    int base = 0;
    vector<int>cnt(len, 0);

    for (int i = 0; i < len; i++) {
        cnt[weights[i]]++;
    }

    for (int i = 0; i < len; i++) {
        if (weights[i] % 2 == 0) {
            base = (weights[i] / 2) * 3;
            if (base <= 1000) answer += cnt[base];
        }
        if (weights[i] % 3 == 0) {
            base = (weights[i] / 3) * 4;
            if (base <= 1000) answer += cnt[base];
        }
        base = weights[i] * 2;
        if (base <= 1000) answer += cnt[base];
    }

    // 몸무게가 같은 경우 (형변환)
    for (int i = 100; i <= 1000; i++) {
        int weightCnt = cnt[i];
        if (weightCnt >= 2) answer += (long long)weightCnt * (weightCnt - 1) / 2;
    }

    return answer;
}