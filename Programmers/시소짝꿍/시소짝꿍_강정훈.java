import java.util.*;
class Solution {
    /**
     * countMap: 특정 무게의 개수를 저장하는 Map
     * Key: weight, Value: Count
     * visited: 자기와 같은 무게를 가진 사람이 이전에 나왔는지 체크하는 용도
     */
    private Map<Integer, Integer> countMap;
    private Set<Integer> visited;
    public long solution(int[] weights) {
        long answer = 0;
        countMap = new HashMap<>();
        visited = new HashSet<>();
        setCountMap(weights);

        for (int weight: weights) {
            if(visited.contains(weight)) {
                continue;
            }
            int currentWeightCount = countMap.get(weight);
            answer += combinations((long) currentWeightCount);
            visited.add(weight);

            //target후보는 weight의 1.5배, 2배, 그리고 4/3배
            int target;
            if (weight % 2 == 0) {
                target = weight*3/2;
                answer += addAnswer(target, currentWeightCount);
            }
            if (weight % 3 == 0) {
                target = weight*4/3;
                answer += addAnswer(target, currentWeightCount);
            }
            target = weight*2;
            answer += addAnswer(target, currentWeightCount);
        }
        return answer;
    }

    private long addAnswer(int target, int currentWeightCount) {
        if (countMap.containsKey(target)){
            return (long) currentWeightCount * countMap.get(target);
        }
        return 0L;
    }

    private void setCountMap(int[] weights) {
        for (int weight : weights) {
            if (countMap.containsKey(weight)) {
                countMap.put(weight, countMap.get(weight)+1);
                continue;
            }
            countMap.put(weight, 1);
        }
    }

    private long combinations(long n) {
        return n*(n-1L) / 2L;
    }
}