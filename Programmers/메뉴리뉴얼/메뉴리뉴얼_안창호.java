import java.util.*;

class Solution {
    static Map<String, Integer> map;
    static List<String> answer;
    public List<String> solution(String[] orders, int[] course) {
        // course 순회 num
        // orders 순회하면서 정렬된 order 별로
        // HashMap String, Integer 코스 요리별 카운트 추가
        // 최소 카운트는 2 이상이고, 가장 많은 카운트가 선정된 요리(들)를 answer 삽입
        // 이후 정렬하고 리턴
        answer = new ArrayList<>();

        for (int courseNum : course) {
            map = new HashMap<>();

            for (String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                findCourse("", new String(arr), courseNum, 0);
            }

            int maxCourseValue = 0;
            for (String key : map.keySet()) {
                maxCourseValue = Math.max(maxCourseValue, map.get(key));
            }

            if (maxCourseValue < 2) continue;

            for (String key : map.keySet()) {
                if (map.get(key) >= maxCourseValue) {
                    answer.add(key);
                }
            }
        }

        Collections.sort(answer, Comparator.naturalOrder());
        return answer;
    }
    public void findCourse(String cur, String order, int num, int idx) {
        if (cur.length() == num) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            findCourse(cur + order.charAt(i), order, num, i + 1);
        }
    }
}