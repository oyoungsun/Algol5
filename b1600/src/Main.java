import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class node{
    public int y;
    public int x;
    public int kk;

    public node(int x, int y, int kk){
        this.x = x;
        this.y = y;
        this.kk = kk;
    }
}
class knight{
    public int y;
    public int x;


    public knight(int x, int y){
        this.x = x;
        this.y = y;

    }
}
public class Main {
    static int cdir[][] = {{-1,0},{0,1},{1,0},{0,-1}};
    static int ucdir[][] = {{-1,0},{0,-1},{1,0},{0,1}};

    static int map[][];
    static int min = Integer.MAX_VALUE;
    static int w, h, k;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        h = sc.nextInt();
        w = sc.nextInt();
        map = new int[w][h];
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                int p = sc.nextInt();
                if(p==1) map[i][j]=-1;
                else map[i][j]=0;
            }
        }
        bfs(0,0);
        if(min==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(min);
        }

    }

private static void bfs(int x, int y) {
    int [][][] visit = new int[w][h][k+1];
    Queue<node> q = new LinkedList<>();
    visit[0][0][0]=1;
    q.add(new node(0,0,k));
    while(!q.isEmpty()) {
        node now = q.poll();
        if (now.x == w - 1 && now.y == h - 1) {
            min = Math.min(visit[now.x][now.y][now.kk], min);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = now.x + cdir[i][0];
            int ny = now.y + cdir[i][1];
            if (!isBoundury(nx, ny)) continue;
            if (visit[nx][ny][now.kk]!=0) continue;
            visit[nx][ny][now.kk] = visit[now.x][now.y][now.kk]+1;
            q.add(new node(nx,ny, now.kk));
        }
        if(now.kk>0){ //k 가능한 먼저 써버린다
            Queue<knight> horseCan = horse(now.x, now.y);
            for (int i = 0; i < 8; i++) {
                knight temp = horseCan.poll();
                if (!isBoundury(temp.x, temp.y)) continue;
                if (visit[temp.x][temp.y][now.kk-1]!=0) continue;
                visit[temp.x][temp.y][now.kk-1] += visit[now.x][now.y][now.kk]+1;
                q.add(new node(temp.x, temp.y, now.kk-1));
            }
        }
    }
    return;
}

    private static boolean isBoundury(int nx, int ny) {
        if(nx<0||ny<0||nx>=w||ny>=h) return false;
        if(map[nx][ny]==-1) return false;
        return true;
    }

    private static Queue<knight> horse(int x, int y) {
        Queue<knight> horseChoice = new LinkedList<>();
        for(int i=0; i<4; i++){
            int nx = x+cdir[i][0]*2;
            int ny = y+cdir[i][1]*2;
            nx += cdir[(i+1)%4][0];
            ny += cdir[(i+1)%4][1];
            horseChoice.add(new knight(nx,ny));
            nx = x+ucdir[i][0]*2;
            ny = y+ucdir[i][1]*2;
            nx += ucdir[(i+1)%4][0];
            ny += ucdir[(i+1)%4][1];
            horseChoice.add(new knight(nx,ny));
        }
        return horseChoice;
    }
}
