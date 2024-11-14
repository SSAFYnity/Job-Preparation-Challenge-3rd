class Solution {
    static int answer = 1;
    static int[] infoArr;
    static int[][] edgesArr;

    public void dfs(int sheep, int wolf, int cur, boolean[] visit) {
        visit[cur] = true;

        if(infoArr[cur] == 0) {     // 양이 있으면
            sheep++;
            answer = Math.max(answer, sheep);
        } else {
            wolf++;
        }

        if(sheep <= wolf) {     // 늑대가 더 많다면
            return;
        }

        for(int[] edge : edgesArr) {
            int parent = edge[0];
            int child = edge[1];        // 자식 노드 번호

            if(visit[parent] && !visit[child]) {       // 부모는 방문했고, 방문하지 않은 자식이라면
                boolean[] newVisit = new boolean[visit.length];
                for(int j=0; j<visit.length; j++) {
                    newVisit[j] = visit[j];
                }
                dfs(sheep, wolf, child, newVisit);
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        int n = info.length;    // 노드의 갯수
        infoArr = info;
        edgesArr = edges;

        dfs(0, 0, 0, new boolean[n]);

        return answer;
    }
}