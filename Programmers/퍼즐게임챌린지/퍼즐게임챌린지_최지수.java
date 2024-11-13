import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int start = 1;
        int end = -1;
        for (int i = 0; i < times.length; i++) {
            limit -= times[i];
            end = Math.max(end, diffs[i]);
        }
        
        int p = (start + end) / 2;
        while (start <= end) {
            p = (start + end) / 2;
            long spend = 0;
            for (int i = 1; i < diffs.length; i++) {
                if (diffs[i] > p) {
                    spend += (diffs[i] - p) * (times[i-1] + times[i]);
                }
            }
            
            if (spend < limit) {end = p-1;}
            else if (spend == limit) {
                answer = p;
                break;
            } else {start = p+1;}
        }
        if (answer == 0) answer = start;
        
        return answer;
    }
}