import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Solution {
    static Map<String, Integer> map;
    static List<Character> keyLst;
    static List<String> combi;
    static StringBuilder sb = new StringBuilder();
    static int maxOrder;
    
    /*
        n개의 길이를 가지는 조합 생성해서 HashMap에 카운팅 
    */
    public void combination(int depth, int n, int start, String sortedOrder, String tmp) {
        if(depth == n) {
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            maxOrder = Math.max(maxOrder, map.get(tmp));
            return;
        }
        for(int i=start; i<sortedOrder.length(); i++) {
            combination(depth + 1, n, i + 1, sortedOrder, tmp + sortedOrder.charAt(i));
        }
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> answerLst = new ArrayList();
        
        for(int cnt : course) {
            map = new HashMap();
            maxOrder = 0;
            for(String order : orders) {
                // 주문한 메뉴를 오름차순 정렬
                char[] chArray = order.toCharArray();
                Arrays.sort(chArray);
                String sortedOrder = new String(chArray);
                combination(0, cnt, 0, sortedOrder, "");
            }
            for(String key : map.keySet()) {
                if(map.get(key) >= 2 && maxOrder == map.get(key)) {
                    answerLst.add(key);
                }
            }
        }
        
        Collections.sort(answerLst);
        answer = new String[answerLst.size()];
        for(int i=0; i<answerLst.size(); i++) {
            answer[i] = answerLst.get(i);
        }   
        return answer;
    }
}