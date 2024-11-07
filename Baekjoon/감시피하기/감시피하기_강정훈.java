import java.util.*;
import java.io.*;

public class 감시피하기_강정훈 {
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] maps = new String[N][N];
        for(int index = 0; index < N; index++) {
            String[] arr = br.readLine().split(" ");
            maps[index] = arr;
        }
        List<Node> candidateList = new ArrayList<>();
        List<int[]> teacherList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (maps[row][column].equals("X")) {
                    candidateList.add(new Node(row, column));
                } else if(maps[row][column].equals("T")) {
                    for (int index = 0; index < 4; index++) {
                        teacherList.add(new int[]{row, column, index});
                    }

                }
            }
        }
        int candidateSize = candidateList.size();
        boolean flag = true;
        // maps의 최고 넓이가 36이기 때문에, 커봐야 (36C3의 연산 * BFS 연산) 이 발생한다.
        for (int first = 0; first < candidateSize-2; first++) {
            Node firstIndex = candidateList.get(first);
            for (int second = first+1; second < candidateSize-1; second++) {
                Node secondIndex = candidateList.get(second);
                for (int third = second+1; third < candidateSize; third++) {
                    flag = true;
                    Node thirdIndex = candidateList.get(third);
                    Set<Node> obstacles = new HashSet<>();
                    obstacles.add(firstIndex);
                    obstacles.add(secondIndex);
                    obstacles.add(thirdIndex);
                    Queue<int[]> q = new ArrayDeque<>(teacherList);

                    // BFS 돌리기
                    while(!q.isEmpty()) {
                        int[] current = q.poll();
                        int nr = current[0] + dr[current[2]];
                        int nc = current[1] + dc[current[2]];
                        if (0 <= nr && nr < N && 0 <= nc && nc < N && !obstacles.contains(new Node(nr, nc))) {
                            if (maps[nr][nc].equals("S")) {
                                flag = false;
                                break;
                            }
                            q.add(new int[]{nr, nc, current[2]});
                        }
                    }
                    if (flag) {
                        System.out.println("YES");
                        return;
                    }
                }
            }
        }
        System.out.println("NO");
    }
}
// 해시 자료구조에서 동일성 확보하기 위한 클래스
class Node {
    int r;
    int c;
    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        return r == node.r && c == node.c;
    }
    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}