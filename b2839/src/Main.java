import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int total=0;
        while (true){
            if(N%5==0){
                total += N/5;
                System.out.println(total);
                break;
            }
            total++;
            N-=3;
            if(N<0){
                System.out.println(-1);
                break;
            }
        }
    }
}
