import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int left = 1;
        int right = 100000;
        int level;
        int size = diffs.length;
        while(left <= right) {
            long time = times[0];
            level = (left + right) / 2;
            for (int index = 1; index < size; index++) {
                time += calculateTime(level, diffs[index], times[index], times[index-1]);
                if (time > limit) {
                    left = level + 1;
                    break;
                }
            }
            if (time == limit) {
                return level;
            }
            if (time < limit) {
                answer = level;
                right = level - 1;
            }
        }
        return answer;
    }

    // 시간 계산 공식
    private long calculateTime(int level, int diff, int time_cur, int time_prev) {
        if (diff <= level) {
            return (long) time_cur;
        } else {
            long repeatCount = (long) (diff - level);
            return repeatCount*(time_cur + time_prev) + time_cur;

        }
    }
}