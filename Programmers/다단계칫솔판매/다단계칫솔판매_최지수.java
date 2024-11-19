import java.util.*;

class Solution {
    
    class Person {
        String name;
        Person parent;
        int wallet;
        
        public Person(String name, Person parent, int wallet) {
            this.name = name;
            this.parent = parent;
            this.wallet = wallet;
        }
        
        public void earn(int money) {
            this.wallet += (money - money/10);
            if (parent != null && money/10 > 0) this.parent.earn(money/10);
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        Map<String, Person> sellerIndexMap = new HashMap<>();
        for (String p : enroll) {
            sellerIndexMap.put(p, new Person(p, null, 0));
        }
        
        for (int i = 0; i < referral.length; i++) {
            if (!referral[i].equals("-")) {
                sellerIndexMap.get(enroll[i]).parent = sellerIndexMap.get(referral[i]);
            }
        }
        
        
        for (int i = 0; i < seller.length; i++) {
            sellerIndexMap.get(seller[i]).earn(amount[i]*100);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = sellerIndexMap.get(enroll[i]).wallet;
        }
        
        return answer;
    }
}