import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int l = sc.nextInt();
        findMid(n, l);
    }

    private static void findMid(int n, int l) {
        int start = 0;
        int sM=0, eM=0;
        int total=0;
        int minL = Integer.MAX_VALUE;

        for(int end=0; end<=n; end++){
            total+=end;
            while(total>n){
                total-=start;
                start+=1;
            }
            if(total==n&&end-start+1>=l) {
                if (minL > end - start+1) {
                    minL = end - start+1;
                    sM = start;
                    eM = end;
                }
                if (end - start + 1 == 1) break;
            }

        }
        if(total-sM==n && (eM-sM+1)>l) {//정수 0이 필요없을 때 빼준다.
            sM+=1;
        }
        if((eM-sM+1)>100||minL>100) System.out.println(-1);
        else {
                for (int i = sM; i <= eM; i++) {
                    System.out.print(i + " ");
                }
            }

    }
}
