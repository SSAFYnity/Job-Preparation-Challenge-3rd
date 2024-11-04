import java.util.*;
import java.io.*;

public class Main {

    static int n,m,l,c;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[][] B = new int[n+l][m+c];

        for (int i = 0; i < n+l; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m+c; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] A = new int[n][m];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = B[i][j];
                A[n-1-i][j] = B[n+l-1-i][c+j];
            }
        }

        for (int i = l; i < n-l; i++) {
            for (int j = 0; j < c; j++) { A[i][j] = B[i][j]; }
            for (int j = c; j < m; j++) {
                A[i][j] = B[i][j] - A[i-l][j-c];
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(A[i][j]);
                sb.append(' ');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

