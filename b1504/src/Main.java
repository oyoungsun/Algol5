import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.System.exit;

class node implements Comparable<node> {
    int v;
    int e;
    public node(int v, int e){
        this.v = v;
        this.e = e;
    }


    @Override
    public int compareTo(node o) {
        return this.e - o.e;
    }
}
public class Main {
    static int n, e;
    static int start=1;
    static int end;
    static int u, v;
    static boolean flag;
    static ArrayList<node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        end = n;
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int w =  Integer.parseInt(st.nextToken());
            graph[n1].add(new node(n2, w));
            graph[n2].add(new node(n1, w));
        }
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        flag= false;
        int dist = 0;
        dist += dijkstra(1, u);
        dist += dijkstra(u, v);
        dist += dijkstra(v, n);
        if(flag) dist = Integer.MAX_VALUE;
        flag = false;
        int dist2 = 0;
        dist2 += dijkstra(1, v);
        dist2 += dijkstra(u, v);
        dist2 += dijkstra(u, n);
        if(flag) dist2 = Integer.MAX_VALUE;

        int answer = Math.min(dist, dist2);
        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(answer);
        }
    }

    private static int dijkstra(int u, int v) {

        boolean visit[] = new boolean[n+1];
        int dist[] = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[u] = 0;
        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.add(new node(u, 0));
        while(!pq.isEmpty()){
            node now = pq.poll();
            visit[now.v] = true;

            for(node next : graph[now.v]){
                if(visit[next.v]) continue;
                if(dist[next.v] > dist[now.v]+next.e) {
                    dist[next.v] = dist[now.v]+next.e;
                    pq.add(new node(next.v, dist[next.v]));
                }
            }
        }
        if(dist[v] ==Integer.MAX_VALUE){
            flag=true;
        }
        return dist[v];

    }
}
