import java.util.*;

class Solution {
    static int inf = Integer.MAX_VALUE - 200;
    static int[][] dp;
    static Set<Integer>[] graph;
    
    int track(int[] gps, int num, int t) {
        if (t == gps.length-1) {
            if (num != gps[gps.length-1]) {return dp[num][t] = inf;}
            return 0;
        }
        
        if (dp[num][t] != -1) {return dp[num][t];}
        
        int result = inf;
        for (int i : graph[num]) {
            result = Math.min(result, track(gps, i, t+1) + (gps[t+1]==i ? 0 : 1));
        }
        
        return dp[num][t] = result;
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        dp = new int[n+1][gps_log.length];
        graph = new HashSet[n+1];
        for (int i = 0; i < n+1; i++) {
            graph[i] = new HashSet<>();
            graph[i].add(i);
            Arrays.fill(dp[i], -1);
        }
        for (int[] arr : edge_list) {
            graph[arr[0]].add(arr[1]);
            graph[arr[1]].add(arr[0]);
        }
        
        int answer = track(gps_log, gps_log[0], 0);
        
        return answer == inf ? -1 : answer;
    }
}