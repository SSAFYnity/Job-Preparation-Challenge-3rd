import java.util.Scanner;

class 소수구하기_강은선 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        boolean[] map = new boolean[N + 1];

        int now = 2;
        while (now < Math.sqrt(N) + 1) {
            for (int i = now + 1; i < N + 1; i++) {
                if (i % now == 0) {
                    map[i] = true;
                }
            }

            now++;
            while (now < N + 1 && map[now]) {
                now++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = Math.max(2, M); i < N + 1; i++) {
            if (!map[i]) {
                sb.append(i);
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}