import java.util.*;

class Solution {
    static ArrayList<String> data;
    public ArrayList<String> solution(String[] orders, int[] course) {
        data = new ArrayList<>();
        for(int i = 0; i < orders.length; i++) {
            for(int j = 0; j < course.length; j++) {
                if (orders[i].length() < course[j]) continue;
                combination(0, course[j], new boolean[orders[i].length()], 0, orders[i]);
            }
        }

        Map<String, Integer> tmp = new HashMap<>();
        int[] maxCount = new int[course[course.length-1]+1];
        for(String d : data) {
            if (tmp.containsKey(d)) {
                int size = d.length();
                tmp.put(d, tmp.get(d) + 1);
                if (tmp.get(d) > maxCount[size]) {
                    maxCount[size] = tmp.get(d);
                }
            } else {
                tmp.put(d, 1);
            }
        }

        ArrayList<String> answer = new ArrayList<>();
        for(int c : course) {
            for(String d : tmp.keySet()) {
                if (d.length() == c && maxCount[c] > 1 && maxCount[c] == tmp.get(d)) {
                    answer.add(d);
                }
            }
        }

        Collections.sort(answer);
        return answer;
    }

    public void combination(int k, int r, boolean[] visit, int start, String value) {
        if (k == r) {
            char[] result = new char[r];
            int idx = 0;
            for(int i = 0; i < value.length(); i++) {
                if (visit[i]) {
                    result[idx++] = value.charAt(i);
                }
            }
            Arrays.sort(result);
            data.add(String.valueOf(result));
            return;
        }

        for(int i = start; i < value.length(); i++) {
            if (visit[i]) continue;
            visit[i] = true;
            combination(k+1, r, visit, i, value);
            visit[i] = false;
        }
    }
}