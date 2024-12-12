import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> q = new LinkedList<>();
		Stack<Integer> stack = new Stack<>();

		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			q.offer(Integer.parseInt(br.readLine()));
		}

		int temp = 1;

		while (!q.isEmpty()) {
			if(temp == q.peek()) {
				temp++;
				q.poll();
				sb.append('+').append('\n').append('-').append('\n');
			}else if(temp < q.peek()) {
				stack.add(temp++);
				sb.append('+').append('\n');
			}else {
				if(stack.isEmpty())
					break;
				
				if(q.peek() <= stack.pop()) {
					q.poll();
					sb.append('-').append('\n');
				}else {
					break;
				}
			}
		}
		if(q.isEmpty())
			System.out.println(sb);
		else
			System.out.println("NO");

	}
}