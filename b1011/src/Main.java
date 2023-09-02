import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();
        for(int i=0; i<t; i++){
            int st = sc.nextInt();
            int ed = sc.nextInt();
            sb.append(space(st, ed)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int space(int st, int ed) {
        int total = ed-st;
        int r = 1;
        int cnt=0;

        while(total>0){
            total -=r;
            if(total<=0){
                cnt+=1;
                break;
            }
            total -=r;
            r+=1;
            cnt+=2;
        }
        return cnt;
    }
}
