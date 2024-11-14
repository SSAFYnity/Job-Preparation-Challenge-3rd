import java.util.*;

class Solution {
    public int solution(int[] a) {
        if(a.length < 2) return 0;
        
        // 배열 속 숫자의 빈도 -> map에 저장
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : a){
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        
        int maxLength = 0;  // 최대 길이
        for(int key : map.keySet()){
            if (map.get(key) * 2 <= maxLength) continue;
            
            int pairs = 0;
            
            for(int i = 0; i < a.length-1; i++){
                if((a[i] == key || a[i+1] == key) && a[i] != a[i+1]){
                    pairs++;
                    i++;
                }
            }
            maxLength = Math.max(maxLength, pairs*2);
        }
        
        return maxLength;
    }
}