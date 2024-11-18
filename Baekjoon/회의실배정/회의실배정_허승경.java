import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
        int [][] times = new int[n][2];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }

        // 정렬 (종료시간 기준)
        Arrays.sort(times, (a, b) -> {
            if(a[1]== b[1]) return Integer.compare(a[0], b[0]); // 종료시간 같으면 시작시간 기준 정렬
            return Integer.compare(a[1], b[1]);
        });

        int count = 0;
        int endTime = 0;

        for(int i = 0; i < n; i++){
            if(times[i][0] >= endTime){
                endTime = times[i][1];
                count++;
            }
        }
        
        System.out.println(count);
    }
}
