import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int [][] map;
    static long [][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new int[n][n];
        dp = new long[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        dp[0][0]=1;
        long max = 2*n-1;
        for(int i=0; i<max; i++){
            for(int j=0; j<=i; j++){
                int k = i-j;
                if(j>=n||k>=n) continue;
                if(map[j][k]==0) continue;
                int newX = j+map[j][k];
                int newY = k+map[j][k];

                if(newX<n){
                    dp[newX][k] += dp[j][k];
                }
                if(newY<n){
                    dp[j][newY] += dp[j][k];
                }

            }
        }
//        for(int i=0; i<n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[n-1][n-1]);
    }
}
