import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int MOD = 1000000000;

        int dp2[] = new int[n+1];
        dp2[0] = 1;
        dp2[1] = 1;
        for(int i = 2 ; i <= n ; i ++)
            dp2[i] = (dp2[i-2] + dp2[i/2])%MOD;
        System.out.println(dp2[n]);

    }}