import java.io.*;
import java.util.*;

public class Main {
	static int distance[] = new int[100001]; // 전체 거리
	static int N, K;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		if(N >= K) {
			System.out.println(N-K);
			return;
		}
		
		for(int i=0; i<N; i++) {
			distance[i] = N-i;
		
		}
		
		DP();
		System.out.print(distance[K]);
	} // End of main
	
	private static void DP() {
		for(int i=N+1; i<=K; i++) {
			
			if(i % 2 == 0) {
				distance[i] = Math.min(distance[i/2] + 1, distance[i-1]+1);
			}
			else {				
				distance[i] = Math.min(distance[(i+1)/2]+2, distance[i-1]+1);
			}
			
		}
	}
} 