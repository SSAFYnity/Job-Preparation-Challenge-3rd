import java.util.*;

public class 다단계칫솔판매_김수빈 {
    static class Solution {
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int N = enroll.length;  // 총 판매원 수
            int M = seller.length;  // 총 실판매원 수

            HashMap<String, Integer> nameToIndex = new HashMap<>();  // 이름 인덱스 매핑
            for (int i = 0; i < N; i++) {
                nameToIndex.put(enroll[i], i);
            }

            int[] profits = new int[N];  // 판매원 이익
            int[] parents = new int[N];  // 추천인
            Arrays.fill(parents, -1);  // 추천인 없는 경우 -1
            for (int i = 0; i < N; i++) {
                if (!referral[i].equals("-")) {
                    parents[i] = nameToIndex.get(referral[i]);
                }
            }
            // 수익 계산
            for (int i = 0; i < M; i++) {
                int profit = amount[i] * 100;
                int personIndex = nameToIndex.get(seller[i]);

                while (personIndex != -1 && profit > 0) {
                    int fee = profit / 10;  // 수수료
                    profits[personIndex] += profit - fee;
                    profit = fee;
                    personIndex = parents[personIndex];
                }
            }
            return profits;
        }

        public static void main(String[] args) {
            String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};  // 판매원 이름
            String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};  // 추천인
            String[] seller = {"sam", "emily", "jaimie", "edward"};  // 판매자
            int[] amount = {2, 3, 5, 4};  // 판매 금액

            Solution sol = new Solution();
            int[] result = sol.solution(enroll, referral, seller, amount);
            System.out.println(Arrays.toString(result));

        }
    }
}
