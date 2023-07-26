import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class fish implements Comparable<fish>{
    int x;
    int y;
    int value;
    int dir;
    boolean live;
    public fish(int x, int y, int v, int dir){
        this.x=x;
        this.y=y;
        this.value = v;
        this.dir = dir;
        this.live=true;
    }
    public fish(int x, int y, int v, int dir, boolean live){
        this.x=x;
        this.y=y;
        this.value = v;
        this.dir = dir;
        this.live=live;
    }
    @Override
    public int compareTo(fish o1){
        return this.value - o1.value;
    }
}
public class Main {
    static int [][] origin = new int[4][4];
    static int max=0;
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<fish> eatlist = new ArrayList<>();

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int v = sc.nextInt();
                int dir =( sc.nextInt() -1 );
                origin[i][j]=v;
                eatlist.add(new fish(i,j,v,dir));
            }
        }
        int sx=0,sy=0,sdir=0;
        int total=0;
        //0,0 먹기
        for(int i=0; i<16; i++){
            if(eatlist.get(i).x==0&&eatlist.get(i).y==0){
                eatlist.get(i).live=false;
                sdir=eatlist.get(i).dir;
                total+=eatlist.get(i).value;
            }
        }
        //bfs -> brute force 방식으로 모든 방향 탐색
        bfs(sx,sy,sdir,total, 0, eatlist);
        System.out.println(max);
    }

    private static void bfs(int sx,int sy,int sdir,int total, int depth, ArrayList<fish> fishs) {
        //상어의 이동을 구현한다.
        //물고기를 먼저 이동시킨다.
        ArrayList<fish> eatlist = fishMove(fishs, sx,sy);
        //상어는 최대 방향+1~방향+3까지 이동가능하다.
        for(int i=1; i<=3; i++){
            int nx=sx+(dx[sdir]*i);
            int ny=sy+(dy[sdir]*i);
            if(!safeZone(nx,ny)) continue; //물고기가 없거나, 갈수없거나
            int eat = findF(nx,ny, eatlist);
            if(!eatlist.get(eat).live) continue;
            int ndir=eatlist.get(eat).dir;
            eatlist.get(eat).live=false;
            total += eatlist.get(eat).value;

            bfs(nx,ny,ndir,total,depth+1, eatlist);

            total -= eatlist.get(eat).value;
            eatlist.get(eat).live=true;
        }
        max = Math.max(max, total);
        return;
    }

    private static int findF(int nx, int ny, ArrayList<fish> fishs) {
        for(int i=0; i<16; i++) {
            fish f = fishs.get(i);
            if(nx==f.x&&ny==f.y) return i;
        }
        return -1;
    }

    private static boolean safeZone(int nx, int ny) {
        if(nx<0||ny<0) return false;
        if(nx>=4||ny>=4) return false;
        return true;
    }

    public static ArrayList<fish> fishMove(ArrayList<fish> temp, int sx,int sy) {
        //Collections.sort(temp);
        ArrayList<fish> fishs = new ArrayList<>();
        for(int i=0; i<16; i++) {
            fish f = temp.get(i);
            fishs.add(new fish(f.x, f.y, f.value, f.dir, f.live));
        }

        for(int i=0; i<16; i++) {
            Collections.sort(fishs);
            fish f = fishs.get(i);
            if (!f.live) continue; //죽은 물고기
            int j = 0;
            int nx = 0, ny = 0;
            int ix=0;
            for (j = 0; j < 8; j++) {
                ix = (f.dir+j)%8;
                nx = f.x + dx[ix];
                ny = f.y + dy[ix];
                if (!safeZone(nx, ny) || (nx == sx && sy == ny)) continue;
                else {
                    break; //이동 가능한 칸
                }
            }
            if (j >= 8) {
                continue; //이 물고기는 이동 불가능
            }
            else {//swap
                f = fishs.remove(i);
                int swap = findF(nx, ny, fishs);
                fish s = fishs.remove(swap);
                if (s.live) {
                    fishs.add(new fish(s.x, s.y, f.value, ix, f.live));
                    fishs.add(new fish(f.x, f.y, s.value, s.dir, s.live));
                } else {
                    fishs.add(new fish(s.x, s.y, f.value, ix, f.live));
                    fishs.add(new fish(f.x, f.y, s.value, s.dir, s.live));
                }
            }
        }
        //Collections.sort(fishs);
        return fishs;
    }
}
