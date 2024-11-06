import java.io.*;
import java.util.*;

public class Main {
	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	static int n; //복도 크기
	static char[][] map; //복도
	
	static ArrayList<Node> srr = new ArrayList<>(); //학생위치
	static ArrayList<Node> trr = new ArrayList<>(); //선생위치
	
	static String res = "NO";
	
	public static void main(String[] args)throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n  = Integer.parseInt(bf.readLine());
		map = new char[n][n];
		for(int i =0; i<n;i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j =0; j<n;j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j]=='S') {
					srr.add(new Node(i,j));
				}
				else if (map[i][j]=='T') {
					trr.add(new Node(i,j));
				}
			}
		}
		makeo(0);
		System.out.print(res);
		
	}
	
	// 장애물 설치
	static void makeo(int cnt){
		if(res.equals("Yes"))return;
		if(cnt==3) {
			watch();
			return;
		}
		
		for(int i =0; i<n;i++) {
			for(int j =0; j<n;j++) {
				
				//빈공간만
				if(map[i][j]=='X') {
					
					map[i][j]='O';
					makeo(cnt+1);
					map[i][j]='X';
					
				}
				
				
			}
		}
	}
	
	//감시
	static void watch() {
		for (Node tn : trr) {

			int f=0;
			for (Node sn : srr) {

        // 선생과 학생이 x열이 같을때
				if(tn.x==sn.x) {	
					f=1;
					for(int y =Math.min(tn.y,sn.y) ; y< Math.max(tn.y,sn.y); y++) {
						if(map[tn.x][y]=='O') {
							f=0;
							break;
							}
					}		
					
				}

				// 선생과 학생이 y열이 같을때
				if(tn.y==sn.y) {	
					f=1;
					for(int x =Math.min(tn.x,sn.x) ; x< Math.max(tn.x,sn.x); x++) {
						if(map[x][tn.y]=='O') {
							f=0;
							break;
							}
					}		
					
				}
				if(f==1)return;
			}
			
		}
		res="YES";
		
	}

	

}
