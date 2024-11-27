import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int target = scores[0][0] + scores[0][1];

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int size = scores.length;
        int max = scores[0][1];

        for(int i = 0;i < size; i++){
            if(scores[i][1] < max){
                if(scores[i][0] == scores[0][0] && scores[i][1] == scores[0][1]) return -1;
                continue;
            }

            if(scores[i][0] + scores[i][1] > target) answer++;

            max = Math.max(max,scores[i][1]);
        }

        return answer;
    }
}