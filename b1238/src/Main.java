import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class node implements Comparable<node>{

    int w;

    int v2;
    public node(int v2, int w){
        this.v2 = v2;
        this.w = w;
    }

    @Override
    public int compareTo(node o1){
        return this.w - o1.w;
    }
}
public class Main {
    static PriorityQueue<node> pq;


    static int INF = 101;
    static int N;
    static int x;
    public static void main(String[] args) {
        ArrayList<node> nodes[] ;
        ArrayList<node> reverse[] ;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int M = sc.nextInt();
        x = sc.nextInt(); //x 가 목적지임.
        nodes = new ArrayList[N+1];
        reverse = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            nodes[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int w = sc.nextInt();
            nodes[v1].add(new node(v2,w));
            reverse[v2].add(new node(v1,w));
        }
        int max=0;

        int dis1[] = dijk(nodes);
        int dis2[] = dijk(reverse);
        for(int i=1; i<=N; i++) {
            max = Math.max(dis1[i]+dis2[i], max);
        }
        System.out.println(max);


    }

    private static int[] dijk(ArrayList<node> [] nodes) {
        pq = new PriorityQueue<>();
        pq.add(new node(x,0));

        boolean visit[] = new boolean[N+1];
        int dist[] = new int[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x]=0;
        while(!pq.isEmpty()){
            node K = pq.poll();
            int now = K.v2;
            if(visit[now]) continue;
            visit[now]=true;
            for(node n : nodes[now]){
                if(!visit[n.v2] && dist[n.v2]> dist[now]+n.w){
                    dist[n.v2] = dist[now]+n.w;
                    pq.add(new node(n.v2, dist[n.v2]));
                }
            }
        }
        return dist;
    }
}
