import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class place implements Comparable<place>{
    int x;
    int y;
    int w;
    public place(int x, int y, int w){
        this.x=x;
        this.y=y;
        this.w=w;
    }

    @Override
    public int compareTo(place o) {
        return this.w - o.w;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int dir[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static int map[][];
    static boolean visit[][];
    static int INF = Integer.MAX_VALUE / 4;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int num = 0;
        int n=1;
        while (true) {
            n = Integer.parseInt(br.readLine());
            if(n==0) break;
            num++;
            map = new int[n][n];
            visit = new boolean[n][n];
            //가중치 map 생성
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //첫 start도 가중치 가짐. 0이아니라 map[0][0];
            place start = new place(0, 0, map[0][0]);
            //정답 양식에 맞춰 작성
            sb.append("Problem " + num + ": " + dijkstra(start, n)).append("\n");
        }
        System.out.println(sb.toString());
    }
    private static int dijkstra(place start, int n) {
        //가중치 합 최소 저장할 dist배열
        int dist[][] = new int[n][n];
        for(int i=0; i<n; i++) Arrays.fill(dist[i], INF); //초기화
        dist[0][0]=map[0][0];
        PriorityQueue<place> pq = new PriorityQueue<>(); //
        pq.add(start);

        while(!pq.isEmpty()){ //dijkstra구현 부분
            place now = pq.poll();

            for(int i=0; i<4; i++){ //네 방향 중 갈수 있는 곳을 선택
                int nx = now.x + dir[i][0];
                int ny = now.y + dir[i][1];
                if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                //if(visit[nx][ny]) continue; //가중치가 0인 곳도 있음.
                if(dist[nx][ny] > dist[now.x][now.y]+map[nx][ny]){ //더 작은경우, 탐색할 의미 있음.
                    visit[now.x][now.y] = true;
                    dist[nx][ny] = dist[now.x][now.y]+map[nx][ny];
                    pq.offer(new place(nx,ny, map[nx][ny]));
                }
            }
        }
        return dist[n-1][n-1];

    }
}
