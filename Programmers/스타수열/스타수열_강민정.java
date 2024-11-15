class Solution {
    public int solution(int[] a) {
        int answer = -1;
        int n = a.length;
        int[] arr = new int[n];

        if(n < 2) {
            return 0;
        }

        // 빈도 수 세기
        for(int i=0; i<n; i++) {
            arr[a[i]]++;
        }

        for(int i=0; i<n; i++) {
            if(arr[i] <= answer) {
                continue;
            }
            int cnt = 0;
            for(int j=0; j<n-1; j++) {
                if(a[j] != a[j + 1] && (i == a[j] || i == a[j + 1])) {
                    cnt++;
                    j++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        return answer * 2;
    }
}