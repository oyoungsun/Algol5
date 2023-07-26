import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class edge implements Comparable<edge>{
    int v1;
    int v2;
    int w;
    public edge(int v1, int v2,int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
    @Override
    public int compareTo(edge o1){
        return this.w-o1.w; //오름차순
    }
}
public class Main {
    static int n;
    static PriorityQueue<edge> edges = new PriorityQueue<>();
    static int parent[];
    static int total=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();


        int E = sc.nextInt();
        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v =sc.nextInt();
            int w= sc.nextInt();
            edges.add(new edge(u,v,w));
        }
        parent = new int[n+1];
        mst();
        System.out.println(total);
    }

    private static void mst() {
        //edge 정렬 후
        //가장 작은 것 부터 뽑는다.
        //모든 노드를 찾을떄까지
        set();
        //부모가 root만 남을때까지
        while(n>1) { //union 자료구조가 한개남을때까지...인데
            edge now = edges.poll();
            //부모가 다름
            if(findP(now.v1)!=findP(now.v2)){
                merge(now.v1, now.v2);
                total += now.w;
            }
            //부모가 같으면 병합 필요 없음
        }
    }

    private static void set() {
        for(int i=0; i<=n; i++){
            parent[i]=i; //자기자신
        }
    }

    private static void merge(int v1, int v2) {
        parent[findP(v2)] = parent[findP(v1)];
        n-=1; //union, 병합하면 root하나가 줄어듦
        //v2의 최종부모의 부모를 v1의 최종부모로 묶음.
    }

    private static int findP(int child) {
        while(parent[child]!=child){
            child = parent[child];
        }
        return child;
    }
}
