import java.io.*;
class Solution {
    static int[] drr; // 난이도
    static int[] trr; // 시간

    public int solution(int[] diffs, int[] times, long limit) {
        drr=diffs.clone();
        trr=times.clone();

        int mx = 0;
        int mn = 1;
        for(int a : diffs){
            mx = Math.max(mx,a);
        }
        
        int mid;
        while(mn<mx){
            mid = (mn + mx) / 2;
            long t = getTime(mid);
            if(limit == t) return mid;
            else if (limit >t){
                mx = mid;
            }
            else if (limit <t){
                if(mn==mid){
                    mn+=1;
                }else{
                    mn = mid;
                }
            }
        }
        return mx;
    }
    
    //숙련도에 해당하는 총 시간 구하기
    static long getTime(int l){
        long res =0;
        for(int i=0;i<drr.length;i++){
            if(drr[i]<=l){
                res+=trr[i];
            } else{
                res+=((drr[i]-l)*(trr[i]+trr[i-1]))+trr[i];
            }
        }
        return res;
    }
}