import java.util.*;
import java.io.*;
public class 트럭_안창호 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 트럭의 수
        int W = Integer.parseInt(input[1]); // 단위 길이
        int L = Integer.parseInt(input[2]); // 최대 하중

        Queue<Integer> trucks = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();
        int time = 0;
        int bridgeWeight = 0;

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            trucks.add(Integer.parseInt(input[i]));
        }

        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }

        while (!bridge.isEmpty()) {
            time++;
            bridgeWeight -= bridge.poll();

            if (!trucks.isEmpty()) {
                if (trucks.peek() + bridgeWeight <= L) { // 다리를 지나갈 수 있다면
                    bridgeWeight += trucks.peek();
                    bridge.add(trucks.poll());
                } else {
                    bridge.add(0);
                }
            }
        }

        System.out.println(time);
    }
}