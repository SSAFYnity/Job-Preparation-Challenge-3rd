import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] trucks = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer> bridge = new ArrayDeque<>();
        for (int i = 0; i < w; i++) {
            bridge.offer(0);
        }
        int index = 0;
        int time = 0;
        while (index < n) {
            int truck = trucks[index];
            bridge.poll();
            if (sum(bridge) + truck <= l) {
                bridge.offer(truck);
                index++;
            } else {
                bridge.offer(0);
            }
            time++;
        }
        time += bridge.size();
        System.out.println(time);
    }

    static int sum(Deque<Integer> bridge) {
        int sum = 0;
        for (int t : bridge) {
            sum += t;
        }
        return sum;
    }
}