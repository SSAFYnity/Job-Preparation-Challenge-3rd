import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
        
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);  // 트럭 개수
        int w = Integer.parseInt(firstLine[1]);  // 다리 길이
        int L = Integer.parseInt(firstLine[2]);  // 다리 하중
        
        String[] secondLine = reader.readLine().split(" ");
        int[] trucks = Arrays.stream(secondLine).mapToInt(Integer::parseInt).toArray();  // n개의 트럭 무게
        
        // w개의 0을 큐에 기본 세팅 (다리 길이)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            queue.add(0);
        }
        
        int cnt = 0;  // 경과 시간 변수
        int idx = 0; // 진입 트럭 인덱스
        int totalWeight = 0;  // 다리 위 트럭 총 무게
        
        while(true) {
            totalWeight -= queue.poll();
            // 남은 트럭이 있고 다리 하중보다 트럭 총 무게가 작거나 같으면 진입
            if (idx < n && (trucks[idx] + totalWeight) <= L) {  
                queue.add(trucks[idx]);
                totalWeight += trucks[idx];
                idx++;
            } else {  // 다리 하중보다 트럭 총 무게가 크다면 보류
                queue.add(0);
            }
            cnt++;
            // 다리 위 트럭 총 무게가 0이 된 경우 종료
            if (totalWeight == 0) {
                break;
            }
        }
        
        System.out.println(cnt);
        
    }
}
