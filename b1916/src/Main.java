import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class city implements Comparable<city>{

    int nextCity;
    int cost;

    public city(int n2, int w) {
        this.nextCity = n2;
        this.cost = w;
    }

    @Override
    public int compareTo(city o) {
        return this.cost - o.cost;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int e = sc.nextInt();
        ArrayList<city> graph[] = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++){
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            int w = sc.nextInt();
            graph[n1].add(new city(n2, w));
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        int dist[] = new int[n+1];
        boolean visit[] = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<city> pq = new PriorityQueue<>();
        pq.add(new city(start, 0));
        visit[start]=true;
        while(!pq.isEmpty()){
            city now = pq.poll();
            for(city c : graph[now.nextCity]){
                if(!visit[c.nextCity]&&c.cost+now.cost<dist[c.nextCity]){

                    dist[c.nextCity] = c.cost+now.cost;
                    pq.add(new city(c.nextCity, dist[c.nextCity]));
                }
            }
            visit[now.nextCity]=true;
            if(visit[end]) break;
        }
        System.out.println(dist[end]);
    }
}
