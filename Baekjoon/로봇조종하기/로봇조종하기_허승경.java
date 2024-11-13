import java.io.*;
import java.util.*;

public class Main {
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][] arr = new int[n][m];
        long [][] mars = new long[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행은 오른쪽으로 뻗어가기
        mars[0][0] = arr[0][0];
        for(int j = 1; j < m; j++){
            mars[0][j] = mars[0][j-1] + arr[0][j];  // 이전값 + 현재값
        }

        for(int i = 1; i < n; i++){
            // 왼 -> 오
            long [] left = new long[m];     // 왼쪽에서 오른쪽 오는 경로 최댓값 저장
            left[0] = mars[i-1][0] + arr[i][0]; // 행의 첫 번째 위치 -> 무조건 윗 행의 첫번째 열에서 밖에 못 옴
            for(int j = 1; j < m; j++){
                left[j] = Math.max(left[j-1], mars[i-1][j]) + arr[i][j];
            }

            // 오 -> 왼
            long [] right = new long[m];    //오른쪽에서 왼쪽 가는 경로 최댓값 저장
            right[m-1] = mars[i-1][m-1] + arr[i][m-1];  // 행의 마지막 위치 -> 무조건 윗행의 마지막 열에서 밖에 못 옴
            for(int j = m-2; j > -1; j--){
                right[j] = Math.max(right[j+1], mars[i-1][j]) + arr[i][j];
            }

            // 최종 합산 (두 방향의 값 중 최댓값이, 현재 위치의 최댓값)
            for(int j = 0; j < m; j++){
                mars[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(mars[n-1][m-1]);
    }
}