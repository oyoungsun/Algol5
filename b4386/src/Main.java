import java.util.*;

class node {
    int num;
    double x;
    double y;
    public node(int num, double x, double y){
        this.num = num;
        this.x =x;
        this.y =y;
    }
}
class star implements Comparable<star>{
    public int x;
    public double dist;
    public star(int x, double dist ){
        this.x = x;
        this.dist = dist;
    }

    @Override
    public int compareTo(star o) {
        return (int) (this.dist - o.dist);
    }
}
public class Main {
    static ArrayList<node> stars = new ArrayList<>();
    static ArrayList<star>[] edgeList;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        edgeList = new ArrayList[n];
        for(int i=0; i<n; i++){
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            stars.add(new node(i, x,y));
            edgeList[i] = new ArrayList<>();
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                double dist = distance(stars.get(i),stars.get(j));
                edgeList[i].add(new star(j, dist));
                edgeList[j].add(new star(i, dist));
            }
        }

        Prim();

    }

    private static void Prim() {
        boolean visit[] = new boolean[n];
        star start;
        PriorityQueue<star> pq = new PriorityQueue<>();
        pq.offer(new star(0,0));
        double total = 0;
        while(!pq.isEmpty()){
            start = pq.poll();

            if(!visit[start.x]){
                total+= start.dist;
                visit[start.x]=true;
            }else continue;
            for(int i=0; i<edgeList[start.x].size(); i++){
                star next = edgeList[start.x].get(i);
                if(!visit[next.x]) pq.offer(new star(next.x, next.dist));
            }
        }

        System.out.printf("%.2f", total);

    }
    public static double distance(node a, node b){
        return Math.sqrt(Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2));
    }
}
