import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int N = diffs.length;
        int left = 1;
        int right = 300_000;

        while (left <= right) {
            int level = (left + right) / 2;
            long time = solvePuzzles(diffs, times, level);

            if (time <= limit) {
                answer = level;
                right = level - 1;
            } else {
                left = level + 1;
            }
        }

        return answer;
    }

    static long solvePuzzles(int[] diffs, int[] times, int level) {
        long res = times[0];

        for (int i = 1; i < diffs.length; i++) {
            int diff = diffs[i];
            int cur = times[i];
            int prev = times[i - 1];

            if (diff <= level) {
                res += cur;
            } else {
                res += (diff - level) * (cur + prev) + cur;
            }
        }

        return res;
    }
}