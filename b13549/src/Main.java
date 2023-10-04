import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
class node implements Comparable<node> {
    int n;
    int depth;
    public node(int n, int depth){
        this.n = n;
        this.depth = depth;
    }
    @Override
    public int compareTo(node o1){
        return  this.depth-o1.depth;
    }

}
public class Main {
    static int k;
    static int minMove = Integer.MAX_VALUE;
    static int max;
    static int visit[] ;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = sc.nextInt();
        max = Math.max(n, k);
        max*=2;
        visit = new int[max+1]; //가능한 수 / depth

        bfs(n, 0);
        System.out.println(minMove);
    }

    private static void bfs(int st, int dt) {
        PriorityQueue<node> pq = new PriorityQueue<node>();
        pq.add(new node(st, dt));
        Arrays.fill(visit, Integer.MAX_VALUE);
        visit[st] = 0;
        while(!pq.isEmpty()){
            node now = pq.poll();
            if(now.n==k){
                minMove = Math.min(minMove, now.depth);
                return;
            }
            if(now.depth>100000)continue;

            if(now.n*2<=max&&visit[now.n*2]>now.depth) {
                visit[now.n*2]=now.depth;
                pq.add(new node(now.n*2, now.depth));
            }
            if(now.depth+1>100000)continue;
            if(now.n+1<=max&&visit[now.n+1]>now.depth+1) {
                visit[now.n+1]=now.depth+1;
                pq.add(new node(now.n+1, now.depth+1));
            }
            if(now.n-1>=0&&visit[now.n-1]>now.depth+1) {
                visit[now.n-1]=now.depth+1;
                pq.add(new node(now.n-1, now.depth+1));
            }
        }

    }
}
