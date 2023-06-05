import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class node{
    int x;
    int y;
    public node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int map[][];
    static int n,m;
    static ArrayList<node> chicken;
    static ArrayList<node> home;
    static int totalSum=Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        chicken = new ArrayList<>();
        home = new ArrayList<>();
        //구간합구하기
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==2){
                    chicken.add(new node(i, j));
                }else if(map[i][j]==1) {
                    home.add(new node(i, j));
                }
            }
        }
        boolean visit[] = new boolean[chicken.size()];
        combination(0,0, visit);
        System.out.println(totalSum);
    }

    private static void combination(int start, int depth, boolean[] visit) {
        if(depth>=m) {
            distanceChick(visit);
            return;
        }
        for(int i=start; i<chicken.size(); i++) {
            visit[i]=true;
            combination(i+1, depth+1, visit);
            visit[i]=false;
        }

    }

    private static void distanceChick(boolean[] visit) {
        //int copy[][] = new int[n][n];
        ArrayList<node> save = new ArrayList<>();
        for(int i=0; i<visit.length; i++){
            if(visit[i]==true){
                save.add(chicken.get(i));
            }
        }

        int sum = 0;
        for(node h : home){
            int min = Integer.MAX_VALUE/4;
            for(node c : save){
                min = Math.min(min, Math.abs(h.x-c.x)+Math.abs(h.y-c.y));
            }
            sum+= min;
        }
        totalSum = Math.min(totalSum, sum);
    }
}
