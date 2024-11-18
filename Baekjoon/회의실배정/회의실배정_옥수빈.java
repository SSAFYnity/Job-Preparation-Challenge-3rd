import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list, (o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);
        int idx = 0;
        int now = 0;
        int answer = 0;
        while(idx < list.size()) {
            Point p = list.get(idx);
            if(now <= p.x) {
                now = p.y;
                answer++;
            }
            idx++;
        }

        System.out.println(answer);
    }
}