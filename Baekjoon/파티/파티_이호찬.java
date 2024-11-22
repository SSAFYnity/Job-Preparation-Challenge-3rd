import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티_이호찬 {

	private static int n, m, x;
	private static List<List<Road>> roads, reverseRoads;

	private static int[] getDijkstra(List<List<Road>> roads) {
		int[] distance = new int[n + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[x] = 0;

		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.offer(new Road(x, 0));

		while (!pq.isEmpty()) {
			Road cur = pq.poll();
			if (cur.weight > distance[cur.to]) {
				continue;
			}
			for (Road road : roads.get(cur.to)) {
				int cost = distance[cur.to] + road.weight;
				if (cost < distance[road.to]) {
					distance[road.to] = cost;
					pq.offer(new Road(road.to, distance[road.to]));
				}
			}
		}

		return distance;
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 학생 수
		m = Integer.parseInt(st.nextToken()); // 도로 수
		x = Integer.parseInt(st.nextToken()); // 목표 지점

		roads = new ArrayList<>();
		reverseRoads = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			roads.add(new ArrayList<>());
			reverseRoads.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			roads.get(from).add(new Road(to, weight));
			reverseRoads.get(to).add(new Road(from, weight));
		}

		br.close();
	}

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		init();

		int[] distance = getDijkstra(roads);
		int[] reverseDistance = getDijkstra(reverseRoads);

		int result = 0;
		for (int i = 1; i <= n; i++) {
			result = Math.max(result, distance[i] + reverseDistance[i]);
		}

		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}

	private static class Road implements Comparable<Road> {
		int to;
		int weight;

		public Road(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		public int compareTo(Road r) {
			return this.weight - r.weight;
		}
	}
}
