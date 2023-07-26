import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int problem[];
    static int N, L, R, X;
    static int count=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        X = sc.nextInt();
        problem = new int[N];
        boolean[] visit = new boolean[N];

        for(int i=0; i<N; i++){
            problem[i] = sc.nextInt();
        }
        bfs(0,visit, 0, 0);
        System.out.println(count);
    }

    private static void bfs(int sum,boolean[] visit, int start, int depth) {

        if(sum>=L&&depth>0) {
            int max=0, min=Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                if(!visit[i]) continue;
                max = Math.max(max, problem[i]);
                min = Math.min(min, problem[i]);
            }

            if (max - min >= X){
                count += 1;
            }
        }
        for(int i=start; i<N; i++){
            if(sum+problem[i]<=R){
                visit[i]=true;
                bfs(sum+problem[i], visit, i+1, depth+1);
                visit[i]=false;
            }
        }
    }
}
