
import java.util.*;
class Solution {
    
    static class Xy{
        int x;
        int y;
        public Xy(int x, int y) {
			this.x = x;
			this.y = y;
		}
    }
    static class Node{
        int r; //오른손
        int l; // 왼손
        int w; // 가중치
        int tidx; // 다음 목표 인덱스
        
        public Node(int r, int l, int w, int tidx) {
			this.r = r;
			this.l = l;
			this.w = w;
			this.tidx = tidx;
		}
    }
    
    // 숫자 좌판
    static Xy[] map={new Xy(3,1),new Xy(0,0),new Xy(0,1),new Xy(0,2),
                      new Xy(1,0),new Xy(1,1),new Xy(1,2),
                      new Xy(2,0),new Xy(2,1),new Xy(2,2)};
    
    
    static int[] num ;
    public int solution(String numbers) {
        
        num = numbers.chars().map(Character::getNumericValue).toArray();
        
        Queue<Node> qu = new LinkedList<>();
        qu.offer(new Node(4,6,0,0));
        int ans = Integer.MAX_VALUE;
        while(!qu.isEmpty()){
            Node n = qu.poll();    
            if(n.w>ans){
                continue;
            }
            if(n.tidx==numbers.length()){
                ans = Math.min(ans, n.w);
                continue;
            }
            //오른손 또는 왼손이 해낭 숫자위에 이미 있을때
            if(n.r==num[n.tidx]||n.l==num[n.tidx]){
                qu.offer(new Node(n.r, n.l, n.w+1,n.tidx+1));
                continue;
            }
            
            //오른손이동
            qu.offer(new Node(num[n.tidx], n.l, n.w+findw(n.r,num[n.tidx]),n.tidx+1));
            
            //왼손이동
            qu.offer(new Node(n.r,num[n.tidx], n.w+findw(n.l,num[n.tidx]),n.tidx+1));
        }
        
        return ans;
    }
    
    
    //숫자 좌판간 가중치 구하기
    static int[][] wrr= new int [10][10];//가중치 저장 배열
    static int findw(int s, int e){
        if(wrr[s][e]!=0)return wrr[s][e];
        
        if (s==e){
            wrr[s][e] =1;
            return 1;
        }
        int x = map[s].x- map[e].x;
        int y = map[s].y- map[e].y;
        
        if(x<0) x*=(-1);
        if(y<0) y*=(-1);
        int mn = Math.min(x,y);
        int mx = Math.max(x,y);
        int res = mn*3+(mx-mn)*2;
        wrr[s][e] = res;
        wrr[e][s] = res;
        return res;
    }
}