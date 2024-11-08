
import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static int[] idxArr;
    static boolean find = false;
    
    static int drop() {
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(1);
        while (!que.isEmpty()) {
            int now = que.poll();
            if (tree[now].size() == 0) {return now;}
            que.add(tree[now].get(idxArr[now]));
            idxArr[now] = (idxArr[now]+1)%tree[now].size();
        }
        return -1;
    }
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        int len = edges.length+2;
        tree = new ArrayList[len];
        idxArr = new int[len];
        
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
        }
        for (List<Integer> t : tree) {
            Collections.sort(t);
        }
        
        int[] dcArr = new int[len];
        List<Integer>[] list = new ArrayList[len];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        int idx = 0;
        while (!find) {
            int leaf = drop();
            dcArr[leaf]++;
            list[leaf].add(idx++);
            
            if (dcArr[leaf] > target[leaf-1]) {break;}
            if (dcArr[leaf] * 3 < target[leaf-1]) {continue;}
            
            boolean capa = true;
            for (int i = 1; i < dcArr.length; i++) {
                if (dcArr[i] * 3 < target[i-1]) {capa = false; break;}
            }
            
            if (capa) {
                answer = new int[idx];
                Arrays.fill(answer, 1);
                for (int i = 1; i < dcArr.length; i++) {
                    if (dcArr[i] == 0) {continue;}
                    target[i-1] -= dcArr[i];
                    for (int j = list[i].size()-1; j >= 0 && target[i-1] > 0; j--) {
                        target[i-1]--;
                        answer[list[i].get(j)]++;
                        if (target[i-1] > 0) {
                            target[i-1]--;
                            answer[list[i].get(j)]++;
                        }
                    }
                }
                return answer;
            }
        }
        return new int[] {-1};
    }
}