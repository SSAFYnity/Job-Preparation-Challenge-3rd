import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String string = br.readLine();
            if (isVPS(string))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public static boolean isVPS(String string) {
        Stack<Integer> stack = new Stack<>();
        for (char c : string.toCharArray()) {
            if (c == '(')
                stack.add(1);
            else {
                if (stack.isEmpty())
                    return false;
                stack.pop();
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }
}