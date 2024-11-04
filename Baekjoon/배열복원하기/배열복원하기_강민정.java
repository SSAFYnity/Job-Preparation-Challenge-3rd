import java.util.Scanner;

class 배열복원하기_강민정
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int[][] bArr = new int[H + X][W + Y];
        int[][] aArr = new int[H][W];
        // 배열 B의 원소 입력 받기
        for(int i=0; i<H+X; i++) {
            for(int j=0; j<W+Y; j++) {
                bArr[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                // 인덱스가 넘어가면
                if(i-X < 0 || j-Y < 0) {
                    aArr[i][j] = bArr[i][j];
                } else {
                    aArr[i][j] = bArr[i][j] - aArr[i - X][j - Y];
                }
            }
        }

        // A배열 출력
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                System.out.print(aArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}