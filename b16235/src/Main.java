import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class tree implements Comparable<tree>{
    int x;
    int y;
    int age;
    boolean live;
    public tree(int x, int y, int age){
        this.x=x;
        this.y=y;
        this.age = age;
        this.live = true;
    }

    @Override
    public int compareTo(tree o) {
        return this.age - o.age;
    }
}

public class Main {
    static int A[][];
    static int map[][];
    static int N;
    static int dir[][] = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static ArrayList<tree> trees = new ArrayList<>();
    static Queue<tree> deads = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(map[i], 5); //초기화
            for(int j=1; j<=N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new tree(x, y, age));
        }
        //나이 작은순 정렬
        Collections.sort(trees);
        for(int i=0; i<K; i++) {
            //봄
            spring();
            //여름
            summer();
            //가을
            fall();
            //겨울
            winter();

        }
        int cnt=0;
        for(tree t : trees){
            if(t.live) cnt++;
        }
        System.out.println(cnt);

    }

    private static void winter() {
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] += A[i][j];
            }
        }
    }

    private static void fall() {
        ArrayList<tree> newtrees = new ArrayList<>();
        for(tree t : trees){
            if(!t.live) continue;
            if(t.age%5==0){
                for(int i=0; i<8; i++){
                    int nx = t.x+dir[i][0];
                    int ny = t.y+dir[i][1];
                    if(nx<1 || ny<1 || nx>N || ny>N) continue;
                    newtrees.add(new tree(nx,ny, 1));
                }
            }
        }
        for(tree t : trees){
            if(t.live) newtrees.add(t);
        }
        trees = newtrees;
    }

    private static void summer() {
        //양분 더해주기
        while(!deads.isEmpty()){
            tree dead = deads.poll();
            map[dead.x][dead.y] += dead.age/2;
        }
    }

    private static void spring() {
        //나이 적은순대로 양분 섭취
        for(tree t : trees){
            if(!t.live) continue;
            if(map[t.x][t.y]-t.age>=0){
                map[t.x][t.y]-=t.age;
                t.age+=1;
            }else{
                t.live=false;//죽었다고 처리 후
                //나무 죽이기 list에 넣는다.
                deads.add(t);
            }
        }
    }
}
