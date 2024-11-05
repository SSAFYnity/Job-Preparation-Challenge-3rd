import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열복원하기_이승환{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] array = new int[H+X][W+Y];
        int[][] originalArray = new int[H][W];

        int curr;
        for(int i=0;i<H+X;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<W+Y;j++){
                curr = Integer.parseInt(st.nextToken());
                array[i][j] = curr;
            }
        }

        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                if(i<X){
                    originalArray[i][j]=array[i][j];
                } else{
                    if(j >= Y){
                        originalArray[i][j] = array[i][j] - originalArray[i-X][j-Y];
                    }else{
                        originalArray[i][j] = array[i][j];
                    }
                }
            }
        }

        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                System.out.print(originalArray[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}