class Solution {
    static int cardsLen;

    public int make(boolean[] visit, int start, int[] cards) {
        int cnt = 1;
        visit[start] = true;
        int nextIdx = cards[start];
        while(true) {
            if(visit[nextIdx - 1]) {        // 이미 열린 상자라면
                break;
            }
            visit[nextIdx - 1] = true;
            nextIdx = cards[nextIdx - 1];
            cnt++;
        }
        return cnt;
    }

    public int solution(int[] cards) {
        int answer = 0;
        cardsLen = cards.length;
        boolean[] visit;
        int group1;
        int group2;

        // 첫 번째 그룹 생성
        for(int start=0; start<cardsLen; start++) {
            visit = new boolean[cardsLen];
            group1 = make(visit, start, cards);

            if(group1 == cardsLen) {
                continue;
            }

            // 두 번째 그룹 생성
            boolean[] tmp = visit;
            for(int i=0; i<cardsLen; i++) {
                group2 = make(tmp, i, cards);
                answer = Math.max(answer, group1 * group2);
            }
        }

        return answer;
    }
}