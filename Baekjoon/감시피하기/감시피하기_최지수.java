import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static boolean ok;
    static boolean[][] check;
    static List<List<int[]>> everyX;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};

    static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    static void back(int p, int cnt) {
        if (cnt > 3) return;
        if (p >= everyX.size()) {
            ok = true;
            return;
        }

        for (int i = 0; i < everyX.get(p).size(); i++) {
            int y = everyX.get(p).get(i)[0];
            int x = everyX.get(p).get(i)[1];
            if (check[y][x]) {
                back(p+1, cnt);
            } else {
                check[y][x] = true;
                back(p+1, cnt+1);
                check[y][x] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        char[][] aisle = new char[n][n];
        List<int[]> tlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                aisle[i][j] = st.nextToken().charAt(0);
                if (aisle[i][j] == 'T') tlist.add(new int[] {i,j});
            }
        }

        /*
        T 시야(좌우상하 두줄) 돌면서 S가 있으면 S와 T 사이 존재하는 모든 X에 O 대입해보기
        모든 사이에 다 껴보고 boolean 맵에 표시해서 바뀌면 cnt++ <- 3 초과하면 실패
        성공하면 바로 YES 출력하고 끝내기
        다 돌고 나왔다면 실패한거니까 NO
         */

        everyX = new ArrayList<>();
        for (int tidx = 0; tidx < tlist.size(); tidx++) {
            int[] t = tlist.get(tidx);


            for (int i = 0; i < 4; i++) { // 바로 눈 앞이면 실패
                if (isOut(t[0]+dr[0][i], t[1]+dr[1][i]) || aisle[t[0]+dr[0][i]][t[1]+dr[1][i]] != 'S') continue;
                System.out.println("NO");
                return;
            }

            boolean[] keep = new boolean[4];
            for (int i = 2; i < n; i++) {
                if (!keep[0] && !isOut(t[0]-i, t[1]) && aisle[t[0]-i][t[1]] == 'S') {
                    everyX.add(new ArrayList<>());
                    for (int j = t[0]-1; j > t[0]-i; j--) {
                        everyX.get(everyX.size()-1).add(new int[] {j,t[1]});
                    }
                    keep[0] = true;
                }
                if (!keep[1] && !isOut(t[0]+i, t[1]) && aisle[t[0]+i][t[1]] == 'S') {
                    everyX.add(new ArrayList<>());
                    for (int j = t[0]+1; j < t[0]+i; j++) {
                        everyX.get(everyX.size()-1).add(new int[] {j,t[1]});
                    }
                    keep[1] = true;
                }
                if (!keep[2] && !isOut(t[0], t[1]-i) && aisle[t[0]][t[1]-i] == 'S') {
                    everyX.add(new ArrayList<>());
                    for (int j = t[1]-1; j > t[1]-i; j--) {
                        everyX.get(everyX.size()-1).add(new int[] {t[0],j});
                    }
                    keep[2] = true;
                }
                if (!keep[3] && !isOut(t[0], t[1]+i) && aisle[t[0]][t[1]+i] == 'S') {
                    everyX.add(new ArrayList<>());
                    for (int j = t[1]+1; j < t[1]+i; j++) {
                        everyX.get(everyX.size()-1).add(new int[] {t[0],j});
                    }
                    keep[3] = true;
                }

                if (keep[0] && keep[1] && keep[2] && keep[3]) break;
            }
        }

//        for (List<int[]> l : everyX) {
//            for (int[] i : l) {
//                System.out.print(Arrays.toString(i));
//            }
//            System.out.println();
//        }

        check = new boolean[n][n];

        back(0, 0);
        System.out.println(ok ? "YES" : "NO");
    }
}

