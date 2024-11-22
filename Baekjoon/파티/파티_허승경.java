import java.io.*;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point>{
        int v;
        int time;

        public Point(int v, int time){
            this.v = v;
            this.time = time;
        }

        public int compareTo(Point o){
            return this.time - o.time;  // time 기준으로 오름차순 정렬
        }
    }

    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Point>> info_list = new ArrayList<>();
        ArrayList<ArrayList<Point>> re_list = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            info_list.add(new ArrayList<>());
            re_list.add(new ArrayList<>());
        }

        // 정보 입력
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            info_list.get(a).add(new Point(b, c));
            re_list.get(b).add(new Point(a, c));
        }

        int [] toParty = dijkstra(n, x, re_list);     // 각자 집 -> 파티 장소(X) 최단 거리 구하기
        int [] toHome = dijkstra(n, x, info_list);        // 파티 장소(x) -> 각자 집 최단 거리 구하기

        // 최장 소요 시간 구하기
        int result = 0;
        for(int i = 1; i <= n; i++){
            result = Math.max(result, (toParty[i]+toHome[i]));
        }

        System.out.println(result);
    }

    static int[] dijkstra(int n, int x, ArrayList<ArrayList<Point>> list){
        int [] res = new int[n+1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[x] = 0;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(x, 0));

        while(!pq.isEmpty()){
            Point p = pq.poll();

            ArrayList<Point> tmp_list = list.get(p.v);
            for(int i = 0; i < tmp_list.size(); i++){
                int a = tmp_list.get(i).v;
                int b = tmp_list.get(i).time;

                if(res[a] > p.time + b){
                    res[a] = p.time+b;
                    pq.add(new Point(a, res[a]));
                }
            }
        }
        return res;
    }
}
