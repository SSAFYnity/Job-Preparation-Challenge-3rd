import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final int MAX_N = 100001;	// 채팅방의 최대 크기
    public static final int MAX_D = 22;		// 최대 권한의 크기

    public static int n, q;		// 채팅방의 수, 명령의 수
    public static int[] a = new int[MAX_N];		// 권한
    public static int[] p = new int[MAX_N];		// 부모
    public static int[] val = new int[MAX_N];	// 알림을 받을 수 있는 채팅방 수
    public static boolean[] noti = new boolean[MAX_N];	// 알림 수신 여부
    public static int[][] nx = new int[MAX_N][MAX_D];

    // 초기 설정 값을 받아옵니다.
    public static void init(Scanner sc) {
        // 부모 채팅과 채팅의 권한 정보를 입력받습니다.
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            // 채팅의 권한이 20을 초과하는 경우 20으로 제한합니다.
            if (a[i] > 20) a[i] = 20;
        }

        // nx 배열과 val 값을 초기화합니다.
        for (int i = 1; i <= n; i++) {	// 채팅방의 수만큼 반복
            int cur = i;	// 현재 채팅방 번호
            int x = a[i];	// 현재 채팅방 권한 크기
            nx[cur][x]++;	// 현재 채팅방의 권한의 영향을 받는 채팅방 수 증가
            // 상위 채팅으로 이동하며 nx와 val 값을 갱신합니다.
            while (p[cur] != 0 && x != 0) {	// 루트 노드가 아니고, 권한이 있을 때까지
                cur = p[cur];	// 현재 노드를 부모 노드로 갱신
                x--;	// 권한 크기 1 감소
                if (x != 0) nx[cur][x]++;	// 권한이 있을 때 채팅방 수 증가
                val[cur]++;		// 알림을 받을 수 있는 채팅방 수 증가
            }
        }
    }

    // 채팅의 알림 상태를 토글합니다.
    public static void toggle_noti(int chat) {
        if (noti[chat]) {	// 알림이 켜져있다면
            int cur = p[chat];	// 부모 노드
            int num = 1;	// depth
            // 상위 채팅으로 이동하며 noti 값에 따라 nx와 val 값을 갱신합니다.
            while (cur != 0) {	// 루트 노드가 아닐때까지
                for (int i = num; i <= 21; i++) {	// 권한 크기만큼 반복
                    val[cur] += nx[chat][i];	// 알림을 받을 수 있는 채팅방의 수 더하기
                    if (i > num) nx[cur][i - num] += nx[chat][i];	// 권한이 유효하면
                }
                if (noti[cur]) break;	// 알림이 켜져있으면
                cur = p[cur];	// 현재 노드를 부모 노드로 갱신
                num++;	// depth 증가
            }
            noti[chat] = false;		// 채팅방의 알림 끄기
        } else {		// 알림이 꺼져있다면
            int cur = p[chat];		// 부모 노드
            int num = 1;	// depth
            // 상위 채팅으로 이동하며 noti 값에 따라 nx와 val 값을 갱신합니다.
            while (cur != 0) {		// 루트 노드가 아닐 동안 반복
                for (int i = num; i <= 21; i++) {	// 권한 크기만큼 반복
                    val[cur] -= nx[chat][i];	// 알림을 받을 수 있는 채팅방의 수 차감
                    if (i > num) nx[cur][i - num] -= nx[chat][i];	// 권한이 유효하면
                }
                if (noti[cur]) break;		// 알림을 받는다면
                cur = p[cur];	// 부모 노드 번호로 갱신
                num++;	// depth 증가
            }
            noti[chat] = true;	// 알림 켜기
        }
    }

    // 채팅의 권한의 크기를 변경합니다.
    public static void change_power(int chat, int power) {
        int bef_power = a[chat];	// 현재 권한 크기
        power = Math.min(power, 20);  // 권한의 크기를 20으로 제한합니다.
        a[chat] = power;	// 권한 크기 변경

        nx[chat][bef_power]--;
        if (!noti[chat]) {		// 채팅방에 알림이 꺼져있다면
            int cur = p[chat];	// 부모 노드
            int num = 1;	// depth
            // 상위 채팅으로 이동하며 nx와 val 값을 갱신합니다.
            while (cur != 0) {	// 루트 노드가 아닐 동안
                if (bef_power >= num) val[cur]--;	// 현재 권한 크기 depth와 크거나 같으면
                if (bef_power > num) nx[cur][bef_power - num]--;	// 현재 권한 크기가 depth보다 크면
                if (noti[cur]) break;	// 알림이 켜져있으면
                cur = p[cur];	// 부모 노드로 갱신
                num++;	// depth 증가
            }
        }

        nx[chat][power]++;	// 현재 채팅방에서 현재 권한에 있는 채팅방 수 증가
        if (!noti[chat]) {		// 알림이 꺼져있다면
            int cur = p[chat];		// 부모 노드 번호
            int num = 1;	// depth
            // 상위 채팅으로 이동하며 nx와 val 값을 갱신합니다.
            while (cur != 0) {		// 루트 노드가 아닐 동안에
                if (power >= num) val[cur]++;	// 권한이 depth보다 크거나 같으면
                if (power > num) nx[cur][power - num]++;	// 권한이 depth보다 크다면
                if (noti[cur]) break;	// 알림이 켜져있으면
                cur = p[cur];	// 부모 노드 갱신
                num++;		// depth 증가
            }
        }
    }

    // 두 채팅의 부모를 교체합니다.
    public static void change_parent(int chat1, int chat2) {
        boolean bef_noti1 = noti[chat1];	// chat1의 알림 수신 여부
        boolean bef_noti2 = noti[chat2];	// chat2의 알림 수신 여부

        // 알람이 꺼져있으면 켜기
        if (!noti[chat1]) toggle_noti(chat1);
        if (!noti[chat2]) toggle_noti(chat2);

        // 부모 교환
        int temp = p[chat1];
        p[chat1] = p[chat2];
        p[chat2] = temp;

        // 원래 알람이 꺼져있었으면 켜기
        if (!bef_noti1) toggle_noti(chat1);
        if (!bef_noti2) toggle_noti(chat2);
    }

    // 해당 채팅의 val 값을 출력합니다.
    public static void print_count(int chat) {
        System.out.println(val[chat]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();	// 채팅방의 수
        q = sc.nextInt();	// 명령의 수

        while (q-- > 0) {
            int query = sc.nextInt();
            if (query == 100) {		// 사내 메신저 준비
                init(sc);
            } else if (query == 200) {		// 알림망 설정 on/off
                int chat = sc.nextInt();
                toggle_noti(chat);
            } else if (query == 300) {		// 권한 세기 변경
                int chat = sc.nextInt();
                int power = sc.nextInt();
                change_power(chat, power);
            } else if (query == 400) {		// 부모 채팅방 교환
                int chat1 = sc.nextInt();
                int chat2 = sc.nextInt();
                change_parent(chat1, chat2);
            } else if (query == 500) {	// 알림을 받을 수 있는 채팅방 수 조회
                int chat = sc.nextInt();
                print_count(chat);
            }
        }
    }
}