import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class node{
    int x;
    int y;
    int d;
    public node(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
public class Main {
    static int n;
    static int map[][];
    static int dp[][];
    static int dir[][] = {{1,0},{1,1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //pipe 초기
        dp[0][1] = 0;
        //pipe꼬리가 i, j일때 :
        /*
        1. 머리가 가로
        2. 머리가 세로.
        3. 머리가 대각선
        +) 해당 위치에 1이 있는 경우는 고려X
        3가지 머리 중에 이동횟수 누적
        */
        if(map[n-1][n-1]==1) {
            System.out.println(0);
            return;
        }
        Queue<node> q  = new LinkedList<>();
        q.add(new node(-0,1,1));
        while(!q.isEmpty()){
            node now = q.poll();
            node [] nexts = directions(now);
            for(node nx : nexts){
                if(nx.x<0||nx.y<0||nx.x>=n||nx.y>=n) continue;
                if(map[nx.x][nx.y]==1) continue;
                if(nx.d==3){
                    if(map[now.x+1][now.y]==1) continue;
                    if(map[now.x][now.y+1]==1) continue;
                }
                dp[nx.x][nx.y] += 1;
                q.add(new node(nx.x, nx.y, nx.d));
            }
        }
        System.out.println(dp[n-1][n-1]);
    }

    private static node[] directions(node now) {
        node[] temp;
        if(now.d==1){
            //가로
            temp = new node[2];
            temp[0] = new node(now.x, now.y+1, 1); // 가로
            temp[1] = new node(now.x+1, now.y+1, 3); // 대
        }else if(now.d==2) {
            //세로
            temp = new node[2];
            temp[0] = new node(now.x+1, now.y, 2); // 세로
            temp[1] = new node(now.x+1, now.y+1, 3); // 대
        }else{
            //대각선
            temp = new node[3];
            temp[0] = new node(now.x, now.y+1, 1); // 가로
            temp[1] = new node(now.x+1, now.y, 2); // 세로
            temp[2] = new node(now.x+1, now.y+1, 3); // 대
            //대각선의 경우에는 가로, 세로가 꼭 빈칸이어야함.
        }
        return temp;
    }
}
