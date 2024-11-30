import java.util.Arrays;

public class 인사고과_이호찬 {

	// 근무 태도, 동료 평가 점수 합으로 완호의 석차 구하기 (0번째 인덱스)
	// 동일 석차가 있다면, 해당 수만큼 다음 석차는 건너 뜀 (ex. 1 1 3 4)
	// 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우는 석차 제외

	public int solution(int[][] scores) {
		int[] find = scores[0]; // 완호 점수

		Arrays.sort(scores, (o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0])); // 근무 태도 (내림차순), 동료 평가 (오름차순)

		int max = 0, answer = 1;
		for (int[] score : scores) {
			if (score[1] < max) {
				// 완호일 경우: -1 반환
				if (score[0] == find[0] && score[1] == find[1]) {
					return -1;
				}
			} else {
				max = Math.max(max, score[1]);
				if (score[0] + score[1] > find[0] + find[1]) {
					answer++;
				}
			}
		}

		return answer;
	}
}