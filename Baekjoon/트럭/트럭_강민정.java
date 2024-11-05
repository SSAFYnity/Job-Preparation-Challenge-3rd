import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Main
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 다리를 건너는 트럭의 수 <= 1000
        int w = sc.nextInt();       // 1 <= 다리의 길이 <= 100
        int l = sc.nextInt();       // 10 <= 다리의 최대하중 <= 1000
        int[] arr = new int[n];     // 트럭의 무게를 저장하는 배열
        Queue<Integer> bridge = new LinkedList();
        // 트럭의 무게 입력받기
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        // 다리 초기화
        for(int i=0; i<w; i++) {
            bridge.add(0);
        }

        int sum = 0;        // 현재 다리에 올려져있는 트럭의 무게
        int time = 0;       // 시간
        int idx = 0;        // 트럭 배열 인덱스
        while(idx < n) {
            time++;
            sum -= bridge.poll();       // 트럭이 움직인다
            if(sum + arr[idx] <= l) {       // 다리 위에 트럭을 올릴 수 있다면
                sum += arr[idx];        // 다리에 올려져있는 트럭의 무게 증가
                bridge.add(arr[idx]);   // 다리에 트럭 올리기
                idx++;      // 트럭 인덱스 증가
            } else {        // 다리 위에 트럭을 올릴 수 없다면
                bridge.add(0);      // 0은 대기를 의미
            }
        }

        System.out.println(time + bridge.size());       // 남은 트럭도 더해준다
    }
}