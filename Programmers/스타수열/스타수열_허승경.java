import java.util.*;

class Solution {
    int [] a;
    int maxLength = -1;
    
    public int solution(int[] a) {
        this.a = a;
        int n = a.length;
        if(n < 2) return 0;
        if(a.length % 2  == 1) n -= 1;
        
        // 조합 만들기
        for(int i = n; i > 0; i = i-2){
            // 짝수개의 조합 만들기(최대 길이부터)
            makeCombination(i, new int[i], 0, 0);
            
            if(maxLength != -1) break;
        }
        
        if(maxLength == -1) maxLength = 0;
        
        return maxLength;
    }
    
    void makeCombination(int depth, int [] combi, int start, int idx){
        if(depth == idx){
            // 조합 생성 -> 스타배열 확인하기
            int [] res = new int[depth];
            for(int i = 0; i < combi.length; i++){
                res[i] = a[combi[i]];
            }
            checkSequence(res);
            return;
        }
        
        for(int i = start; i < a.length; i++){
            combi[idx] = i;     // 인덱스 저장
            makeCombination(depth, combi, i+1, idx+1);
            if(maxLength != -1) break;
        }
        
    }
    void checkSequence(int [] comb){
        Map<Integer, Integer> elementCount = new HashMap<>();

        for (int i = 0; i < comb.length - 1; i += 2) {
            int x = comb[i];
            int y = comb[i + 1];

            if (x == y) return; // 서로 다른 두 요소로 이루어지지 않으면 반환

            elementCount.put(x, elementCount.getOrDefault(x, 0) + 1);
            elementCount.put(y, elementCount.getOrDefault(y, 0) + 1);
        }

        // 공통된 요소가 하나 이상 존재하는지 확인
        for (int count : elementCount.values()) {
            if (count >= comb.length / 2) {
                maxLength = Math.max(maxLength, comb.length);
                return;
            }
        }
    }
}