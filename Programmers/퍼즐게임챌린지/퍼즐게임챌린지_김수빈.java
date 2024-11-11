public class 퍼즐게임챌린지_김수빈 {
    static class Solution {
        private boolean canSolvePuzzles(int[] diffs, int[] times, long limit, int level, int puzzleNum) {
            int[] wrongCounts = new int[puzzleNum]; // 틀릴 개수 배열
            for (int i = 0; i < puzzleNum; i++) {
                wrongCounts[i] = diffs[i] - level >= 0 ? diffs[i] - level : 0;
            }
            long usedTime = times[0] * (wrongCounts[0] + 1);  // 쓴 시간 판단
            for (int i = 1; i < puzzleNum; i++) {
                usedTime += (times[i] + times[i-1]) * (wrongCounts[i]) + times[i];
            }
            boolean result = usedTime > limit ? false : true;
            return result;
        }

        public int solution(int[] diffs, int[] times, long limit) {
            int puzzleNum = diffs.length;  // 퍼즐 개수
            int left = 1;
            int right = 100000;
            int result = right;
            // 일반 탐색은 너무 많이 걸리니 이진 탐색을 전환
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canSolvePuzzles(diffs, times, limit, mid, puzzleNum)) {
                    result = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] diffs = {1, 99999, 100000, 99995};
        int[] times = {9999, 9001, 9999, 9001};
        long limit = 3456789012L;
        int result = sol.solution(diffs, times, limit);
        System.out.println(result);
    }

}
