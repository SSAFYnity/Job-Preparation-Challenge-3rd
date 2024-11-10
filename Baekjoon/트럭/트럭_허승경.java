import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int [] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> que = new LinkedList<>();
        int total_time = 0; // 다리 건너는 총 시간
        int cur_weight = 0;
        int idx = 0;

        while(idx < n || cur_weight > 0){
            total_time++;       // 1초 증가

            if(que.size() == w){
                cur_weight -= que.poll();
            }

            if(idx < n && cur_weight + trucks[idx] <= l){
                que.add(trucks[idx]);
                cur_weight += trucks[idx];
                idx++;
            }else{
                que.add(0);
            }
        }

        System.out.println(total_time);
    }
}