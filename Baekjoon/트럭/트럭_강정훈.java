import java.io.*;
import java.util.*;

public class 트럭_강정훈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Queue<Integer> trucks = new ArrayDeque<>();
        for (int index = 0; index < n; index++) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }
        Queue<int[]> bridge = new ArrayDeque<>();
        int time = 0;
        int weight = 0;
        while(!trucks.isEmpty() || !bridge.isEmpty()) {
            if(!bridge.isEmpty()) {
                int[] currentTruck = bridge.peek();
                if (time >= currentTruck[1]) {
                    weight -= currentTruck[0];
                    bridge.poll();
                }
            }
            // 들어갈 트럭이 남아 있다.
            if (!trucks.isEmpty()) {
                int currentTruck = trucks.peek();
                // 트럭을 넣을 수 있다면
                if(weight + currentTruck <= L) {
                    bridge.add(new int[]{currentTruck, time+w});
                    weight += currentTruck;
                    trucks.poll();
                    if(trucks.isEmpty()) {
                        System.out.println(time+w+1);
                        return;
                    }
                }
            }
            time++;
        }
    }

}