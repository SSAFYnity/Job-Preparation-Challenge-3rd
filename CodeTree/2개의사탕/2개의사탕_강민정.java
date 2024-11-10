import java.util.Scanner;

public class Main {
    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    static int n;   // 세로
    static int m;   // 가로
    static Position redCandy;
    static Position blueCandy;
    static String[] arr;
    static int answer = 11;     // 기울여야 하는 최소 횟수

    public static void dfs(int depth) {
        if(depth > 10) {
            return;
        } else if(arr[redCandy.x].charAt(redCandy.y) == 'O' && arr[blueCandy.x].charAt(blueCandy.y) != 'O') {
            if(answer > depth) {
                answer = depth;     // 기울여야 하는 최소 횟수 갱신
            }
            return;
        } else if(arr[blueCandy.x].charAt(blueCandy.y) == 'O') {   // 파란 사탕이 먼저 빠지면
            return;
        }

        for(int d=0; d<4; d++) {
            Position originRed = redCandy;
            Position originBlue = blueCandy;
            play(d);
            dfs(depth + 1);
            redCandy = originRed;
            blueCandy = originBlue;
        }
    }

    public static void play(int d) {
        if(isRedCandyFirst(d)) {        // 빨간색 사탕을 먼저 움직이기
            if(isRedCandyGo(d)) {
                redCandy = move(redCandy, d, 1);
            }
            if(isBlueCandyGo(d)) {
                blueCandy = move(blueCandy, d, 0);
            }
        } else {    // 파란색 사탕을 먼저 움직이기
            if(isBlueCandyGo(d)) {
                blueCandy = move(blueCandy, d, 0);
            }
            if(isRedCandyGo(d)) {
                redCandy = move(redCandy, d, 1);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean isRedCandyGo(int d) {
        int nx = redCandy.x + dx[d];
        int ny = redCandy.y + dy[d];
        if(!isValid(nx, ny)) {
            return false;
        } else if(arr[nx].charAt(ny) == '#') {      // 장애물
            return false;
        } else if(blueCandy.x == nx && blueCandy.y == ny && arr[nx].charAt(ny) != 'O') {     // 파란사탕이 있어서 지나가지 못한다면
            return false;
        }
        return true;
    }

    public static boolean isBlueCandyGo(int d) {
        int nx = blueCandy.x + dx[d];
        int ny = blueCandy.y + dy[d];
        if(!isValid(nx, ny)) {
            return false;
        } else if(arr[nx].charAt(ny) == '#') {      // 장애물
            return false;
        } else if(redCandy.x == nx && redCandy.y == ny && arr[nx].charAt(ny) != 'O') {     // 빨간 사탕이 있어서 지나가지 못한다면
            return false;
        }
        return true;
    }

    /*
        빨간 사탕부터 움직여야한다면 true 반환
        d: 방향
    */
    public static boolean isRedCandyFirst(int d) {
        int redX = redCandy.x;
        int redY = redCandy.y;
        int blueX = blueCandy.x;
        int blueY = blueCandy.y;

        if(d == 0 && redY == blueY && redX < blueX) {        // 상
            return true;
        } else if(d == 1 && redY == blueY && redX > blueX) {    // 하
            return true;
        } else if(d == 2 && redX == blueX && redY < blueY) {    // 좌
            return true;
        } else if(d == 3 && redX == blueX && redY > blueY) {    // 우
            return true;
        }
        return false;       // 파란색 사탕이 먼저 움직여야 함
    }

    /*
        kind: 0이 파란 사탕
    */
    public static Position move(Position candy, int d, int kind) {
        int nx = candy.x + dx[d];
        int ny = candy.y + dy[d];
        while(0 <= nx && nx < n && 0 <= ny && ny < m) {
            if(arr[nx].charAt(ny) == '#') {      // 장애물이 있으면
                nx -= dx[d];
                ny -= dy[d];
                break;
            } else if(arr[nx].charAt(ny) == 'O') {       // 출구를 발견했으면
                break;
            } else if(kind == 0 && redCandy.x == nx && redCandy.y == ny) {       // 빨간 사탕에 부딪히면
                nx -= dx[d];
                ny -= dy[d];
                break;
            } else if(kind == 1 && blueCandy.x == nx && blueCandy.y == ny) {     // 파란 사탕에 부딪히면
                nx -= dx[d];
                ny -= dy[d];
                break;
            }
            nx += dx[d];
            ny += dy[d];
        }
        return new Position(nx, ny);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();   // 세로
        m = sc.nextInt();   // 가로
        arr = new String[n];
        for(int i=0; i<n; i++) {
            String str = sc.next();
            arr[i] = str;
            for(int j=0; j<m; j++) {
                if(str.charAt(j) == 'R') {      // 빨간 사탕 발견
                    redCandy = new Position(i, j);
                } else if(str.charAt(j) == 'B') {   // 파란 사탕 발견
                    blueCandy = new Position(i, j);
                }
            }
        }

        dfs(0);
        if(answer == 11) {
            answer = -1;
        }
        System.out.println(answer);
    }
}