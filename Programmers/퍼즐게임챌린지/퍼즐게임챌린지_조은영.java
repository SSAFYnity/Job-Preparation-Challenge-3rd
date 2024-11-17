class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        long answer = 0;
        int start=1;
        int end=100000;
        
        // 이진탐색
        while(start<end){
            int mid=(start+end)/2;
            long sum=0;
            
            for(int i=0; i<diffs.length; i++){
                if(mid>=diffs[i]) sum+=times[i];
                else{
                    sum+=((diffs[i]-mid)*(times[i]+times[i-1])+times[i]);
                }
                
                if(sum>limit) break;
            }
            
            if(limit<sum){
                start=mid+1;
            }else{
                end=mid;
            }
            answer=end;
        }
        
        return answer;
    }
}