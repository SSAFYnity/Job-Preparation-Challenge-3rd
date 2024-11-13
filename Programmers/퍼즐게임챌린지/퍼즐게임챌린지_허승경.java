import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int n = diffs.length;
        int start = 1;
        int end = Arrays.stream(diffs).max().getAsInt();
        
        while(start <= end ){
            int level = (start+end)/2;      // 레벨
            long total_time = 0;
            
            // 시간 구하기
            for(int i = 0; i < n; i++){
                if(diffs[i] <= level){   // pre_cur로 해결
                    total_time += times[i];
                }else{
                    // (난이도-등급)*이전시간 + 현재시간
                    total_time += (diffs[i]-level)*(times[i-1]+times[i]) + times[i];
                }
            }
            
            if(total_time <= limit){
                answer = level;
                end = level-1;
            }else{
                start = level+1;
            }
        }
        
        return answer;
    }
}