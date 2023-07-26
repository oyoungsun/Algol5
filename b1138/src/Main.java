import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] pre = new int[N+1];
        int [] map = new int[N+1];
        for(int i=1; i<=N; i++){
            pre[i] = sc.nextInt();
        }
        for(int i=0; i<=N; i++){
            int cnt=0;
            for(int j=1; j<=N; j++){
                if(cnt ==pre[i] && map[j]==0){
                    map[j]= i;
                    break;
                }else if(map[j]==0) cnt++;
            }
        }
        for(int i=1; i<=N; i++)
            System.out.print(map[i]+" ");

    }
}
