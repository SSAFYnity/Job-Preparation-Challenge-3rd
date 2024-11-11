import java.util.*;

class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        
        long start = 1;
        long end = Arrays.stream(diffs).max().getAsInt();
        long answer = 0;
        
        while (start<=end) {
            long mid = (start+end)/2;
            long time = 0;
            for (int i = 0; i<diffs.length; i++) {
                if (diffs[i]<=mid) {
                    time += times[i];
                } else {
                    time += (diffs[i]-mid)*(times[i]+times[i-1]) + times[i];

                }
            }
            if (time>limit) {
                start = mid+1;
            } else {
                end = mid-1;
                answer = mid;
            }
        }
        return answer;
    }
}