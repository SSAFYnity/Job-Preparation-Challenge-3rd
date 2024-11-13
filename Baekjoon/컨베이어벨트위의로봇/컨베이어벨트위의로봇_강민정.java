import java.util.Scanner;
import java.util.LinkedList;

class Main {
    static class Box {
        int power;      // 내구도
        boolean flag;   // 로봇이 있는지

        Box(int power, boolean flag) {
            this.power = power;
            this.flag = flag;
        }

        public void put() {
            this.power--;
            this.flag = true;
        }
    }

    static LinkedList<Box> belt;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;     // 단계
        n = sc.nextInt();       // 컨테이너 벨트의 길이
        int k = sc.nextInt();       // 종료 기준
        int curK = 0;       // 내구도가 0인 칸의 갯수
        belt = new LinkedList();

        // 벨트 입력 받기
        for(int i=0; i<n*2; i++) {
            belt.add(new Box(sc.nextInt(), false));
        }

        while(curK < k) {
            answer++;
            // 회전
            belt.add(0, belt.removeLast());       // 맨앞에 추가
            if(belt.get(n - 1).flag) {
                belt.get(n - 1).flag = false;       // 로봇 내리기
            }

            // 로봇 움직이기
            for(int i=n-2; i>=0; i--) {
                if(belt.get(i).flag) {      // 현재 벨트위에 로봇이 있다면
                    if(!belt.get(i + 1).flag && belt.get(i + 1).power > 0) {        // 로봇을 이동시킬 수 없다면
                        // 로봇을 한 칸 이동시키기
                        belt.get(i).flag = false;    // 현재 벨트 위에 있는 로봇은 없어진다
                        belt.get(i + 1).put();
                        if (belt.get(i + 1).power <= 0) {
                            curK++;
                        }
                        if (i + 1 == n - 1) {
                            belt.get(i + 1).flag = false;       // 로봇 내리기
                        }
                    }
                }
            }

            // 로봇 올리기
            if(belt.get(0).power > 0) {
                belt.get(0).put();
                if(belt.get(0).power <= 0) {
                    curK++;
                }
            }
        }

        System.out.println(answer);
    }
}