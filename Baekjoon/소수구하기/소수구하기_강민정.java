import java.util.Scanner;

public class Main {
    static int m;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        for(int num=m; num<=n; num++) {
            if(!eratosthenes(num)) {
                System.out.println(num);
            }
        }
    }

    public static boolean eratosthenes(int num) {
        if(num == 1) {
            return true;
        } else if(num <= 2) {
            return false;
        }
        for(int i=2; i<=Math.sqrt(num); i++) {
            if(num % i == 0) {
                return true;
            }
        }
        return false;
    }
}