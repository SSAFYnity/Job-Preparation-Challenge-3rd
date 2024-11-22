import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            list.add(new int[]{start, end});
        }

        Collections.sort(list, (o1,o2) -> o1[1] == o2[1] ? o1[0]-o2[0] : o1[1]-o2[1]);

        int answer = 0;
        int end = 0;

        for (int[] time : list) {
            if (time[0] >= end) {
                answer++;
                end = time[1];
            }
        }

        System.out.println(answer);
    }
}