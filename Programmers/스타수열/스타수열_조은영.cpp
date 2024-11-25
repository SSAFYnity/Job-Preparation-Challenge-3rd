#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int solution(vector<int> a) {
    int answer = 0;
    int size_a = a.size();
    vector<int>cnt(size_a);

    // ���� ����
    for (int i = 0; i < size_a; i++) {
        cnt[a[i]]++;
    }


    for (int i = 0; i < cnt.size(); i++) {
        // �����ϴ� ���ڸ� Ȯ��. �� �߿����� �亸�� ū ��츸
        if (cnt[i] <= answer) continue;
        int result = 0;

        for (int j = 0; j < a.size() - 1; j++) {
            // �ֿ� �ش� ���ڰ� �־���ϰ� �� ���� �޶����
            if ((a[j] == i || a[j + 1] == i) && a[j] != a[j + 1]) {
                result++;
                j++; // ���� ���ǿ� �ش�Ǹ� �ϳ� �ǳʶ�
            }
        }

        answer = max(answer, result);
    }

    return answer * 2;
}