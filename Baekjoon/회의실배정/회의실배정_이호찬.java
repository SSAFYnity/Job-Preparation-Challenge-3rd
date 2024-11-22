import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 회의실배정_이호찬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// n개의 회의 정보 저장
		PriorityQueue<Meeting> pq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		int cnt = 1;
		int endTime = pq.poll().end;
		while (!pq.isEmpty()) {
			Meeting cur = pq.poll();
			if (endTime <= cur.start) {
				endTime = cur.end;
				cnt++;
			}
		}

		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Meeting m) {
			if (this.end == m.end) {
				return this.start - m.start;
			}
			return this.end - m.end;
		}
	}
}