import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] trucks = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        int idx = 0;
        int sum = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        while(true) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                if(queue.peekFirst()[1] >= w - 1) {
                    sum -= queue.pollFirst()[0];
                } else {
                    int[] t = queue.pollFirst();
                    t[1]++;
                    queue.addLast(t);
                }
            }

            if(idx < n && sum + trucks[idx] <= L) {
                sum += trucks[idx];
                queue.addLast(new int[]{trucks[idx++], 0});
            }

            time++;

            if(queue.isEmpty()) break;
        }

        System.out.println(time);
    }
}
