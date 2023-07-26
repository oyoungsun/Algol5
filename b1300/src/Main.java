import java.util.Scanner;

public class Main {
    static int n;
    static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        biInx(1,k);
        
    }

    private static void biInx(long lo, long hi) {
        while(lo<hi){
            long mid = (lo+hi)/2;
            long count=0;
            for(int i=0; i<=n; i++){
                count+= Math.min(mid/i, n);
            }
            if(k<=count){
                hi =mid;
            }else{
                lo =mid+1;
            }
        }
        System.out.println(lo);
    }


}
