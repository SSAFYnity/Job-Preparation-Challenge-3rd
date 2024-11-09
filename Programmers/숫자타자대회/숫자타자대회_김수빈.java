import java.util.*;

public class 숫자타자대회_김수빈 {

    static class Solution {
        private static final int INF = Integer.MAX_VALUE;
        private static final int[][] keyboardPositions = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};  // 키보드 위치

        private static int bfs(int[] startPos, int[] endPos) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{startPos[0], startPos[1], 0});  // 시작 위치 및 초기 비용
            boolean[][] visited = new boolean[4][3];
            visited[startPos[0]][startPos[1]] = true;

            int[] dr = {0, 0, -1, 1, -1, 1, -1, 1};
            int[] dc = {-1, 1, 0, 0, -1, -1, 1, 1};
            int[] weightArr = {2, 2, 2, 2, 3, 3, 3, 3};

            while(!queue.isEmpty()) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], cost = curr[2];
                // 목표 위치 도달 시 최소 가중치 변환
                if (r == endPos[0] && c == endPos[1]) {
                    return cost;
                }
                // 상하좌우 및 대각선 탐색
                for (int d = 0; d < 8; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    int nextCost = cost + weightArr[d];
                    // 범위 안이고 방문 안 했을 경우에만 방문
                    if (nr >= 0 && nr < 4 && nc >= 0 && nc < 3 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc, nextCost});
                    }
                }

            }
            return INF;
        }

        private static int calculateCost(int[] pos1, int[] pos2) {
            int dr = Math.abs(pos1[0] - pos2[0]);
            int dc = Math.abs(pos1[1] - pos2[1]);
            // 제자리
            if (dr == 0 && dc == 0) {
                return 1;
            }
            // 상하좌우
            if (dr + dc == 1) {
                return 2;
            }
            // 대각선
            if (dr == 1 && dc == 1) {
                return 3;
            }
            // 나머지
            return bfs(pos1, pos2);
        }


        public int solution(String numbers) {
            // 초기 세팅
            int len = numbers.length();
            // len + 1인 이유는 끝까지 계산해서 집어넣기 위해서 (초기 4, 6 위치에 있는 것을 0번째 인덱스에 집어넣음)
            int[][][] dp = new int[len + 1][10][10];  // idx, 왼손, 오른손
            for (int[][] dpLayer : dp) {
                for (int[] dpRow : dpLayer) {
                    Arrays.fill(dpRow, INF);
                }
            }

            dp[0][4][6] = 0;  // 시작 초기 비용 (왼손 4, 오른손 6)

            for (int i = 0; i < len; i++) {
                int nextNum = numbers.charAt(i) - '0';
                for (int left = 0; left < 10; left++) {
                    for (int right = 0; right < 10; right++) {
                        if (dp[i][left][right] == INF) {
                            continue;
                        }
                        // 두 손 중복 방지용
                        if (nextNum != right) {
                            int leftCost = calculateCost(keyboardPositions[left], keyboardPositions[nextNum]);
                            dp[i + 1][nextNum][right] = Math.min(dp[i + 1][nextNum][right], dp[i][left][right] + leftCost);
                        }
                        if (nextNum != left) {
                            int rightCost = calculateCost(keyboardPositions[right], keyboardPositions[nextNum]);
                            dp[i + 1][left][nextNum] = Math.min(dp[i + 1][left][nextNum], dp[i][left][right] + rightCost);
                        }

                    }
                }
            }
            int answer = INF;
            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    answer = Math.min(answer, dp[len][left][right]);
                }
            }


            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solutionClass = new Solution();
        String numbers = "5123";
        int result = solutionClass.solution(numbers);
        System.out.println(result);
    }
}
