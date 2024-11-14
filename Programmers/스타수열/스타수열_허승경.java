import java.util.*;

class Solution {
    int [] a;
    int maxLength = -1;
    
    public int solution(int[] a) {
        this.a = a;
        int n = a.length;
        if(n < 2) return 0;
        if(a.length % 2  == 1) n -= 1;
        
        // 조합 만들기
        for(int i = n; i > 0; i = i-2){
            // 짝수개의 조합 만들기(최대 길이부터)
            makeCombination(i, new int[i], 0, 0);
            
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
            combi[idx] = i;     // 인덱스 저장
            makeCombination(depth, combi, i+1, idx+1);
            if(maxLength != -1) break;
        }
        
    }
    void checkSequence(int [] comb){
        boolean flag = true;
        int [] point = {-1, -1};
        
        for(int i = 0; i < comb.length-1; i+=2){
            int a = comb[i];
            int b = comb[i+1];
            
            if(a == b){ // 교집합끼리 같으면 안 됨
                flag = false;
                break;
            }
            
            if(point[0] == -1 && point[1] == -1){   // 첫번째 원소일 경우 셋팅
                point[0] = a;
                point[1] = b;
            }else{  // 교집합 체크
                if(point[0] != a && point[0] != b && 
                  point[1] != a && point[1] != b){
                    // 하나도 해당되는 게 없음
                    flag = false;
                    break;
                }
            }
        }
        if(flag) maxLength = comb.length;
    }
}