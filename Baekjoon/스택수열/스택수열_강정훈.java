import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack();
        int N = Integer.parseInt(br.readLine());
        int[] targetArray = new int[N];
        for (int index = 0; index < N; index++) {
            targetArray[index] = Integer.parseInt(br.readLine());
        }
        int currentNum = 1;
        for (int index = 0; index < N; index++) {
            int target = targetArray[index];
            if (target >= currentNum) {
                while (target >= currentNum) {
                    stack.push(currentNum);
                    currentNum++;
                    answer.append("+\n");
                }
                stack.pop();
                answer.append("-\n");
            } else {
                int poppedNum = stack.pop();
                if (poppedNum > target) {
                    System.out.println("NO");
                    return;
                } else {
                    answer.append("-\n");
                }
            }
        }
        System.out.println(answer);
    }
}
