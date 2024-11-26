import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        int s1 = scores[0][0];
        int s2 = scores[0][1];
        int wanho_score = s1 + s2;
        int tmp = 0;

        Arrays.sort(scores, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])
                    return 1;
                else if (o1[0] == o2[0]) {
                    if (o1[1] > o2[1])
                        return 1;
                    else
                        return -1;
                }
                else return -1;
            }
        });

        for(int i = 0; i < scores.length; i++){
            if (s1 < scores[i][0] && s2 < scores[i][1]){
                return -1;
            }
            if (tmp <= scores[i][1]){
                if (wanho_score < scores[i][0] + scores[i][1]){
                    answer += 1;
                }
                tmp = scores[i][1];
            }
        }
        return answer;
    }
}