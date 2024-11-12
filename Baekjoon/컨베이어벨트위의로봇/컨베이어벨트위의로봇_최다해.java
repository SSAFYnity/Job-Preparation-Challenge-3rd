import java.io.*;
import java.util.*;
public class Main {

  public static void main(String[] args) throws IOException {
	  BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st = new StringTokenizer(bf.readLine());
	
	  //벨트 길이
	  int n = Integer.parseInt(st.nextToken()); 
	  //내구도가 0인 칸의 개수
	  int k = Integer.parseInt(st.nextToken()); 
	  //벨트 내구도
	  int[] arr = new int[2*n];
	  //로봇 여부
	  boolean[] isrrr = new boolean[2*n];
	  
	  //벨트 위치번호
	  int[] brr = new int[2*n];
	  

	  //로봇 위치
	  Queue<Integer> qu = new ArrayDeque<>();
	  
	  st = new StringTokenizer(bf.readLine());
	  
	  for(int i=0; i<2*n;i++) {
		  int a = Integer.parseInt(st.nextToken());
		  arr[i] = a;
		  brr[i] = i;

	  }
	  
	  int zb = 0;
	  int idx =0;
	  int totidx =0;
	  int l =0;
	  while(true) {
		  l++;
		  if(l ==5) l=1;
		  int size;

		  switch(l) {
			  case 1 : //벨트 회전
				  for(int i=0; i<2*n;i++) {
					  brr[i] = brr[i]-1;
					  if(brr[i]==-1)brr[i]=(2*n)-1;
				  }
				  size = qu.size();
				  for(int i=0; i<size;i++) {
					  int x = qu.poll();
					  int nx = (x+1)%(2*n);

					  //내리는 위치일때
					  if(nx ==n) {
						  isrrr[x]=false;
					  }else {
						  qu.add(nx);
						  isrrr[x]=false;
						  isrrr[nx]=true;
					  }
					  
				  }
				  
				  break;
				  
			  case 2 : //로봇 이동
				  size= qu.size();
				  for(int i=0; i<size;i++) {
					  int x = qu.poll();
					  int nx = (x+1)%(2*n);
					  
					  //내리는 위치일때
					  if(nx ==n) {
						  isrrr[x]=false;
						  continue;
					  }
					  
					  //이동 하는 경우
					  if(!isrrr[nx] & arr[brr[nx]]>=1) {
						  qu.add(nx);
						  
						  arr[brr[nx]]=arr[brr[nx]]-1;
						  if(arr[brr[nx]]==0)zb++;
						  isrrr[x]=false;
						  isrrr[nx]=true;
					  }
					  //멈춘 경우 경우
					  else {
						  qu.add(x);
					  }						 					  
				  }
				  
				  break;
			  case 3 : //로봇 올리기
				  if(arr[brr[0]]>=1) {
					  qu.add(0);
					  isrrr[0]=true;
					  arr[brr[0]]=arr[brr[0]]-1;
					  if(arr[brr[0]]==0)zb++;						  
				  }
				  break;
			  case 4 : 
				  totidx++;
				 
				  
				  if(zb>=k) {
					  
					  System.out.println(totidx);
					  return;  
				  }
				  
				  break;
		  }


	  }
	  
	  
  }
}