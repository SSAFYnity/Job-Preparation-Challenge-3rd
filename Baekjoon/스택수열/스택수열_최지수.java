import java.util.*;
import java.io.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int t = 0; t < n; t++) {
            int num = Integer.parseInt(br.readLine());
            arr[t] = num;
        }

        int point = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i < n+1; i++) {
            if (i == arr[point]) {
                point++;
                sb.append("+\n-\n");
                while (!stack.isEmpty() && stack.peekFirst() == arr[point]) {
                    stack.pop();
                    point++;
                    sb.append("-\n");
                }
            } else {
                stack.push(i);
                sb.append("+\n");
            }
        }

        while (!stack.isEmpty() && point < n) {
            if (arr[point] == stack.peek()) {
                point++;
                sb.append("-\n");
            } else break;
        }

        System.out.println(point == n ? sb.toString() : "NO");

    }
}

