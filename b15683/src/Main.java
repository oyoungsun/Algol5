import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class node implements Comparable<node>{
    int x;
    int y;
    int num;
    public node(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }

    @Override
    public int compareTo(node o) {
        return this.num-o.num;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n, m;
    static int min=100;
    static ArrayList<node> cctv =new ArrayList<node>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int [][] map = new int[n][m];
        //cctv 위치 따로 저장
        //cctv 번호마다 돌 수 있는 범위(방향)함수 제공
        //방향 함수에서 준 list에서 가장 크게 가리는 방향을 찾음.
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 6 && map[i][j] != 0) {
                    cctv.add(new node(i, j, map[i][j]));
                }
            }
        }
        ArrayList<int[]> dirList = new ArrayList<>();
        dfs(map, 0, dirList);
        System.out.println(min);
    }

    //완탐으로 모든 경로 찾기
    private static void dfs(int[][] map, int depth, ArrayList<int []>dirL) {
        if(depth==cctv.size()){
            //mark

            int [][] copy = copyM(map);
            for(int i=0; i<depth; i++){
                mark(copy, dirL.get(i), cctv.get(i).x, cctv.get(i).y);
            }
            //count
            int total=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(copy[i][j]==0) total++;
                }
            }
            min = Math.min(min, total);
            return;
        }
        int [][] dir = getDir(cctv.get(depth));
        for(int i=0; i<dir.length; i++){
            dirL.add(dir[i]);
            dfs(map, depth+1, dirL);
            dirL.remove(depth);
        }
    }

    private static int[][] copyM(int[][] map) {
        int [][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    private static void mark(int map[][], int[] mark, int x, int y) {
        for (int k = 0; k < mark.length; k++) {
            if (mark[k] == 1) {//좌
                int nx = x;
                int ny = y;
                while (true) {
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                    if (map[nx][ny] == 6) break;
                    map[nx][ny]= 7;
                    nx = nx;
                    ny = ny - 1;
                }
            } else if (mark[k] == 2) {//위
                int nx = x;
                int ny = y;
                while (true) {
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                    if (map[nx][ny] == 6) break;
                    map[nx][ny]= 7;
                    nx = nx - 1;
                    ny = ny;
                }
            } else if (mark[k] == 3) {//우
                int nx = x;
                int ny = y;
                while (true) {
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                    if (map[nx][ny] == 6) break;
                    map[nx][ny]= 7;
                    nx = nx;
                    ny = ny+1;
                }
            } else { //아래
                int nx = x;
                int ny = y;
                while (true) {
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) break;
                    if (map[nx][ny] == 6) break;
                    map[nx][ny]= 7;
                    nx = nx + 1;
                    ny = ny;
                }
            }

        }
    }


    private static int[][] getDir(node node) {
        int num = node.num;
        if(num==1){
            return new int[][]{{1},{2},{3},{4}};//좌, 위, 우, 아래
        }else if(num==2){
            return new int[][]{{1,3},{2,4}}; //가로, 세로
        }else if(num==3){
            return new int[][]{{1,2},{2,3},{3,4},{1,4}};
        }else if(num==4){
            return new int[][]{{1,2,3},{2,3,4},{1,3,4},{1,2,4}};
        }else{
            return new int[][]{{1,2,3,4}};
        }
    }
}
