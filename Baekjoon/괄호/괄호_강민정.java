import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static boolean isCorrect(String str) {
        Stack<Character> stack = new Stack();
        int idx = -1;
        int size = str.length();
        while(idx++ < size-1) {
            char ch = str.charAt(idx);
            if(ch == '(') {     // 열린 괄호라면
                stack.add(ch);
            } else {        // 닫힌 괄호라면
                if(stack.isEmpty()) {       // 스택이 비었다면
                    return false;
                } else if(stack.peek() != '(') {    // 열린 괄호가 아니면
                    return false;
                }
                stack.pop();    // 스택에서 열린 괄호를 꺼내기
            }
        }
        if(!stack.isEmpty()) {      // 스택이 비어있지 않다면
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();      // 테스트케이스 수
        while(tc-- > 0) {
            String str = sc.next();
            String answer = "YES";
            if(!isCorrect(str)) {
                answer = "NO";
            }
            System.out.println(answer);
        }
    }
}