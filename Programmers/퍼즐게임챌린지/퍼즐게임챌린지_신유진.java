import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;

        int start = 1;
        int end = 0;
        for(int i = 0; i < diffs.length; i++) {
            end = Math.max(end, diffs[i]);
        }

        while(start <= end) {
            int mid = (start + end) / 2;

            long sum = times[0];
            for(int i = 1; i < diffs.length; i++) {
                if (diffs[i] <= mid) {
                    sum += times[i];
                } else {
                    sum += ((diffs[i] - mid) * (times[i] + times[i-1]) + times[i]);
                }
            }

            if (sum > limit) {
                start = mid + 1;
            } else {
                answer = mid;
                end = mid - 1;
            }
        }

        return answer;
    }
}