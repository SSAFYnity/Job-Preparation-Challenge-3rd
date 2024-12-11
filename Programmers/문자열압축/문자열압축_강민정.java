class Solution {
    public int solution(String s) {
        int answer = s.length();
        int sLen = s.length();
        StringBuilder sb = new StringBuilder();
        int idx = 0;    // 최근 시작점 인덱스

        for(int size=1; size<=sLen/2; size++) {     // size만큼 앞에서부터 자르기
            String prev = "";
            int cnt = 1;        // 압축 횟수
            for(int start=0; start<sLen; start+=size) {
                if(start + size > sLen) {
                    break;
                }
                idx = start + size;
                String cur = s.substring(start, start + size);
                if(prev.equals(cur)) {      // 같은 문자를 찾으면
                    cnt++;
                } else {
                    if(cnt > 1) {       // 압축할 수 있다면
                        sb.append(cnt + prev);
                    } else {
                        sb.append(prev);
                    }
                    cnt = 1;        // 연속으로 같은 문자 카운팅 변수 초기화
                    prev = cur;
                }
            }
            
            if(cnt > 1) {   // 마지막 문자열이 압축할 수 있는 경우
                sb.append(cnt + prev);
                // size보다 작은 문자열 ex) 3개씩 앞에서부터 잘랐는데 그 이하로 남았을 때
                sb.append(s.substring(idx, sLen));      
            } else {        // 압축을 못 하는 문자열인 경우
                sb.append(prev);
                // size보다 작은 문자열 ex) 3개씩 앞에서부터 잘랐는데 그 이하로 남았을 때
                sb.append(s.substring(idx, sLen));      
            }
            answer = Math.min(answer, sb.length());
            sb = new StringBuilder();
        }
        return answer;
    }
}