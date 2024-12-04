import java.util.*;

class Solution {
    
    List<Character> openList = new ArrayList<>(Arrays.asList('[', '(', '{'));
    List<Character> closeList = new ArrayList<>(Arrays.asList(']', ')', '}'));
    
    public int solution(String s) {
        
        int answer = 0;
        for(int i = 0; i < s.length(); i++){
            if(isCorrect(s.substring(i, s.length()) + s.substring(0, i)))
                answer++;
        }
        return answer;
    }
    
    public boolean isCorrect(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(openList.contains(c)){
                stack.push(c);
            } else {
                if(stack.isEmpty())
                    return false;
                
                if(stack.peek() == openList.get(closeList.indexOf(c)))
                    stack.pop();
                else
                    return false;
            }
        }
        
        if(stack.isEmpty())
            return true;
        
        return false;
    }
}