import java.util.Scanner;

public class Main {
//하노이탑 K
    static double k;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //hanoi => h[n-1]+1+h[n-1]
        int n = sc.nextInt();
        k = sc.nextDouble();

        hanoi(n, 1, 2,3);
    }

    private static int hanoi(int n, int from, int mid, int to) {

        if(n==0) return 0;
        int r = (int) (Math.pow(2, n-1)-1);
        if(k<=r){ //n-1 , from, mid
            hanoi(n-1, from, to, mid);
        }else {
            k = k - r; //앞단계 pass
        }
        k=k-1;
        if(k==0){
            System.out.println(from+" "+to);
            return 0;
        }

        if (k<=r){ //n-1 , from, mid
            hanoi(n - 1, mid, from, to);
        }
        else{
            k = k-r;
        }
        return 0;
    }

}
