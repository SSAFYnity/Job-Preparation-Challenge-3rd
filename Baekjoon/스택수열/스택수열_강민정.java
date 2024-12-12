import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack();
        int n = sc.nextInt();
        int cur = 1;

        for(int i=0; i<n; i++) {
            int num = sc.nextInt();
            while (num >= cur) {
                stack.push(cur);
                sb.append("+\n");
                cur++;
            }
            if (!stack.isEmpty() && stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            }
        }

        if(!stack.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println(sb.toString());
        }
    }
}