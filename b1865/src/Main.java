import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
class node {
    int v;
    int cost;
    public node(int v, int e){
        this.v = v;
        this.cost= e;
    }

}
public class Main {
    static int n, m, w;
    static ArrayList<node>[] graph;
    static boolean visit[][];
    static int INF = 10001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {

        //1. 사이클이 가능한가
        //2. 사이클이 가능하며, 그 값이 음수인가
        //-> edge n-1개를 쓴 경우 > edge n개를 쓴경우 라면, YES 출력
        //아니라면 NO
        //두 지점을 연결하는 도로가 한개 이상? -> 언제나 최소값으로 이동
        //경로가 없는, INF의 경우는 상관없음.
        int testcase = Integer.parseInt(br.readLine());
        while(testcase-->0){
            System.out.println(solution());
        }
    }

    private static String solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        visit = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){ //양방향 & >=0
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t =  Integer.parseInt(st.nextToken());
            if(!visit[s][e]) {
                graph[s].add(new node(e, t));
                graph[e].add(new node(s, t));
                visit[s][e] = true; visit[e][s] = true;
            }else{
                int temp=0;
                for(int k=0; k<graph[s].size(); k++){
                    if(graph[s].get(k).v==e){
                        temp = Math.min(graph[s].get(k).cost, t);
                        graph[s].remove(k);
                        graph[s].add(new node(e, temp));
                    }
                }
                for(int k=0; k<graph[e].size(); k++){
                    if(graph[e].get(k).v==s){
                        temp = Math.min(graph[e].get(k).cost, t);
                        graph[e].remove(k);
                        graph[e].add(new node(s, temp));
                    }
                }
            }
        }
        for(int i=0; i<w; i++){ //무방향 & < 0
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t =  Integer.parseInt(st.nextToken());
            if(!visit[s][e]) {
                graph[s].add(new node(e, t*-1));
                visit[s][e]=true;
            }else{
                int temp=0;
                for(int k=0; k<graph[s].size(); k++){
                    if(graph[s].get(k).v==e) {
                        temp = Math.min(graph[s].get(k).cost, t * -1);
                        graph[s].remove(k);
                        graph[s].add(new node(e, temp));
                    }
                }


            }

        }
        if(floyd()){
            return "YES";
        }else{
            return "NO";
        }
    }

    private static boolean floyd() {
        int depth[][] = new int[n][n+1];
        int answer[] = new int[n+1];
        Arrays.fill(depth[0], INF);
        depth[0][1]=0; //v 는 1부터 시자구ㅜㅜ

        for(int i=0; i<n-1; i++){ // edge layer
            for(int j=1; j<=n; j++){ //node layer
                depth[i+1][j] = depth[i][j];
                for(node nbr : graph[j]){
                    depth[i+1][j] = Math.min(depth[i+1][j], depth[i][nbr.v]+nbr.cost);
                }
            }
        }
        //n-1, n 비교를 위한 n단계
        for(int j=1; j<=n; j++){ //node layer
            answer[j] = depth[n-1][j];
            for(node nbr : graph[j]){
                answer[j] = Math.min(answer[j], depth[n-1][nbr.v]+nbr.cost);
            }
        }
        for(int j=1; j<=n; j++){ //node layer
            if(depth[n-1][j]!=answer[j]) return true;
        }
        return false;

    }


}
