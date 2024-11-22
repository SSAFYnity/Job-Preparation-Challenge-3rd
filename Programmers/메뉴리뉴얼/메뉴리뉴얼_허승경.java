import java.util.*;

class Solution {
    HashMap<String, Integer> map = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> result = new ArrayList<>();   // 결과 리스트
        
        // 1. 코스 개수 만큼 반복
        for(int cour : course){
            map = new HashMap<>();  // 코스 수행할 때마다 초기화
            
            // 2. 코스 수만큼 현재 주문에서 조합 만들기
            for(String order : orders){
                char [] str =order.toCharArray();
                Arrays.sort(str);   // 오름 차순 정렬
                combination(str, cour, 0, "");
            }
            
            // 3. 만들어진 조합에서 최빈값 구하기
            int maxCount = 0;
            for(int value : map.values()){
                maxCount = Math.max(maxCount, value);
            }
            
            //  최빈값인 문자열을 result에 추가하기
            if(maxCount < 2) continue;  // 버리기
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                if(entry.getValue() == maxCount){
                    result.add(entry.getKey());
                }
            }
        }
        
        // 리스트 -> 배열로
        String[] answer = new String[result.size()];
        Collections.sort(result);   // 정렬
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    void combination(char [] str, int depth, int count, String menu){
        if(depth == menu.length()){
            map.put(menu, map.getOrDefault(menu, 0)+1);
            return;
        }
        
        for(int i = count; i < str.length; i++){
            combination(str, depth, i+1, menu+str[i]);      // 세트 메뉴 조합 생성
        }
    }
}