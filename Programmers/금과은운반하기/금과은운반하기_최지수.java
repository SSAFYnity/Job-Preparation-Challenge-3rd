import java.util.*;

class Solution {
    
    static int n;
    
    boolean fin(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        long jewel = 0;
        long gold = 0;
        long silver = 0;
        
        for (int i = 0; i < n; i++) {
            long cnt = time / 2 / t[i];
            if (time - (cnt * 2 * t[i]) >= t[i]) {cnt++;}
            jewel += Math.min(cnt * w[i], g[i]+s[i]);
            long one = Math.min(Math.min(a, g[i]), w[i] * cnt);
            gold += one;
            long two = Math.min(Math.min(b, s[i]), w[i] * cnt);
            silver += two;
        }
        return jewel >= a+b && gold >= a && silver >= b;
    }
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        if (a == b && a == 0) {return 0;}
        n = t.length;
        
        long start = 0;
        long end = 400000000000000L;
        long p;
        while (start <= end && end <= 400000000000000L) {
            p = (start + end) / 2;
            if (fin(a, b, g, s, w, t, p)) {
                end = p - 1;
            } else {start = p + 1;}
        }
        
        
        return start;
    }
}