import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 감시피하기_이승환 {

    static class Node{
        int i;
        int j;

        public Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    static List<Node> student = new ArrayList<>();
    static int N;
    static String[][] school;
    static int[] di = {0,1,0,-1};
    static int[] dj = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        
        school = new String[N][N];


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                school[i][j] = st.nextToken();
                if(school[i][j].equals("S")){
                    student.add(new Node(i,j));
                }
            }
        }

        dfs(0);
        
        System.out.println("NO");

    }

    public static void dfs(int cnt){
        if(cnt == 3){
            bfs();
            return;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(school[i][j].equals("X")){
                    school[i][j] = "O";
                    dfs(cnt+1);
                    school[i][j] = "X";
                }
            }
        }
    }

    public static void bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(school[i][j].equals("T")){
                    q.add(new Node(i, j));
                    check[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int ci = curr.i;
            int cj = curr.j;

            for(int k=0;k<4;k++){
                int ni = ci + di[k];
                int nj = cj + dj[k];

                while(0 <= ni && ni < N && 0 <= nj && nj <N){
                    if (!school[ni][nj].equals("O")){
                        check[ni][nj] = true;
                        ni += di[k];
                        nj += dj[k];
                    } else{
                        break;
                    }
                }
            }
        }

        if(checkWatch(check)){
            System.out.println("YES");
            System.exit(0);
        }


    }

    public static boolean checkWatch(boolean[][] check){
        for(Node node : student){
            if(check[node.i][node.j] == true){
                return false;
            }
        }

        return true;
    }
}
