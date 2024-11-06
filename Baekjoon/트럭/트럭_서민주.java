import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Queue<Integer> truck = new LinkedList<>();
        for (int i = 0; i<n; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }
        int time = 0;
        int bw = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i<w; i++) {
            q.add(0);
        }
        while (!q.isEmpty()) {
            time++;
            bw -= q.poll();
            if (!truck.isEmpty()) {
                if (truck.peek()+bw<=L) {
                    bw += truck.peek();
                    q.offer(truck.poll());
                } else {
                    q.offer(0);
                }
            }
        }
        System.out.println(time);
    }
}
