import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

class Solution {
    static int rowSize;
    static int colSize;
    static int[][] pos1;
    static int[][] pos2;
    static Set<Character> treeSet;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    public boolean isValid(int nx, int ny) {
        return 0 <= nx && nx < rowSize && 0 <= ny && ny < colSize;
    }

    /*
        한번 꺾기
    */
    public boolean removeTurnLine(int x, int y, char[][] boardArr) {
        for(int d=0; d<4; d++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;

            if(!isValid(nx, ny) || boardArr[nx][ny] == '*') {
                continue;
            }

            while(isValid(nx, ny) && boardArr[nx][ny] != '*' && boardArr[nx][ny] == '.') {
                // 위로 꺾기
                int topNx = nx;
                int topNy = ny;
                while(topNx >= 0) {
                    if(boardArr[topNx][topNy] == boardArr[x][y]) {
                        lst.add(boardArr[x][y]);
                        boardArr[topNx][topNy] = '.';
                        boardArr[x][y] = '.';
                        return true;
                    } else if(boardArr[topNx][topNy] != '.') {
                        break;
                    }
                    topNx--;
                }

                // 아래로 꺾기
                int bottomNx = nx;
                int bottomNy = ny;
                while(bottomNx < rowSize) {
                    if(boardArr[bottomNx][bottomNy] == boardArr[x][y]) {
                        lst.add(boardArr[x][y]);
                        boardArr[bottomNx][bottomNy] = '.';
                        boardArr[x][y] = '.';
                        return true;
                    } else if(boardArr[bottomNx][bottomNy] != '.') {
                        break;
                    }
                    bottomNx++;
                }

                nx += dx[d];
                ny += dy[d];
            }
        }
        return false;
    }

    /*
        수직 또는 수평으로 없애기
    */
    public boolean removeLine(int x, int y, char[][] boardArr) {
        for(int d=0; d<4; d++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;

            while(isValid(nx, ny)) {
                if(boardArr[nx][ny] == boardArr[x][y]) {      // 제거할 타일을 찾음
                    lst.add(boardArr[x][y]);
                    boardArr[x][y] = '.';
                    boardArr[nx][ny] = '.';
                    return true;
                }

                // 못 가는 칸이라면
                if(boardArr[nx][ny] != '.') {
                    break;
                }

                nx += dx[d];
                ny += dy[d];
            }
        }
        return false;
    }

    public String solution(int m, int n, String[] board) {
        StringBuilder answer = new StringBuilder();
        rowSize = m;
        colSize = n;
        char[][] boardArr = new char[m][n];
        pos1 = new int[26][2];
        pos2 = new int[26][2];
        treeSet = new TreeSet();

        // 배열 초기화
        for(int i=0; i<26; i++) {
            Arrays.fill(pos1[i], -1);
        }

        for(int x=0; x<m; x++) {        // 세로
            boardArr[x] = board[x].toCharArray();
            for(int y=0; y<n; y++) {        // 가로
                if(boardArr[x][y] != '.' && boardArr[x][y] != '*') {
                    int idx = boardArr[x][y] - 'A';
                    if (pos1[idx][0] == -1) {
                        pos1[idx][0] = x;
                        pos1[idx][1] = y;
                    } else {
                        pos2[idx][0] = x;
                        pos2[idx][1] = y;
                    }
                    treeSet.add(boardArr[x][y]);
                }
            }
        }

        while(true) {
            boolean isEnd = true;
            lst = new ArrayList();
            for(char ch : treeSet) {
                int x = pos1[ch - 'A'][0];
                int y = pos1[ch - 'A'][1];
                if (boardArr[x][y] == '.' || boardArr[x][y] == '*') {
                    continue;
                }
                if (removeLine(x, y, boardArr)) {
                    isEnd = false;
                    answer.append(ch);
                    treeSet.remove(ch);
                    break;
                } else if (removeTurnLine(x, y, boardArr)) {
                    isEnd = false;
                    answer.append(ch);
                    treeSet.remove(ch);
                    break;
                }
            }

            // 다 돌았는데 제거한 게 하나도 없다면
            if(isEnd) {
                break;
            }
        }

        // 다 제거했는지
        if(!treeSet.isEmpty()) {
            return "IMPOSSIBLE";
        }

        return answer.toString();
    }
}