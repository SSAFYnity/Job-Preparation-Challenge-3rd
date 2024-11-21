import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 메뉴_리뉴얼_이호찬 {

	// 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 반환하기 (오름차순)
	// 2명 이상의 손님으로부터 주문된 단품메뉴 조합 구하기

	private Map<String, Integer> map;
	private char[] selectedNum;

	private void combination(int depth, int start, int goal, String str) {
		if (depth == goal) {
			String result = new String(selectedNum);
			if (map.containsKey(result)) {
				map.put(result, map.get(result) + 1);
			} else {
				map.put(result, 1);
			}
			return;
		}

		for (int i = start; i < str.length(); i++) {
			selectedNum[depth] = str.charAt(i);
			combination(depth + 1, i + 1, goal, str);
		}
	}

	public String[] solution(String[] orders, int[] course) {
		map = new HashMap<>();
		List<String> result = new ArrayList<>();

		for (int i = 0; i < course.length; i++) {
			map.clear();
			// 메뉴 조합
			for (int j = 0; j < orders.length; j++) {
				if (orders[j].length() < course[i]) {
					continue;
				}
				selectedNum = new char[course[i]];
				char[] tmp = orders[j].toCharArray();
				Arrays.sort(tmp);
				combination(0, 0, course[i], new String(tmp));
			}
			// 메뉴 조합 정렬 (내림차순)
			List<Order> list = new ArrayList<>();
			for (String key : map.keySet()) {
				list.add(new Order(key, map.get(key)));
			}
			// Collections.sort(list, (o1, o2) -> o2.cnt - o1.cnt);
			Collections.sort(list, new Comparator<Order>() {
				public int compare(Order o1, Order o2) {
					return o2.cnt - o1.cnt;
				}
			});
			// 2번 이상 주문 && 가장 많이 주문한 메뉴들
			for (int j = 0; j < list.size(); j++) {
				if (list.get(0).cnt >= 2 && list.get(0).cnt == list.get(j).cnt) {
					result.add(list.get(j).name);
				} else {
					break;
				}
			}
		}
		// 새로 추가할 코스요리 메뉴명 정렬 (오름차순)
		Collections.sort(result);
		String[] answer = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			answer[i] = result.get(i);
		}
		return answer;
	}

	private class Order {
		private String name;
		private int cnt;

		public Order(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
	}
}
