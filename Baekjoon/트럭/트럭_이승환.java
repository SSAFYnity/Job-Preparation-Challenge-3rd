import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int w = scanner.nextInt();
        int l = scanner.nextInt();
        
        Queue<Integer> trucksWaiting = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            trucksWaiting.add(scanner.nextInt());
        }
        
        Queue<Integer> bridge = new LinkedList<>();
        Queue<Integer> trucksPosition = new LinkedList<>();
        int totalWeightOnBridge = 0;
        int time = 0;

        while (!bridge.isEmpty() || !trucksWaiting.isEmpty()) {
            time++;

            if (!bridge.isEmpty()) {
                for (int i = 0; i < trucksPosition.size(); i++) {
                    int newPosition = trucksPosition.poll() + 1;
                    trucksPosition.add(newPosition);
                }

                if (trucksPosition.peek() > w) {
                    totalWeightOnBridge -= bridge.poll();
                    trucksPosition.poll();
                }
            }

            if (!trucksWaiting.isEmpty()) {
                int nextTruckWeight = trucksWaiting.peek();
                if (totalWeightOnBridge + nextTruckWeight <= l) {
                    bridge.add(trucksWaiting.poll());
                    trucksPosition.add(1);
                    totalWeightOnBridge += nextTruckWeight;
                }
            }
        }
        
        System.out.println(time);
        scanner.close();
    }
}
