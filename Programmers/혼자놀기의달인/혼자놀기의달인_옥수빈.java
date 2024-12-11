import java.util.*;

class Solution {
    
    int[] parents;
    
    public void union(int a, int b, int[] cards){
        if(a == b)
            return;
        parents[b] = a;
        union(a, cards[b-1], cards);
    }
    
    public int solution(int[] cards) {        
        int len = cards.length;
        parents = new int[len + 1];
        
        for(int i = 1; i <= len; i++){
            parents[i] = i;
        }
                
        for(int i = 1; i <= len; i++){
            if(i == parents[i])
                union(i, cards[i-1], cards);
        }
                
        int[] countChild = new int[len + 1];
        for(int i = 1; i <= len; i++){
            countChild[parents[i]]++;
        }
        Arrays.sort(countChild);
                
        return countChild[len] * countChild[len-1];
    }
}