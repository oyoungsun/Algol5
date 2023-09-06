import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int map [][];
    static int n, m;
    static int dir[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j] = sc.nextInt();
            }
        }
        int time=0;
        while(true){
            int s = IsSeperate();
            if(s>=2) {
                System.out.println(time);
                break;
            }
            if(s==0) {
                System.out.println(0);
                break;
            }
            melting();
            time++;
        }

    }

    private static void melting() {
        int temp[][] = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j]==0) continue;
                int melt=0;
                for(int k=0; k<4; k++) {
                    int nx = i + dir[k][0];
                    int ny = j + dir[k][1];
                    if (nx >= n || ny >= m || nx < 0 || ny < 0) continue;
                    if (map[nx][ny] == 0) melt++;
                }
                temp[i][j] = Math.max(0, map[i][j]-melt);
            }
        }
        map = temp;
    }


    private static int IsSeperate() {
        boolean visit[][] = new boolean[n][m];

        int component = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) continue;
                if (!visit[i][j]) {
                    component++;
                    DFS(i, j, visit);
                }
            }
        }
        return component;
    }


    public static void DFS(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        int dx, dy;
        for (int i = 0; i < 4; i++) {
            dx = x + dir[i][0];
            dy = y + dir[i][1];

            if (dx < 0 || dy < 0 || dx >= n || dy >= m) {
                continue;
            }

            if (map[dx][dy] != 0 && !visited[dx][dy]) {
                DFS(dx, dy, visited);
            }
        }
    }

}
