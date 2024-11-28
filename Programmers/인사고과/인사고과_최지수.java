import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wan = {scores[0][0], scores[0][1]};
        int wansum = wan[0] + wan[1];
        Arrays.sort(scores, new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0]) {
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });
        
        int maxi = -1;
        int rank = 1;
        for (int[] i : scores) {
            if (maxi > i[1]) {
                if (Arrays.equals(wan, i)) return -1;
                continue;
            }
            if (maxi < i[1]) maxi = i[1];
            if (i[0] + i[1] > wansum) rank++;
        }
        
        return rank;
    }
}