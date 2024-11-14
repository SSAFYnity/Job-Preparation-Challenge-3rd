import java.util.*;

class Solution {
    int [] a;
    int maxLength = -1;
    
    public int solution(int[] a) {
        this.a = a;
        int n = a.length;
        if(a.length % 2  == 1) n -= 1;
        
        // 조합 만들기
        for(int i = n; i > 0; i = i-2){
            // 짝수개의 조합 만들기(최대 길이부터)
            makeCombination(i, new int[n], 0, 0);
            
            if(maxLength != -1) break;
        }
        
        if(maxLength == -1) maxLength = 0;
        
        return maxLength;
    }
    
    void makeCombination(int depth, int [] combi, int start, int idx){
        if(depth == idx){
            // 조합 생성 -> 스타배열 확인하기
            int [] res = new int[depth];
            for(int i = 0; i < combi.length; i++){
                res[i] = a[combi[i]];
            }
            checkSequence(res);
            return;
        }
        
        for(int i = start; i < a.length; i++){
            combi[idx] = a[i];
            makeCombination(depth, combi, start+1, idx+1);
            if(maxLength != -1) break;
        }
        
    }
    void checkSequence(int [] comb){
        // 교집합 원소 개수 구하기
        boolean flag = true;
        
        Stack<int []> stack = new Stack<>();
        for(int i = 0; i < comb.length; i=i+2){
            if(stack.isEmpty()){
                stack.add(new int[]{comb[i], comb[i+1]});
            }else{
                int [] point = stack.peek();
                // 교집합 조건 확인
                if(point[0] == comb[i] || point[0] == comb[i+1] || 
                  point[1] == comb[i] || point[1] == comb[i+1]){
                    //앞뒤 확인
                    if(comb[i] == comb[i+1]){
                        flag = false;
                        break;
                    }
                    
                }else{
                    flag = false;
                    break;
                }
            }
        }
        if(flag) maxLength = comb.length;
    }
}