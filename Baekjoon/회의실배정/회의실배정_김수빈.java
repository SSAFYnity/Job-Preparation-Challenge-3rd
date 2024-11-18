import java.io.*;
import java.util.*;

public class 회의실배정_김수빈 {
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        int N = Integer.parseInt(br.readLine());
        int[][] meetingTime = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int e = Integer.parseInt(input[1]);
            meetingTime[i] = new int[] {s, e};
        }
        // 끝나는 시간으로 정렬
        Arrays.sort(meetingTime, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1] , b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });
        // 끝나는 시간 짧은 순서대로 회의 시작하기
        int cnt = 0;
        int end = -1;
        for (int i = 0; i < N; i++) {
            int s = meetingTime[i][0];
            if (s >= end) {
                cnt++;
                end = meetingTime[i][1];
            }
        }
        // 출력
        System.out.println(cnt);

    }
}
