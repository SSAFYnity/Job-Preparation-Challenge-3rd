import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        int n = weights.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(weights[i], map.getOrDefault(weights[i], 0)+1);
        }
        int[][] mul = {{4, 3}, {3, 2}, {2, 1}};
        for (int num : map.keySet()) {
            long onecnt = map.get(num);
            if (onecnt >= 2) {
                long a = onecnt;
                a *= (a-1);
                a /= 2;
                answer += a;
            }
            for (int i = 0; i < 3; i++) {
                if (num * mul[i][0] % mul[i][1] != 0) continue;
                int temp = num * mul[i][0] / mul[i][1];
                if (map.containsKey(temp)) {
                    long twocnt = map.get(temp);
                    answer += (onecnt * twocnt);
                }
            }
        }
        return answer;
    }
}