import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    static class City {
        int num;        // 도시 번호
        int time;       // 소요되는 시간

        City(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    static int n;       // 학생 수 (도시의 개수)
    static int m;       // 마을의 수 (도로의 개수)
    static int x;       // 출발 마을 번호
    static List<List<City>> lst;     // 도로 정보를 담은 인접 리스트
    static List<List<City>> reversedList;  // 반대 방향 도로 정보를 담은 인접 리스트
    static int[] dijkstraArr1;  // 출발점에서 각 도시로 가는 최단 시간
    static int[] dijkstraArr2;  // 각 도시에서 출발점으로 가는 최단 시간

    // 다익스트라 알고리즘
    public static void dijkstra(int[] dijkstraArr, List<List<City>> info, int start) {
        boolean[] visit = new boolean[n + 1]; // 방문 여부 배열
        dijkstraArr[start] = 0; // 출발점은 0으로 설정

        for (int i = 1; i <= n; i++) {
            int minTime = Integer.MAX_VALUE;
            int minTimeCity = -1;

            // 아직 방문하지 않은 도시 중 최단 시간 가진 도시를 찾기
            for (int j = 1; j <= n; j++) {
                if (!visit[j] && dijkstraArr[j] < minTime) {
                    minTime = dijkstraArr[j];
                    minTimeCity = j;
                }
            }

            // 모든 도시가 방문되었으면 종료
            if (minTimeCity == -1) {
                break;
            }

            // 최단 시간이 결정된 도시 방문 처리
            visit[minTimeCity] = true;

            // 해당 도시에서 연결된 인접 도시들의 최단 시간 갱신
            for (City city : info.get(minTimeCity)) {
                if (dijkstraArr[minTimeCity] + city.time < dijkstraArr[city.num]) {
                    dijkstraArr[city.num] = dijkstraArr[minTimeCity] + city.time;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력값 읽기
        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        x = Integer.parseInt(st.nextToken()); // 출발 도시

        // 다익스트라 배열 초기화
        dijkstraArr1 = new int[n + 1];
        dijkstraArr2 = new int[n + 1];
        Arrays.fill(dijkstraArr1, Integer.MAX_VALUE);
        Arrays.fill(dijkstraArr2, Integer.MAX_VALUE);

        // 리스트 초기화
        lst = new ArrayList<>();
        reversedList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            lst.add(new ArrayList<>());
            reversedList.add(new ArrayList<>());
        }

        // 도로 정보 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            // lst: 정방향 도로
            lst.get(startPoint).add(new City(endPoint, time));
            // reversedList: 역방향 도로
            reversedList.get(endPoint).add(new City(startPoint, time));
        }

        // 출발점에서 모든 도시로 가는 최단 시간 구하기
        dijkstra(dijkstraArr1, lst, x);

        // 모든 도시에서 출발점으로 가는 최단 시간 구하기
        dijkstra(dijkstraArr2, reversedList, x);

        // 가장 먼 도시에 대한 시간 출력
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dijkstraArr1[i] != Integer.MAX_VALUE && dijkstraArr2[i] != Integer.MAX_VALUE) {
                maxTime = Math.max(maxTime, dijkstraArr1[i] + dijkstraArr2[i]);
            }
        }

        System.out.println(maxTime);
    }
}