import java.util.*;
class Solution {
    private boolean[][][] visited = new boolean[17][17][17];
    private int maxSheep = 0;
    private List<Integer>[] graph;
    private int[] nodes;
    public int solution(int[] info, int[][] edges) {
        nodes = info;
        int size = info.length;
        graph = new ArrayList[size];
        for (int index = 0; index < size; index++) {
            graph[index] = new ArrayList<>();
        }
        int edgeLength = edges.length;
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph[parent].add(child);
            graph[child].add(parent);
        }
        dfs(0, 0, 0);
        return maxSheep;
    }

    private void dfs(int index, int sheep, int wolf) {
        if(visited[index][sheep][wolf]) {
            return;
        }
        visited[index][sheep][wolf] = true;
        int backupSheep = sheep;
        int backupWolf = wolf;
        int backupNode = nodes[index];
        if (nodes[index] == 1) {
            wolf++;
        } else if(nodes[index] == 0) {
            sheep++;
        }
        nodes[index] = -1;
        if(wolf < sheep) {
            maxSheep = Math.max(maxSheep, sheep);
            for (int next: graph[index]) {
                dfs(next, sheep, wolf);
            }
        }
        nodes[index] = backupNode;
        visited[index][backupSheep][backupWolf] = false;
    }
}