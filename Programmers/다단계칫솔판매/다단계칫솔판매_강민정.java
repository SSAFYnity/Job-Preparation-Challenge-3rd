import java.util.Map;
import java.util.HashMap;

class Solution {
    static String[] referralArr;
    static String[] enrollArr;
    static Map<String, Integer> hashMap;
    static int[] answer;

    /*
        change: 거스름돈
        cur: 현재 노드 번호
    */
    public void dfs(int change, int cur) {
        if(change < 1) {
            return;
        } else if(cur == -1) {     // 루트 노드에 왔다면
            return;
        }
        answer[cur] += Math.ceil(change * 0.9);
        dfs(change / 10, hashMap.get(referralArr[cur]));
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int sellerLen = seller.length;
        int enrollLen = enroll.length;
        hashMap = new HashMap();
        answer = new int[enrollLen];
        referralArr = referral;
        enrollArr = enroll;

        for(int i=0; i<enrollLen; i++) {
            hashMap.put(enroll[i], i);
        }
        hashMap.put("-", -1);

        for(int i=0; i<sellerLen; i++) {
            String sellerName = seller[i];        // 판매자 이름
            int sellAmount = amount[i];     // 판매 수량
            dfs(sellAmount * 100, hashMap.get(sellerName));
        }
        return answer;
    }
}