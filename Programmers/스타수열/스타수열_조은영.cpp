#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int solution(vector<int> a) {
    int answer = 0;
    int size_a = a.size();
    vector<int>cnt(size_a);

    // 원소 개수
    for (int i = 0; i < size_a; i++) {
        cnt[a[i]]++;
    }


    for (int i = 0; i < cnt.size(); i++) {
        // 존재하는 숫자만 확인. 그 중에서도 답보다 큰 경우만
        if (cnt[i] <= answer) continue;
        int result = 0;

        for (int j = 0; j < a.size() - 1; j++) {
            // 쌍에 해당 숫자가 있어야하고 두 수는 달라야함
            if ((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                result++;
                j++; // 만약 조건에 해당되면 하나 건너뜀
            }
        }

        answer = max(answer, result);
    }

    return answer * 2;
}