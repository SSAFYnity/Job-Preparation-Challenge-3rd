import java.util.ArrayList;
import java.util.List;

class Solution {
    int maxSheep = 0;
    List<Integer>[] graph;
    int[] info;
    
    public int solution(int[] infoInput, int[][] edges) {
        info = infoInput;
        graph = new ArrayList[info.length];

        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        List<Integer> path = new ArrayList<>();
        path.add(0); // 루트 노드부터 시작
        dfs(0, 0, 0, path); // 초기 상태로 DFS 시작

        return maxSheep;
    }
    
    public void dfs(int node, int sheep, int wolf, List<Integer> path) {
        if (info[node] == 0)
            sheep++; // 현재 노드가 양일 경우 양 증가
        else
            wolf++; // 현재 노드가 늑대일 경우 늑대 증가


        if (wolf >= sheep) // 늑대가 양 이상이 되면 종료
            return;
        maxSheep = Math.max(maxSheep, sheep); // 최대 양의 수 업데이트

        List<Integer> nextNodes = new ArrayList<>(path);
        nextNodes.remove(Integer.valueOf(node));
        nextNodes.addAll(graph[node]); // 다음 방문 가능한 노드 추가

        for (int next : nextNodes) {
            dfs(next, sheep, wolf, nextNodes); // 재귀적으로 DFS 호출
        }
    }
}