import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main
{
    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] arr = new String[n][n];
        String answer = "YES";
        List<Position> teachers = new ArrayList();
        boolean[][] visit = new boolean[n][n];
        int teacherCnt = 0;
        int obstacles = 0;      // 장애물의 수
        // 복도의 정보 입력받기
        for(int i=0; i<n; i++) {
            // 학생이 있으면 S, 선생님이 있으면 T, 아무것도 없으면 X
            for(int j=0; j<n; j++) {
                arr[i][j] = sc.next();
                if(arr[i][j].equals("T")) {     // 선생님이라면
                    teachers.add(new Position(i, j));
                    teacherCnt++;
                }
            }
        }

        for(int i=0; i<teacherCnt; i++) {
            Position teacher = teachers.get(i);
            for(int d=0; d<4; d++) {
                int nx = dx[d] + teacher.x;
                int ny = dy[d] + teacher.y;
                boolean flag = false;   // 현재 방향에서 감시할 학생을 찾았는지 여부
                int cnt = 0;        // 현재 방향으로 탐색 횟수
                while(0 <= nx && 0 <= ny && nx < n && ny < n) {
                    if(arr[nx][ny].equals("S")) {       // 학생을 발견했다면
                        if(cnt == 0) {      // 선생님과 학생이 붙어있으면 장애물 설치 불가
                            answer = "NO";
                            //break;
                        }
                        flag = true;
                        obstacles++;
                        break;
                    }
                    if(visit[nx][ny]) {     // 장애물을 설치간 구간이면 이 방향으로 더이상 탐색하지 않아도 됨
                        break;
                    }
                    nx += dx[d];
                    ny += dy[d];
                    cnt++;
                }

                // 감시할 학생이 있으면 방문 처리를 해주어 다른 선생님이 탐색하지 않아도 되도록 처리
                if(flag) {
                    nx = dx[d] + teacher.x;
                    ny = dy[d] + teacher.y;
                    while(0 <= nx && 0 <= ny && nx < n && ny < n) {
                        visit[nx][ny] = true;
                        nx += dx[d];
                        ny += dy[d];
                    }
                }
            }
        }

        // 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피할 수 있는지 여부를 출력
        if(obstacles > 3) {
            answer = "NO";
        }
        System.out.println(answer);
    }
}