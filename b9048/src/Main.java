import java.util.Scanner;

public class Main {
    static int coin[];
    static int dp[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int k = 0; k < t; k++) {
            int n = sc.nextInt();
            coin = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                coin[i] = sc.nextInt();
            }
            int m = sc.nextInt();
            dp = new int[n+1][m+1];

            //중복이 가능한 수세기
            for(int i=1; i<=n; i++){
                dp[i][coin[i]]+=1;
            }
            for (int item = 1; item <= n; item++) {
                for (int size = 1; size <= m; size++) {
                    dp[item][size] += dp[item-1][size];
                    //넣을 수 없는 경우 -> 그대로 유지
                    if (size - coin[item] >= 0) {
                        //넣을 수 있는 경우
                        dp[item][size] += dp[item][size-coin[item]];
                    }
                }

            }
            System.out.println(dp[n][m]);
        }
    }
}
