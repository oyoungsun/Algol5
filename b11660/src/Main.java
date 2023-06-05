import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int map[][];
    static long dp[][];
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        //구간합구하기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        dp = new long[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= n; i++) {
            dp[1][i] = map[1][i];
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i-1][j] + map[i][j];
            }
        }
        for(int k=0; k<m; k++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            long sum = 0;
            for(int i=sy; i<=ey; i++){
                sum += dp[ex][i] - dp[sx-1][i];
            }
            sb.append(sum).append("\n");

        }
        System.out.println(sb);

    }
}
