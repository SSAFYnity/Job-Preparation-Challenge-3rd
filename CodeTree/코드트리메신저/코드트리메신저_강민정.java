import java.io.*;
import java.util.StringTokenizer;

public class Main {
    final static int MAX_N = 100001;

    static int n;       // 채팅방의 수
    static int q;       // 명령의 수
    static int[] parents = new int[MAX_N];   // 부모 채팅방 번호
    static int[] authorities = new int[MAX_N];       // 권한
    static boolean[] isNotification = new boolean[MAX_N];   // 알림망 설정
    static int answer;

    public static void settingNotification(int c) {
        if(isNotification[c]) {
            isNotification[c] = false;
        } else {
            isNotification[c] = true;
        }
    }

    public static void changeAuthority(int c, int power) {
        authorities[c] = power;
    }

    public static void changeParent(int c1, int c2) {
        int tmp = parents[c1];
        parents[c1] = parents[c2];
        parents[c2] = tmp;
    }

    public static void dfs(int cur, int depth) {
        if(depth != 0 && isNotification[cur]) {      // 알림이 차단되어 있다면
            return;
        } /*else if(authorities[cur] < depth) {
            return;
        }*/ else if(depth != 0 && authorities[cur] >= depth) {
            answer++;
        }

        for(int i=1; i<=n; i++) {
            if(parents[i] == cur) {     // i번 노드의 부모 노드가 cur이 맞다면
                dfs(i, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        st.nextToken();     // 100 명령어
        for(int i=1; i<=n; i++) {
            parents[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++) {
            authorities[i] = Integer.parseInt(st.nextToken());
        }

        q--;

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("200")) {     // 알림망 설정 on/off
                settingNotification(Integer.parseInt(st.nextToken()));   // 채팅방 번호
            } else if(cmd.equals("300")) {      // 권한 세기 변경
                changeAuthority(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else if(cmd.equals("400")) {      // 부모 채팅방 교환
                changeParent(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {        // 알림을 받을 수 있는 채팅방 수 조회
                dfs(Integer.parseInt(st.nextToken()), 0);
                bw.write(answer + "\n");
                answer = 0;     // 초기화
            }
        }
        bw.flush();
    }
}