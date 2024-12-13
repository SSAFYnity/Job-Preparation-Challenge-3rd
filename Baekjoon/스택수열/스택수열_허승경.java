/*
 * 아이디어
 * 1. 1~n까지 statck에 push
 * 2. 만드는 수열의 현재 숫자와 stack의 마지막 요소가 같다면 빼기
 * */
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		int cnt = 1;
		
		for(int i = 0; i < n; i++) {
			while(cnt <= arr[i]) {
				stack.add(cnt++);
				sb.append("+"+"\n");
			}
			if(stack.peek() == arr[i]) {
				stack.pop();
				sb.append("-"+"\n");
			}else {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println(sb.toString());
	}

}
