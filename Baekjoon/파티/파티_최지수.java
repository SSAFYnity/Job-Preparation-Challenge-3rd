import java.io.*;
import java.util.*;

public class Main { 
	static int n;
	static int m;
	static int f;
	
	static int INF = Integer.MAX_VALUE;
	
    static int[][] map;
    static boolean[] visit;
    static int result;
    static List<List<Integer>> list = new ArrayList<>();

    static class Step implements Comparable<Step>{
    	int x, t;
    	public Step(int x, int t) {
    		this.x = x;
    		this.t = t;
    	}
    	
    	@Override
    	public int compareTo(Step o) {
	    	// TODO Auto-generated method stub
	    	return this.t - o.t;
    	}
    }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		int[][] dij = new int[n+1][n+1];
		for (int i = 0; i < n+1; i++) {
			for (int j = 0; j < n+1; j++) {
				map[i][j] = INF;
				dij[i][j] = INF;
			}
			list.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int de = Integer.parseInt(st.nextToken());
			int ar = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[de][ar] = time;
			list.get(de).add(ar);
		}
		

		int maxi = 0;
		PriorityQueue<Step> que = new PriorityQueue<Main.Step>();
		
		for (int i = 1; i <= n; i++) {
			if (i == f) continue; 
			
			que.clear();
			que.add(new Step(i, 0));
			visit = new boolean[n+1];
			
			while (!que.isEmpty()) {
				Step now = que.poll();
				visit[now.x] = true;
				for (int j : list.get(now.x)) {
					if (visit[j]) continue;
					int acc = now.t + Math.min(map[now.x][j], dij[now.x][j]);
					if (acc < dij[i][j]) {
						dij[i][j] = acc;
						que.add(new Step(j, acc));
					}
				}
			}
			
			que.clear();
			que.add(new Step(f, 0));
			visit = new boolean[n+1];
			
			while (!que.isEmpty()) {
				Step now = que.poll();
				visit[now.x] = true;
				for (int j : list.get(now.x)) {
					if (visit[j]) continue;
					int acc = now.t + Math.min(map[now.x][j], dij[now.x][j]);
					if (acc < dij[f][j]) {
						dij[f][j] = acc;
						que.add(new Step(j, acc));
					}
				}
			}
		}
		
		
		
		for (int i = 1; i <= n; i++) {
			if (i == f) continue;
			if (maxi < dij[i][f] + dij[f][i]) maxi = dij[i][f] + dij[f][i];
		}
        
        System.out.println(maxi);
	}
}
