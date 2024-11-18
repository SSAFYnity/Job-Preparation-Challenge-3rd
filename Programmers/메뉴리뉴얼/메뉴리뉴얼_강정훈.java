import java.util.*;
class Solution {
    private Map<Integer, Map<String, Integer>> courseCandidates;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        courseCandidates = new HashMap<>();
        for (int num :course) {
            courseCandidates.put(num, new HashMap<>());
        }

        for (String order : orders) {
            char[] orderCharArr = order.toCharArray();
            Arrays.sort(orderCharArr);
            for (int num: course) {
                executeCombinations(orderCharArr, new boolean[order.length()], new StringBuilder(), 0, num);
            }

        }

        for (int num : course) {
            int maxCount = 2;
            Map<String, Integer> courseCandidate = courseCandidates.get(num);
            List<String> answerCandidateList = new ArrayList<>();
            for (String menu : courseCandidate.keySet()) {
                int menuCount = courseCandidate.get(menu);
                if (menuCount > maxCount) {
                    maxCount = menuCount;
                    answerCandidateList = new ArrayList<>();
                    answerCandidateList.add(menu);
                } else if (menuCount == maxCount) {
                    answerCandidateList.add(menu);
                }
            }
            answer.addAll(answerCandidateList);
        }

        int size = answer.size();
        String[] result = new String[size];
        for (int index = 0; index < size; index++) {
            result[index] = answer.get(index);
        }
        Arrays.sort(result);
        return result;
    }

    private void executeCombinations(char[] orderCharArr, boolean[] visited, StringBuilder sb, int start, int num) {
        int size = orderCharArr.length;
        if (num == sb.length()) {
            setCourseCandidates(num, sb);
            return;
        }
        for (int index = start; index < size; index++) {
            if (visited[index]) {
                continue;
            }
            StringBuilder newSB = new StringBuilder(sb);
            newSB.append(orderCharArr[index]);
            visited[index] = true;
            executeCombinations(orderCharArr, visited, newSB, index+1, num);
            visited[index] = false;
        }
    }

    private void setCourseCandidates(int num, StringBuilder sb) {
        Map<String, Integer> courseCandidate = courseCandidates.get(num);
        String course = sb.toString();
        if (courseCandidate.containsKey(course)) {
            courseCandidate.put(course, courseCandidate.get(course)+1);
        } else {
            courseCandidate.put(course, 1);
        }
    }

}