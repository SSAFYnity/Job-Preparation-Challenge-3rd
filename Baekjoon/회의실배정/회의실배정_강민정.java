import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());        // 회의의 수
        int cnt = 0;
        List<long[]> timeTable = new ArrayList();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            timeTable.add(new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())});      // 회의 시작 시각, 회의 끝나는 시각
        }

        // 시간 순으로 정렬: 시작 시각이 같은 경우, 끝나는 시각을 먼저 처리
        Collections.sort(timeTable, (a, b) -> {
            if(a[1] == b[1]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);  // 먼저 끝나는 시각이 오도록 정렬
        });

        int idx = 0;
        long cur = 0;    // 이전 회의가 끝난 시각
        while(idx < timeTable.size()) {
            if(cur <= timeTable.get(idx)[0]) {
                cur = timeTable.get(idx)[1];
                cnt++;
            }
            idx++;
        }

        // 최대 사용할 수 있는 회의의 최대 개수를 출력
        System.out.println(cnt);
    }
}