import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startx, int starty, int[][] balls) {
        int[] answer = new int[balls.length];
        int inf = Integer.MAX_VALUE;
        Arrays.fill(answer, inf);
        
        for (int i = 0; i < balls.length; i++) {
            int nowx = balls[i][0];
            int nowy = balls[i][1];
            
            int[] part = new int[4];
            int ab = (int) Math.pow(Math.abs(startx-nowx), 2);
            int cd = (int) Math.pow(Math.abs(starty-nowy), 2);
            
            part[0] = (int) Math.pow(nowy+starty, 2) + ab;
            part[1] = (int) Math.pow((n-nowy)+(n-starty), 2) + ab;
            part[2] = (int) Math.pow(nowx+startx, 2) + cd;
            part[3] = (int) Math.pow((m-nowx)+(m-startx), 2) + cd;
            
            if (nowy == starty) {
                if (nowx < startx) part[2] = inf;
                else part[3] = inf;
            }
            if (nowx == startx) {
                if (nowy < starty) part[0] = inf;
                else part[1] = inf;
            }
            
            for (int j = 0; j < 4; j++) {
                answer[i] = Math.min(answer[i], part[j]);
            }
        }
        return answer;
    }
}