import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    static int [][] rank ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in) ;
        int n = sc.nextInt();
        int score = sc.nextInt();
        int p =sc.nextInt();
        int max = Math.max(p+1, n+1);
        rank = new int[max][2];
        for(int i=0; i<max; i++){
            rank[i][0]=-1;
        }
        for(int i=0; i<n; i++){
            rank[i][0] = sc.nextInt();
            rank[i][1]= 0;
        }
        rank[n][0]=score;
        rank[n][1]=0;
        Arrays.sort(rank, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        }.reversed());

        boolean find=false;
        for(int i=0; i<n+1; i++){
            if(i!=0&&rank[i][0]==rank[i-1][0]){
                rank[i][1]=rank[i-1][1];
            }else {
                rank[i][1] = i+1;
            }
            if(rank[i][0]==score&&rank[i][0]>rank[max-1][0]){
                System.out.println(rank[i][1]);
                find=true;
                return;
            }
        }
        if(!find){
            System.out.println(-1);
        }

    }
}
