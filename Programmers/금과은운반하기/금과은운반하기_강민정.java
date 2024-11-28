class Solution {
    static final long MAX_TIME = (long)(10e9 * 2 * 10e5 * 2);

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = MAX_TIME;
        int n = g.length;       // 도시 갯수
        long left = 0;
        long right = MAX_TIME;

        while(left <= right) {
            long mid = (left + right) / 2;
            int gold = 0;
            int silver = 0;
            int add = 0;

            for(int i=0; i<n; i++) {
                int curGold = g[i];
                int curSilver = s[i];
                int curWeight = w[i];
                long curTime = t[i];

                long moveCount = mid / (curTime * 2);
                if(mid % (curTime * 2) >= t[i]) {
                    moveCount++;
                }

                gold += Math.min(curGold, moveCount * curWeight);
                silver += Math.min(curSilver, moveCount * curWeight);
                add += Math.min(curGold + curSilver, moveCount * curWeight);
            }

            if(a <= gold && b <= silver && a + b <= add) {
                right = mid - 1;        // 값을 줄이기
                answer = Math.min(mid, answer);
            } else {
                left = mid + 1;     // 값을 키우기
            }
        }
        return answer;
    }
}