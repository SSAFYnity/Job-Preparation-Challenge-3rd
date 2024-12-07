import java.io.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Direction extends Position {
        int d;

        Direction(int x, int y, int d) {
            super(x, y);
            this.d = d;
        }
    }

    static int n;       // 격자 크기
    static int m;       // 도망자의 수
    static int h;       // 나무의 수
    static int k;       // 턴 수
    static List<Integer>[][] hider;      // 도망자가 바라보는 방향 저장
    static List<Integer>[][] newhider;   // 도망자가 움직였을 때
    static boolean[][] tree;
    static int answer;      // 술래의 총 점수
    static Position monster;   // 술래
    static List<Direction> snail;    // 술래가 나선형으로 움직였을 때 위치 좌표들
    static List<Direction> reversedSnail;       // 술래가 중앙을 향해 나선형으로 움직였을 때 위치 좌표들
    static int snailIdx;
    static boolean isGo;        // 중앙에서 출발했는지
    // 센터 좌표
    static int CENTER_X;
    static int CENTER_Y;

    // 상우하좌
    final static int[] DX = {-1, 0, 1, 0};
    final static int[] DY = {0, 1, 0, -1};

    public static int manhattanDistance(int x, int y) {
        return Math.abs(monster.x - x) + Math.abs(monster.y - y);
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void initSnail(int curX, int curY) {
        int d = 1;  // 이동 거리
        int idx = 0;
        int cnt = 0;
        boolean flag = true;
        snail.add(new Direction(curX, curY, idx));
        reversedSnail.add(new Direction(curX, curY, (idx + 2) % 4));

        while(flag) {
            for(int i=0; i<d; i++) {
                curX += DX[idx];
                curY += DY[idx];
                if(snail.size() == n*n) {
                    flag = false;
                    break;
                }
                snail.add(new Direction(curX, curY, idx));
                reversedSnail.add(new Direction(curX, curY, (idx + 2) % 4));
            }
            cnt++;
            reversedSnail.get(reversedSnail.size() - 1).d = (idx + 2) % 4;
            idx = (idx + 1) % 4; // 방향 변경
            snail.get(snail.size() - 1).d = idx;
            if(cnt == 2) {
                d++;        // 이동 거리 증가
                cnt = 0;
            }
        }
    }

    /*
        술래가 움직인다
    */
    public static void monsterMove() {
        // 끝에 도달했다면
        if(monster.x == 0 && monster.y == 0) {
            // 다시 거꾸로 중심으로 이동
            isGo = false;
        } else if(monster.x == CENTER_X && monster.y == CENTER_Y) {   // 중앙에 도달하면
            // 끝을 향해 이동
            isGo = true;
        }

        //snailIdx = (snailIdx + 1) % (n * n);
        // 끝을 향해 이동
        /*if(isGo) {
            snailIdx++;
        } else {    // 중앙을 향해 이동
            snailIdx--;
        }*/

        //Direction tmp = snail.get(snailIdx);
        if(isGo) {
            snailIdx++;
            monster = new Position(snail.get(snailIdx).x, snail.get(snailIdx).y);
        } else {
            snailIdx--;
            monster = new Position(reversedSnail.get(snailIdx).x, reversedSnail.get(snailIdx).y);
        }
    }

    /*
        술래에게 잡혀서 도망자가 죽는다
     */
    public static int kill(int nx, int ny) {
        // 유효하지 않은 위치이거나 나무가 있다면
        if(!isValid(nx, ny) || tree[nx][ny] == true) {
            return 0;
        }
        // 도망자를 찾았다면
        int cnt = hider[nx][ny].size();
        hider[nx][ny].clear();
        return cnt;
    }

    /*
        술래가 도망자를 잡는다
    */
    public static int gotcha() {
        int cnt = 0;        // 잡은 도망자 수
        // 현재 있는 칸을 포함하여 3칸을 내다보기
        int curD;
        if(isGo) {
            curD = snail.get(snailIdx).d;       // 현재 바라보는 방향
        } else {
            curD = reversedSnail.get(snailIdx).d;
        }
        int curX = monster.x;
        int curY = monster.y;

        cnt += kill(curX, curY);

        for(int i=0; i<2; i++) {
            int nx = DX[curD] + curX;
            int ny = DY[curD] + curY;
            cnt += kill(nx, ny);
            curX = nx;
            curY = ny;
        }
        return cnt;
    }

    /*
        한 명의 도망자가 움직인다
     */
    public static void run(int i, int j, int d) {
        int nx = DX[d] + i;
        int ny = DY[d] + j;
        // 격자를 벗어난 경우
        if(!isValid(nx, ny)) {
            // 반대 방향으로 튼다
            d = (d + 2) % 4;
            nx = i + DX[d];
            ny = j + DY[d];
        }
        // 술래가 있으면
        if(monster.x == nx && monster.y == ny) {
            newhider[i][j].add(d);
        } else {
            newhider[nx][ny].add(d);
        }
    }

    /*
        도망자들이 움직인다
    */
    public static void runAll() {
        // 도망자가 움직였을 때 저장하는 리스트 배열 초기화
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                newhider[i][j] = new ArrayList();
            }
        }

        for(int i=0; i<n; i++) {        // 행
            for(int j=0; j<n; j++) {    // 열
                List<Integer> tmp = hider[i][j];
                for(int d : tmp) {
                    // 움직이지 않아도 되는 도망자라면
                    if(manhattanDistance(i, j) > 3) {
                        newhider[i][j].add(d);      // 현재 위치 그대로 넣기
                    } else {
                        run(i, j, d);
                    }
                }
            }
        }

        // 기존 리스트 배열 갱신
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                hider[i][j] = new ArrayList(newhider[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        CENTER_X = n / 2;
        CENTER_Y = n / 2;
        monster = new Position(CENTER_X, CENTER_Y);   // 정가운데 위치
        snail = new ArrayList();
        reversedSnail = new ArrayList();

        // 도망자의 위치와 이동 방법을 입력 받기
        hider = new ArrayList[n][n];
        newhider = new ArrayList[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                hider[i][j] = new ArrayList();
            }
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            hider[x][y].add(d);
        }

        // 나무의 위치 입력 받기
        tree = new boolean[n][n];
        for(int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            tree[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }

        initSnail(monster.x, monster.y);
        isGo = true;        // 중앙에서 출발한다

        for(int turn=1; turn<=k; turn++) {
            // 도망자가 움직인다
            runAll();
            // 몬스터가 움직인다
            monsterMove();
            // 도망자를 잡는다
            int cnt = gotcha();
            answer += turn * cnt;
        }

        System.out.println(answer);
    }
}