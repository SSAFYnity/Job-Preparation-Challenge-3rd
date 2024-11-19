import java.util.*;

class Person{
    int num, parent, get;
    public Person(int num){
        this.num = num;
        this.parent = -1;
        this.get = 0;
    }
}

class Solution {
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String,Integer> map = new HashMap<>();
        Person[] person = new Person[enroll.length];
        
        // key: 이름 - value: num
        for(int num=0; num<enroll.length; num++){
            map.put(enroll[num],num);
            person[num] = new Person(num);
        }
        
        // 추천인
        for(int i=0; i<referral.length; i++){
            String parent = referral[i];
            if(parent.equals("-")) continue;
            
            int pNum = map.get(parent);
            person[i].parent = pNum;
        }
        
        // 판매한 사람
        for(int i=0; i<seller.length; i++){
            String s = seller[i];
            int sNum = map.get(s);
            int cnt = amount[i];
            int w = 100*cnt;
            
            Person p = person[sNum];
            p.get += w;
            while(true){
                int ww = w/10; // 10%
                
                // 10%가 1원 미만이면 분배 X
                if(ww==0) break;
                
                // 부모가 center 이면
                if(p.parent==-1){
                    p.get -= ww; // 10%만 빼주기
                    break;
                }
                
                p.get -= ww; // 10% 빼고
                person[p.parent].get += ww; // 10% 더하고
                w = ww; // 값 갱신
                p = person[p.parent]; // person 갱신
            }

        }
        
        // 수익금 나열
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++){
            answer[i] = person[i].get;
        }
        
        return answer;
    }
}
