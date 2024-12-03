import java.util.*;
// 아이디어 참조 : https://school.programmers.co.kr/questions/58394
// dp 아이디어가 어려운데, 접근법을 잘 알려주셔서 너무 좋은 자료
class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] graph = new ArrayList[n+1];

        for (int index = 0; index < n+1; index++) {
            graph[index] = new ArrayList<>();
            graph[index].add(index);
        }

        for (int index = 0; index < m; index++) {
            int start = edge_list[index][0];
            int end = edge_list[index][1];
            graph[start].add(end);
            graph[end].add(start);
        }

        int[][] dp = new int[k][n+1];
        for (int index = 0; index < k; index++) {
            Arrays.fill(dp[index], 101);
        }

        dp[0][gps_log[0]] = 0;

        for (int time = 0; time < k-1; time++) {
            for (int current = 1; current < n+1; current++) {
                // 도달 불가
                if(dp[time][current] == 101) {
                    continue;
                }
                for (int next : graph[current]) {
                    if(gps_log[time+1] != next) {
                        dp[time+1][next] = Math.min(dp[time+1][next], dp[time][current] +1);
                    } else {
                        dp[time+1][next] = Math.min(dp[time+1][next], dp[time][current]);
                    }
                }
            }
        }

        return dp[k-1][gps_log[k-1]] == 101 ? -1 : dp[k-1][gps_log[k-1]];
    }
}