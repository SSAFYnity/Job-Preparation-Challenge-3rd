import java.util.*;

class Solution {
    static int[] num;
    static int len;
    static int[][][] dp;
    static int[][] pad = {{1,2,3}, {4,5,6}, {7,8,9}, {-1, 0, -1}};
    static int[][] dr = {{0,1,0,-1},{1,0,-1,0}};
    static int[][] cr = {{1,1,-1,-1}, {1,-1,1,-1}};
    static int[][] term = new int[10][10];
    static HashMap<Integer, int[]> map = new HashMap<>();
    static void checkXY() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                map.put(pad[i][j], new int[] {i, j});
            }
        }
    }
    static void countTerm() {
        boolean[][] visit = new boolean[4][3];
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < 10; i++) {
            for (int target = i; target < 10; target++) {
                if (i == target) {term[i][target] = 1; continue;}
                que.clear();
                visit = new boolean[4][3];
                que.add(new int[] {i, 0});
                visit[map.get(i)[0]][map.get(i)[1]] = true;
                
                while (!que.isEmpty()) {
                    int[] l = que.poll();
                    int[] wv = map.get(l[0]);
                    int c = l[1];
                    if (l[0] == target) {
                        term[i][target] = c;
                        term[target][i] = c;
                        break;
                    }
                    for (int d = 0; d < 4; d++) {
                        int y = wv[0] + dr[0][d];
                        int x = wv[1] + dr[1][d];
                        if (y < 0 || y >= 4 || x < 0 || x >= 3 || visit[y][x] || pad[y][x] == -1) {continue;}
                        que.add(new int[] {pad[y][x],c+2});
                        visit[y][x] = true;
                    }
                    for (int d = 0; d < 4; d++) {
                        int y = wv[0] + cr[0][d];
                        int x = wv[1] + cr[1][d];
                        if (y < 0 || y >= 4 || x < 0 || x >= 3 || visit[y][x] || pad[y][x] == -1) {continue;}
                        que.add(new int[] {pad[y][x],c+3});
                        visit[y][x] = true;
                    }
                }
            }
        }
    }
    static int back(int p, int left, int right) {
        if (p >= len) {return 0;}
        if (dp[p][left][right] != -1) {return dp[p][left][right];}
        
        int target = num[p];
        int answer = Integer.MAX_VALUE;
        
        if (left != target) {answer = Math.min(back(p+1, left, target) + term[right][target], answer);}
        if (right != target) {answer = Math.min(back(p+1, target, right) + term[left][target], answer);}
        
        return dp[p][left][right] = answer;
    }
    public int solution(String numbers) {
        len = numbers.length();
        num = new int[len];
        dp = new int[len][10][10];
        
        for (int i = 0; i < len; i++) {
            num[i] = numbers.charAt(i) - '0';
            for (int j = 0; j < 10; j ++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        checkXY();
        countTerm();
        
        return back(0, 4, 6);
    }
}