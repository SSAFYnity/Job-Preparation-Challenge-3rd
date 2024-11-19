import java.util.*;

public class 메뉴리뉴얼_김수빈 {
    static class Solution {
        // 각 손님들이 주문한 단품메뉴 orders (중복 없음)
        // 추가하고 싶은 코스요리를 구성하는 단품메뉴 개수 담긴 course (오름차순 정렬)
        public String[] solution(String[] orders, int[] course) {
            int[] maxValue = new int[11];
            HashMap<String, Integer> setMenu = new HashMap<>();
            for (int num : course) {
                for (String order : orders) {
                    // 정렬해서 기준을 맞추기 (손님이 주문한 음식이 정렬이라는 소리는 없었음...)
                    char[] charArray = order.toCharArray();
                    Arrays.sort(charArray);
                    String sortedOrder = new String(charArray);
                    dfs(0, sortedOrder, "", setMenu, num, maxValue);
                }
            }
            ArrayList<String> result = new ArrayList<>();
            // 코스 요리로 만들 세트 선택 (가장 많이 담긴 것, 똑같다면 여러 개 다)
            for (Map.Entry<String, Integer> entry : setMenu.entrySet()) {
                int lengthKey = entry.getKey().length();
                int value = entry.getValue();
                if (value == maxValue[lengthKey] && value >= 2) {
                    result.add(entry.getKey());
                }
            }
            Collections.sort(result);
            String[] answer = result.toArray(new String[0]);
            return answer;
        }

        private static void dfs(int i, String order, String menu, HashMap<String, Integer> setMenu, int num, int[] maxValue) {
            // 메뉴가 특정 num개의 조합으로 만들어졌때 체크
            if (menu.length() == num) {
                setMenu.compute(menu, (k, v) -> (v == null) ? 1 : v + 1);
                // 가장 많이 주문한 세트메뉴 찾기 위해 계산
                if (maxValue[num] < setMenu.get(menu)) {
                    maxValue[num] = setMenu.get(menu);
                }
                return;
            }
            // 인덱스가 범위 벗어나지 않도록 체크
            if (i >= order.length()) {
                return;
            }
            dfs(i + 1, order, menu + order.charAt(i), setMenu, num, maxValue);  // 현재 문자 추가
            dfs(i + 1, order, menu, setMenu, num, maxValue);  // 현재 문자 추가 x
        }

        public static void main(String[] args) {
            Solution sol = new Solution();
            String[] orders = {"XYZ", "XWY", "WXA"};
            int[] course = {2, 3, 4};
            String[] result = sol.solution(orders, course);
            System.out.println(Arrays.toString(result));
        }
    }
}
