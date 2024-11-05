import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		

		int[][] arr = new int[h+x][w+y];
	
		int[][] res = new int[h][w];
		
		for ( int i=0;i<h+x;i++) {
			st = new StringTokenizer(bf.readLine());
			for ( int j=0;j<w+y;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				
				if(arr[i][j]==0)continue;
				
				
				if (i<x & j<w ) {
					res[i][j] = arr[i][j];
				}
				
				if (i>=x) {
					if( j<y ) {
						res[i][j] = arr[i][j];
					}
					else if( j>=w ) {
						res[i-x][j-y] = arr[i][j];
					}					
				}
				
				if(i>=h) {
					res[i-x][j-y] = arr[i][j];
				}
			}
		}
		
		for ( int i=0;i<h-x-1;i++) {
			for ( int j=0;j<w-y-1;j++) {
				res[x+i][y+j]=arr[x+i][y+j]-res[i][j];
			}
		}
		
		for ( int i=0;i<h;i++) {
			for ( int j=0;j<w;j++) {
				System.out.print(res[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		
	}

}
