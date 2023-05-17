import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int v;
    int w;

    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
    @Override
    public int compareTo(Node o) {
        return this.w - o.w; // 가중치가 오름차순으로 정렬됨.
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = Integer.MAX_VALUE/4;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        ArrayList<Node>[] adjlist = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            adjlist[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int v1 =Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjlist[v1].add(new Node(v2, w));
        }
        int dist[] = dijkstra(start, adjlist);
        for(int i=1; i<n+1; i++){
            if(dist[i]!=INF) {
                System.out.println(dist[i]);
            }else System.out.println("INF");
        }
    }

    private static int [] dijkstra(int start, ArrayList<Node>[] adjlist) {
        //초기화
        PriorityQueue<Node> pq = new PriorityQueue();
        boolean visit[] = new boolean[adjlist.length];
        int dist[]= new int[adjlist.length];
        Arrays.fill(dist, INF);
        dist[start]=0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll(); //아직 방문안한 노드중 최소w중에 선택
            if(visit[now.v]) continue;
            visit[now.v]=true;

            for(Node neighbor : adjlist[now.v]){ //해당노드의 이웃들 경로 최적화

                if(dist[neighbor.v] > dist[now.v]+neighbor.w){
                    dist[neighbor.v] = dist[now.v]+neighbor.w;
                    pq.add(new Node(neighbor.v, dist[neighbor.v])); // 누적경로-> pq에넣음
                }

            }
        }
        return dist;
    }
}
