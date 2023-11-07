import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class pos implements Comparable<pos> {
    int x;
    int y;
    int crash;
    pos(int x, int y, int crash){
        this.x = x;
        this.y = y;
        this.crash = crash;
    }
    @Override
    public int compareTo(pos o1){
        return this.crash - o1.crash;
    }

}

public class Main {
    static int [] dy = {-1, 0, 1, 0};
    static int [] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] inputs = br.readLine().split(" ");
        int m = Integer.parseInt(inputs[0]);
        int n = Integer.parseInt(inputs[1]);
        char map[][] = new char[n][m];
        int dist[][] = new int[n][m];
        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int min = dijkstra(dist, map, n, m);

    }

    private static int dijkstra(final int[][] dist, final char[][] map, final int n, final int m) {

        PriorityQueue<pos> pq = new PriorityQueue<>();
        pq.offer(new pos(0,0,0));
        dist[0][0] = 0;
        while(!pq.isEmpty()){
            pos now = pq.poll();
            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int crash = now.crash;
                if(nx<0 || ny<0 || ny>=n || nx>=m) continue;
                if(map[ny][nx]=='1'){
                    crash++;
                }
                if(dist[ny][nx] > crash) {
                    dist[ny][nx] = crash;
                    pq.offer(new pos(nx, ny, crash));
                }
            }
        }
        System.out.print(dist[n-1][m-1]);
        return dist[n-1][m-1];
    }
}
