class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;
        long right = (long) Math.pow(10, 14) * 4;
        long left = 0L;
        while(left <= right) {
            long mid = (left + right) / 2;
            if(check(a, b, g, s, w, t, mid)) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private boolean check(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        int size = t.length;
        long totalSum = 0L;
        long totalA = 0L;
        long totalB = 0L;
        for (int index = 0; index < size; index++) {
            long count = time / (2 * t[index]);
            if (time % (2L * t[index]) >= t[index]) {
                count++;
            }
            long sum = count * w[index]; // 가져갈 수 있는 총 무게
            totalSum += Math.min(sum, g[index] + s[index]);
            totalA += Math.min(sum, g[index]);
            totalB += Math.min(sum, s[index]);
        }
        if(totalSum < a+b || totalA < a || totalB < b) {
            return false;

        }
        return true;
    }
}