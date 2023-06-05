import java.util.*;

public class Main {
    static int attack[][] = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
    static int D = 0;
    static int minV = Integer.MAX_VALUE;
    static int dp[][][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] svc = new int[3];
        for (int i = 0; i < n; i++) {
            svc[i] = sc.nextInt();
        }
//        for(int i=n; i<3; i++){
//            svc[i] = 0;
//        }
        dp = new int[svc[0] + 1][svc[1] + 1][svc[2] + 1];
        dp[svc[0]][svc[1]][svc[2]] = 0;
//시간초과-> bfs말고 dfs로 변경
        Queue<int[]> q = new LinkedList<>();
        q.add(svc);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if(now[0] == 0 && now[1] == 0 && now[2] == 0) {
                break; //모두가 0이 되는 즉시종료-> 최소로 도달
            }
            for (int i = 0; i < 6; i++) {
                int idx1, idx2, idx3;
                if (now[0] - attack[i][0] > 0) {
                    idx1 = now[0] - attack[i][0];
                } else idx1 = 0; //뺐는데 index -경우되면 0으로 바꿈
                if (now[1] - attack[i][1] > 0) {
                    idx2 = now[1] - attack[i][1];
                } else idx2 = 0;
                if (now[2] - attack[i][2] > 0) {
                    idx3 = now[2] - attack[i][2];
                } else idx3 = 0;
                if (dp[idx1][idx2][idx3] == 0) {
                    //아직 방문한적 없으면 현재 방문이 최소임
                    q.add(new int[]{idx1, idx2, idx3});
                    dp[idx1][idx2][idx3] = dp[now[0]][now[1]][now[2]] + 1;
                }
            }
        }
        System.out.println(dp[0][0][0]);
    }
}

//        private static boolean allZ(int[] svc) {
//        for(int i=0; i<3; i++){
//            if(svc[i]>0) return false;
//        }
//        return true;
//    }

