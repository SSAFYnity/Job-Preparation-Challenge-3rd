import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int[] count = new int[a.length];

        for (int num : a) {
            count[num]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0 || count[i] * 2 <= answer) continue;

            int sum = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if ((a[j] == i || a[j + 1] == i) && (a[j] != a[j + 1])) {
                    sum += 2;
                    j++;
                }
            }

            answer = Math.max(answer, sum);
        }

        return answer;
    }
}
