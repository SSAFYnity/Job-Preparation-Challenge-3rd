import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    /*
        2 <= 거점 개수 n <= 200
        1 <= 도로의 개수 m <= 10,000
        2 <= 택시가 시간대별로 보내오는 거점 정보의 총 개수 k <= 100
        머물렀던 거점의 정보 gps_log
    */
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Set<Integer>> graph = new ArrayList();     // 양방향 연결 정보를 담을 그래프
        int[][] dp = new int[k][n + 1];     // 거점 n을 1부터 시작할거라서 +1함

        // 초기화
        for(int i=0; i<=n; i++) {
            graph.add(new HashSet());
            graph.get(i).add(i);        // 자기 자신을 추가
        }

        // 양방향 연결하기
        for(int[] edge : edge_list) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // DP 초기화
        for(int i=0; i<k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][gps_log[0]] = 1;      // 시작점 초기화

        for(int i=0; i<k-1; i++) {    // 순서
            for(int j=1; j<=n; j++) {        // 거점
                if(dp[i][j] == Integer.MAX_VALUE) {     // 불가능한 경로
                    continue;
                }

                for(int v : graph.get(j)) {     // 연결된 정점 탐색
                    int updateCnt = dp[i][j];   // 경로 수정 횟수

                    if(gps_log[i + 1] != v) {       // 다음으로 방문할 정점이 v가 아니면
                        updateCnt++;        // 경로 수정 횟수 증가
                    }

                    dp[i + 1][v] = Math.min(dp[i + 1][v], updateCnt);       // 연결된 정점의 경로 수정 횟수 갱신
                }
            }
        }

        return dp[k - 1][gps_log[k - 1]] == Integer.MAX_VALUE ? -1 : dp[k - 1][gps_log[k - 1]] - 1;      // 최소의 오류 수정 횟수
    }
}