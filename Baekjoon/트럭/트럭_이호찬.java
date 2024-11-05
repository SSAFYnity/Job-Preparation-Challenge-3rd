import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트럭_이호찬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 트럭 수
		int w = Integer.parseInt(st.nextToken()); // 다리 길이
		int l = Integer.parseInt(st.nextToken()); // 다리 최대 하중

		st = new StringTokenizer(br.readLine());
		int[] trucks = new int[n];
		for (int i = 0; i < n; i++) {
			trucks[i] = Integer.parseInt(st.nextToken());
		}

		// int[] trucks = Stream.of(br.readLine().split(" "))
		// 	.mapToInt(Integer::parseInt)
		// 	.toArray();

		int time = 1; // 소요 시간, 1초부터 트럭 이동
		int idx = 0; // 현재 트럭 idx
		int weight = trucks[idx]; // 현재 다리 하중
		Queue<Truck> q = new ArrayDeque<>(); // 다리 위에 있는 트럭
		q.offer(new Truck(trucks[idx], time + w)); // 트럭 무게, 트럭이 다리를 빠져나올 시간을 저장
		idx++;

		// 모든 트럭이 지나갈 때까지
		while (idx < n) {
			time++;

			// 트럭이 지나갈 수 있는지
			if (!q.isEmpty() && q.peek().t == time) {
				Truck truck = q.poll();
				weight -= truck.w;
			}

			// 최대 다리 길이만큼 올라갈 수 있음, 현재 하중 + 새 트럭 무게 <= 다리 최대 하중
			if (q.size() < w && weight + trucks[idx] <= l) {
				q.offer(new Truck(trucks[idx], time + w));
				weight += trucks[idx];
				idx++;
			}
		}

		while (!q.isEmpty()) {
			time = q.poll().t;
		}

		bw.write(time + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Truck {
		int w; // 트럭 무게
		int t; // 다리 빠져나올 시간

		public Truck(int w, int t) {
			this.w = w;
			this.t = t;
		}
	}
}