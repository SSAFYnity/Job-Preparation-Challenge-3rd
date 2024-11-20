import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main { 
	static int n;
	static int m;
	
	static List<int[]> que;
	
	static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		que = new ArrayList<int[]>();
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] a = new int[2];
			a[0] = Integer.parseInt(st.nextToken());
			a[1] = Integer.parseInt(st.nextToken());
			que.add(a);
		}
		
		int p = 0;
		
		que.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[1]==o2[1]) {
					return o1[0]-o2[0];
				}
				return o1[1]-o2[1];
			}
		});
		
		for (int[] q : que) {
			if (p <= q[0]) {
				cnt++; 
				p = q[1];
			}
		}
		
		System.out.println(cnt);
	}
}
