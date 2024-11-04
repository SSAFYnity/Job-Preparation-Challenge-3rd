import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(bf.readLine());

    int n = Integer.parseInt(st.nextToken()); // 트럭 수
    int w = Integer.parseInt(st.nextToken()); // 다리 길이
    int l = Integer.parseInt(st.nextToken()); // 다리 최대 하중

    st = new StringTokenizer(bf.readLine());
		Queue<Integer> tqu = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			tqu.add(Integer.parseInt(st.nextToken()));
		}

		Queue<Integer> bqu = new LinkedList<>();
		for (int i = 0; i < w; i++) {
			bqu.add(0);
		}

    int time =0;
    int cw=0;
    while(!bqu.isEmpty()){
      
      time++;
      cw -= bqu.poll();

      if(tqu.isEmpty()){
        continue;
      }

      if(tqu.peek()+cw <= l){
        int t = tqu.poll();
        cw+= t;
        bqu.add(t);

      }
      else{
        bqu.add(0);
      }
    }
    System.out.println(time);
  }
}