import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int[] answer;
        int leafCnt = 0;        // 리프 노드의 개수
        int[] movedArr = new int[edges.length + 1];     // 지나간 횟수를 세는 배열
        int[] cntArr = new int[edges.length + 1];       // 현재 노드가 가지고 있는 숫자 개수를 세는 배열
        boolean[] visited = new boolean[edges.length + 1];      // 방문한 노드인지 저장하는 배열
        List<Integer>[] tree = new ArrayList[edges.length + 1];    // target의 길이 = edges의 길이 + 1
        List<Integer> leafNodeLst = new ArrayList();        // 리프 노드를 저장하는 리스트
        List<Integer> answerLst = new ArrayList();

        for(int i=0; i<edges.length + 1; i++) {
            tree[i] = new ArrayList();          // 초기화
        }
        for (int i=0; i<edges.length; i++) {
            int parent = edges[i][0] - 1;       // 부모 노드
            int child = edges[i][1] - 1;        // 자식 노드
            tree[parent].add(child);
        }
        for (int i=0; i<edges.length + 1; i++) {
            Collections.sort(tree[i]);          // 부모 노드 i의 자식 노드들을 오름차순 정렬
        }

        // 숫자를 떨어뜨려야 하는 리프 노드의 개수 세기
        for(int i=0; i<edges.length + 1; i++) {
            if(tree[i].isEmpty() && target[i] > 0) {
                leafCnt++;
            }
        }

        while(leafCnt > 0) {    // 리프 노드가 남아있을 동안에
            int node = 0;       // 루트 노드 할당
            while(tree[node].size() > 0) {
                int nextNodeIdx = movedArr[node]++ % tree[node].size();       // 그 다음으로 큰 노드의 인덱스
                node = tree[node].get(nextNodeIdx);
            }
            cntArr[node]++;       // 리프 노드 node에 떨어진 숫자의 갯수 증가
            leafNodeLst.add(node);       // 리프 노드 node 저장
            if(cntArr[node] > target[node]) {       // 목표로 하는 갯수보다 더 많이지면 안 된다
                return new int[]{-1};
            }
            if(!visited[node] && target[node] <= 3 * cntArr[node]) {
                visited[node] = true;
                leafCnt--;
            }
        }

        for(int num : leafNodeLst) {
            cntArr[num]--;      // 노드 num이 가지고 있는 숫자 갯수 감소
            for(int val=1; val<=3; val++) {
                if(cntArr[num] <= target[num] - val && target[num] - val <= 3 * cntArr[num]) {
                    answerLst.add(val);
                    target[num] -= val;
                    break;
                }
            }
        }

        answer = new int[answerLst.size()];
        for(int i=0; i<answerLst.size(); i++) {
            answer[i] = answerLst.get(i);
        }

        return answer;
    }
}