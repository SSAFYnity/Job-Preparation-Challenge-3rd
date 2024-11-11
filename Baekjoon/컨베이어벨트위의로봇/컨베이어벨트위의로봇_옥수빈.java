import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static int N, K;
	static Deque<Integer> belt1 = new ArrayDeque<>();
	static Deque<Integer> belt2 = new ArrayDeque<>();
	static Deque<Boolean> robot = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			robot.addLast(false);
			belt1.addLast(Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < N; i++) {
			belt2.addLast(Integer.parseInt(st.nextToken()));
		}

		int answer = 1;
		while (true) {
			step1();
			step2();
			step3();
			if (!step4())
				break;
			answer++;
		}
		System.out.println(answer);
	}

	// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
	private static void step1() {
		belt1.addFirst(belt2.pollLast());
		belt2.addFirst(belt1.pollLast());
		robot.pollLast();
		robot.addFirst(false);
		robot.pollLast();
		robot.addLast(false);
	}

	/**
	 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히
	 * 있는다.
	 */
	private static void step2() {
		boolean tempRobot = robot.pollLast();
		int tempBelt = belt1.pollLast();
		for (int i = 1; i < N; i++) {
			if (!tempRobot && tempBelt != 0 && robot.peekLast()) {
				belt1.addFirst(tempBelt - 1);
				robot.pollLast();
				robot.addLast(false);
				robot.addFirst(true);
			} else {
				robot.addFirst(tempRobot);
				belt1.addFirst(tempBelt);
			}
			tempRobot = robot.pollLast();
			tempBelt = belt1.pollLast();
		}

		robot.addFirst(false);
		belt1.addFirst(tempBelt);
		robot.pollLast();
		robot.addLast(false);
	}

	// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
	private static void step3() {
		if (belt1.peekFirst() != 0) {
			belt1.addFirst(belt1.pollFirst() - 1);
			robot.pollFirst();
			robot.addFirst(true);
		}
	}

	// 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
	private static boolean step4() {
		int countZero = 0;
		for (int b : belt1) {
			if (b == 0)
				countZero++;
			if (countZero == K)
				return false;
		}
		for (int b : belt2) {
			if (b == 0)
				countZero++;
			if (countZero == K)
				return false;
		}
		return true;
	}

}