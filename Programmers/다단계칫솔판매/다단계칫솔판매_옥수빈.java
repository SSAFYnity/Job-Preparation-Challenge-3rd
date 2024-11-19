import java.util.*;

class Solution {
    public class Person {
        String name;
        String parent;
        int parentIdx;

        public Person(String name, String parent, int parentIdx) {
            this.name = name;
            this.parent = parent;
            this.parentIdx = 0;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> profitMap = new HashMap<>();
        for (String name : enroll) {
            profitMap.put(name, 0);
        }
        Map<String, Integer> nameToIdx = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            nameToIdx.put(enroll[i], i);
        }
        nameToIdx.put("-", -1);

        Person[] people = new Person[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            int pIdx = nameToIdx.get(referral[i]);
            people[i] = new Person(enroll[i], referral[i], pIdx);
        }

        for (int i = 0; i < seller.length; i++) {
            String sellerName = seller[i];
            int profit = amount[i] * 100;
            while (true) {
                if(profit == 0)
                    break;
                int sellerIdx = nameToIdx.get(sellerName);
                Person person = people[sellerIdx];
                int interest = profit / 10;
                profit -= interest;
                profitMap.put(sellerName, profitMap.get(sellerName) + profit);
                if (person.parent.equals("-"))
                    break;
                profit = interest;
                sellerName = person.parent;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profitMap.get(enroll[i]);
        }

        return answer;
    }
}