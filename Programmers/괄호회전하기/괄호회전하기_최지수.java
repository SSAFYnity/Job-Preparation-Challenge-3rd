import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (map.containsValue(c)) stack.push(c);
                else if (!stack.isEmpty() && stack.peek() == map.get(c)) {
                    stack.poll();
                } else break;
                if (j == s.length()-1 && stack.isEmpty()) answer++;
            }
            s = s.substring(1, s.length()) + s.charAt(0);
            stack.clear();
        }
        
        return answer;
    }
}