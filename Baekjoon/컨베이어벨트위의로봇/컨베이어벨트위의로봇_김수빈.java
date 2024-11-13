import java.util.*;
import java.io.*;

// 길이가 2N인 벨트가 N 길이의 컨베이어 벨트를 위 아래 감싸며 돌고 있음
// 로봇 올리는 위치에만 올릴 수 있음
// 로봇 올리거나 이동 시 칸 내구도 1 감소 (로봇 이동 위해 칸 내구도 1 이상 남아야 함)
// 내구도 0인 카의 개수가 K 이상이면 과정 종료
public class 컨베이어벨트위의로봇_김수빈 {
    public static void main(String[] args) throws IOException {
        // 초기 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);  // 벨트 길이
        int K = Integer.parseInt(input[1]);  // 종료 조건
        String[] arr = br.readLine().split(" ");
        int[] durabilityArr = new int[2 * N];  // 벨트 내구도 배열
        for (int i = 0; i < 2 * N; i++) {
            durabilityArr[i] = Integer.parseInt(arr[i]);
        }
        boolean[] robots = new boolean[2 * N];  // 해당 위치에 로봇 존재 여부 배열
        int zero = 0;  // 내구도 0인 벨트 개수
        int step = 0;
        // 내구도 0인 벨트 개수가 K개 이상이면 종료
        while (zero < K) {
            // 1. 벨트 이동
            int[] temp1 = new int[2 * N];
            temp1[0] = durabilityArr[2 * N - 1];
            boolean[] temp2 = new boolean[2 * N];
            temp2[0] = robots[2 * N - 1];
            for (int i = 1; i < 2 * N; i++) {
                temp1[i] = durabilityArr[i - 1];
                temp2[i] = robots[i - 1];
            }
            durabilityArr = temp1;
            robots = temp2;
            robots[N - 1] = false;
            // 2. 로봇 이동
            for (int j = N - 2; j >= 0; j--) {
                // 로봇이 위의 컨베이어벨트고 앞에 갈 수 있다면
                if (robots[j] == true && robots[j + 1] == false && durabilityArr[j + 1] > 0) {
                    robots[j + 1] = true;
                    durabilityArr[j + 1]--;
                    robots[j] = false;
                }
            }
            robots[N - 1] = false;
            // 3. 로봇 올리기
            if (durabilityArr[0] > 0) {
                durabilityArr[0]--;
                robots[0] = true;
            }
            // 컨테이너벨트 내구성 파악하기
            int check = 0;
            for (int d: durabilityArr) {
                if (d == 0) {
                    check++;
                }
            }
            zero = check;
            step++;
        }
        System.out.println(step);
    }
}
