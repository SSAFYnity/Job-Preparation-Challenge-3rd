public class 당구_연습 {

	// 4방향 탐색
	// 경로에 목표공이 있는지 확인

	private int getLength(int r, int c) {
		return (int)Math.pow(r, 2) + (int)Math.pow(c, 2);
	}

	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];
		for (int i = 0; i < balls.length; i++) {
			int min = Integer.MAX_VALUE;
			if ((startY != balls[i][1]) || (startX < balls[i][0])) { // x -> 0
				min = Math.min(min, getLength(startX + balls[i][0], Math.abs(startY - balls[i][1])));
			}
			if ((startY != balls[i][1]) || (startX > balls[i][0])) { // x -> m
				min = Math.min(min, getLength(2 * m - startX - balls[i][0], Math.abs(startY - balls[i][1])));
			}
			if ((startX != balls[i][0]) || (startY < balls[i][1])) { // y -> 0
				min = Math.min(min, getLength(Math.abs(startX - balls[i][0]), startY + balls[i][1]));
			}
			if ((startX != balls[i][0]) || (startY > balls[i][1])) { // y -> n
				min = Math.min(min, getLength(Math.abs(startX - balls[i][0]), 2 * n - startY - balls[i][1]));
			}
			answer[i] = min;
		}
		return answer;
	}
}
