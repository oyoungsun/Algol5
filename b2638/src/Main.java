import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class node{
    int x;
    int y;
    public node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int n, m;
    static int map[][];
    static boolean outside[][];
    static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j]=sc.nextInt();
            }
        }
        int time=0;
        while(true){
            findOutside();
            if(!melting()){
                break; //치즈 없어서 종료됨.
            }
            time++;
        }
        System.out.println(time);

    }

    private static boolean melting() {
        boolean isThereC=false;
        int temp[][] = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                temp[i][j]=map[i][j];
                if(map[i][j]==1){
                    if(!isThereC) isThereC=true;
                    int c=0;
                    for(int d=0; d<4; d++){
                        int nx = i+dir[d][0];
                        int ny = j+dir[d][1];
                        if(nx>=n||ny>=m||nx<0||ny<0) continue;
                        if(outside[nx][ny]) c++;
                    }
                    if(c>1){
                        temp[i][j]=0;
                    }
                }
            }
        }
        map = temp;
        return isThereC;
    }

    private static void findOutside() {
        outside = new boolean[n][m];
        bfs(0,0);
    }

    private static void bfs(int i, int j) {

        Queue<node> q = new LinkedList<>();
        outside[i][j]=true;
        q.add(new node(i,j));
        while(!q.isEmpty()){
            node now = q.poll();
            for(int d=0; d<4; d++){
                int nx = now.x+dir[d][0];
                int ny = now.y+dir[d][1];
                if(nx>=n||ny>=m||nx<0||ny<0) continue;
                if(outside[nx][ny]) continue;
                if(map[nx][ny]==1) continue;
                outside[nx][ny]=true;
                q.add(new node(nx, ny));
            }
        }
    }

}
