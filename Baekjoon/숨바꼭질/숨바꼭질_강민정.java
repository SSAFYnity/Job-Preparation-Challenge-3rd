import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    final static int MAX_POS = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 수빈이가 있는 위치
        int k = sc.nextInt();   // 동생이 있는 위치

        if(n == k) {
            System.out.println(0);
            return;
        }

        boolean[] visit = new boolean[MAX_POS + 1];
        Queue<Integer> que = new LinkedList();
        int answer = 0;
        que.add(n);
        visit[n] = true;
        while(true) {
            int queSize = que.size();
            answer++;
            for(int i=0; i<queSize; i++) {
                int cur = que.poll();
                if (cur + 1 == k || cur - 1 == k || cur * 2 == k) {
                    System.out.println(answer);
                    return;
                }
                if (cur + 1 <= MAX_POS && !visit[cur + 1]) {
                    visit[cur + 1] = true;
                    que.add(cur + 1);
                }
                if (cur - 1 >= 0 && !visit[cur - 1]) {
                    visit[cur - 1] = true;
                    que.add(cur - 1);
                }
                if (cur * 2 <= MAX_POS && !visit[cur * 2]) {
                    visit[cur * 2] = true;
                    que.add(cur * 2);
                }
            }
        }
    }
}