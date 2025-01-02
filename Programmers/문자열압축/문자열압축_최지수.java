import java.util.*;

class Solution {
    public int solution(String s) {
        int n = s.length();
        int answer = n;
        
        ArrayDeque<String> que = new ArrayDeque<>();
        for (int a = n/2; a > 0; a--) {
            int resultWhenA = n;
            que.clear();
            for (int i = 0; i+a <= n; i+=a) {
                que.add(s.substring(i, i+a));
            }
            int cnt = 1;
            String pre = que.poll();
            while (!que.isEmpty()) {
                String now = que.poll();
                if (pre.equals(now)) {
                    cnt++;
                } else {
                    if (cnt != 1) {
                        resultWhenA += String.valueOf(cnt).length();
                        resultWhenA -= (cnt-1) * a;
                        cnt = 1;
                    }
                    pre = now;
                }
            }
            if (cnt != 1) {
                resultWhenA += String.valueOf(cnt).length();
                resultWhenA -= (cnt-1) * a;
            }
            answer = Math.min(answer, resultWhenA);
        }
        return answer;
    }
}