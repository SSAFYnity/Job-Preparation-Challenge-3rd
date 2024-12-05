import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

class Solution {
    static int size;        // 문자열의 길이
    static String str;
    static Map<Character, Character> hashMap;

    /*
        괄호가 유효할까
    */
    public boolean isValid(int start) {
        Stack<Character> stack = new Stack();
        // 처음부터 닫힌 괄호라면 유효하지 않다
        char ch = str.charAt(start);
        if(ch == ')' || ch == ']' || ch == '}') {
            return false;
        }
        stack.add(ch);      // 열린 괄호를 빈 스택에 추가

        for(int i=1; i<size; i++) {
            char cur = str.charAt((i + start) % size);
            // 열린 괄호는 스택에 추가
            if(cur == '(' || cur == '{' || cur == '[') {
                stack.add(cur);
            } else if(!stack.isEmpty()) {       // 닫힌 괄호일 때 스택에서 가장 위에 있는 문자와 비교
                char top = stack.peek();
                // 짝이 맞는지 검증
                if(hashMap.get(cur) != top) {
                    return false;
                }
                stack.pop();        // 짝이 맞아서 열린 괄호 꺼내기
            } else {        // 스택이 비었다면
                return false;
            }
        }

        // 스택이 비어있지 않으면
        if(!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    public int solution(String s) {
        int answer = 0;
        size = s.length();
        str = s;
        hashMap = new HashMap();
        hashMap.put(')', '(');
        hashMap.put('}', '{');
        hashMap.put(']', '[');

        for(int i=0; i<size; i++) {
            if(isValid(i)) {
                answer++;
            }
        }
        return answer;
    }
}