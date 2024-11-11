class Solution {
    static int n;

    public static int solution(int[] diffs, int[] times, long limit) {
        n = diffs.length;   // 퍼즐의 갯수
        binarySearch(diffs, times, limit);
        return (int)binarySearch(diffs, times, limit);      // 최소 숙련도
    }

    public static long play(int[] diffs, int[] times, long level) {
        long time = 0;
        int timePrev = times[0];       // 이전 퍼즐의 소요 시간

        for(int i=0; i<n; i++) {
            int diff = diffs[i];
            int timeCur = times[i];     // 현재 퍼즐의 소요 시간
            if(diff <= level) {
                time += timeCur;
            } else {
                // diff - level번 틀린다
                int fail = (int)(diff - level);
                int addTime = fail * (timeCur + timePrev);
                time += (timeCur + addTime);
            }
            timePrev = times[i];
        }
        return time;
    }

    public static long binarySearch(int[] diffs, int[] times, long limit) {
        long left = 1;
        long right = limit;

        while(left < right) {
            long mid = (left + right) / 2;      // 숙련도
            long time = play(diffs, times, mid);
            if(time > limit) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}