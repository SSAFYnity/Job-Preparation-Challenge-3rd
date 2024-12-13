import java.io.*;
import java.util.*;
class Main {
    static boolean[] visited = new boolean[200001];
    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        visited[start] = true;
        if (checkAnswer(start, start, start)) {
            System.out.println(0);
            return;
        }
        while(!q.isEmpty()){
            int[] current = q.poll();
            int index = current[0];
            int count = current[1];
            int prev = index-1;
            int next = index+1;
            int doubleIndex = index*2;
            if (checkAnswer(prev, next, doubleIndex)) {
                System.out.println(count+1);
                return;
            }
            if (check(prev)) {
                q.add(new int[]{prev, count+1});
                visited[prev] = true;
            }
            if (check(next)) {
                q.add(new int[]{next, count+1});
                visited[next] = true;
            }
            if (check(doubleIndex)) {
                q.add(new int[]{doubleIndex, count+1});
                visited[doubleIndex] = true;
            }
        }

    }
    private static boolean checkAnswer(int prev, int next, int doubleIndex) {
        return prev == end || next == end || doubleIndex == end;
    }
    private static boolean check(int index) {
        return 0 <= index && index < 200000 && !visited[index];
    }

}