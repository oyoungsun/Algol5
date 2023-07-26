import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class dust{
    int x;
    int y;
    int remain;
    public dust(int x, int y, int remain){
        this.x = x;
        this.y = y;
        this.remain = remain;
    }
}
public class Main {
    static int A[][];
    static int dirMap[][];
    static int head[], tail[], R, C, T;
    static int dir[][] = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    //reverse 방향
    static int dirR[][] = new int[][]{{0,1},{-1,0},{0,-1},{1,0}};
    //시계방향
    static int dirT[][] = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    static ArrayList<dust> dusts = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        A = new int[R][C];
        dirMap = new int[R][C];
        boolean isHead=true;
        for(int i=0; i<R; i++){
            Arrays.fill(dirMap[i], -1);
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j]>0){
                    dusts.add(new dust(i, j, A[i][j]));
                }else if(A[i][j]==-1){
                    if(isHead) {
                        isHead=false;
                        head = new int[2];
                        head[0] = i;
                        head[1] = j;
                    }else{
                        tail = new int[2];
                        tail[0] = i;
                        tail[1] = j;
                    }

                }
            }
        }

        flow();
        //prt(dirMap);
        for(int i=0; i<T; i++) {
            extend();
            //prt(A);
            //초기화
            A[head[0]][head[1]] = -1;
            A[tail[0]][tail[1]] = -1;
            cleaning();
            A[head[0]][head[1]] = -1;
            A[tail[0]][tail[1]] = -1;
            //prt(A);
        }
        int total=0;
        for(dust d : dusts){
            total += d.remain;
        }
        System.out.println(total);
    }

    private static void prt(int[][] map) {
        for(int i=0; i<R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void flow(){

        //머리 역시계방향
        int nHx = head[0];
        int nHy = head[1];
        for(int i=0; i<4; i++){
            while(true) {
                if(nHx==tail[0]&&nHy==tail[1]) break; //한줄 다 하기 전에
                nHx = nHx + dirR[i][0];
                nHy = nHy + dirR[i][1];
                if(nHx<0 || nHy<0 || nHx>=R ||nHy>=C) {
                    //네 모서리에서 한칸 더가는 경우임
                    //네 모서리이므로 방향을 i+1로 정해야함.
                    //3일때는 본인에게로 돌아옴.
                    nHx = nHx-dirR[i][0];
                    nHy = nHy-dirR[i][1];
                    dirMap[nHx][nHy] = (i+1) % 4;
                    break;
                }
                dirMap[nHx][nHy] = i;
            }
        }
        //꼬리 시계방향
        nHx = tail[0];
        nHy = tail[1];
        for(int i=0; i<4; i++){

            while(true) {
                if(nHx==head[0]&&nHy==head[1]) break; //한줄 다 하기 전에
                nHx = nHx + dirT[i][0];
                nHy = nHy + dirT[i][1];
                if(nHx<0 || nHy<0 || nHx>=R ||nHy>=C) {
                    //네 모서리에서 한칸 더가는 경우임
                    //네 모서리이므로 방향을 i+1로 정해야함.
                    //3일때는 본인에게로 돌아옴.
                    nHx = nHx-dirT[i][0];
                    nHy = nHy-dirT[i][1];
                    dirMap[nHx][nHy] = (i+1) % 4;
                    break;
                }
                dirMap[nHx][nHy] = i;
            }
        }
    }
    private static void cleaning() {
        //방향 보고 dusts 위치 재정렬 해야함.
        int [][] tempM = new int[R][C];
        ArrayList<dust> temp = new ArrayList<>();
        for(dust d : dusts){
            if(dirMap[d.x][d.y]>-1){
                int di = dirMap[d.x][d.y];
                if(d.x<=head[0]) {//머리보다 같거나 위 에 있으면
                    //방향보고 위치 바꿔서 넣음
                    int nx = d.x + dirR[di][0];
                    int ny = d.y + dirR[di][1];
                    if(nx==head[0]&&ny==head[1]) continue; //정화
                    if(nx==head[0]&&ny==tail[1]) continue; //정화
                    tempM[nx][ny] = d.remain;
                    temp.add(new dust(nx, ny, d.remain));
                }else{ //아래 시계방향
                    int nx = d.x + dirT[di][0];
                    int ny = d.y + dirT[di][1];
                    if(nx==tail[0]&&ny==tail[1]) continue; //정화
                    if(nx==tail[0]&&ny==head[1]) continue; //정화
                    tempM[nx][ny] = d.remain;
                    temp.add(new dust(nx, ny, d.remain));
                }
            }else{// 그대로
                tempM[d.x][d.y] = d.remain;
                temp.add(new dust(d.x, d.y, d.remain));
            }
        }
        dusts = temp;
        A = tempM;
    }

    private static void extend() {
        int [][] tempM = new int[R][C];
        ArrayList<dust> temp = new ArrayList<>();
        for(dust d : dusts){
            int cnt=0;
            int nextR = d.remain/5;
            for(int i=0; i<4; i++){
                int nx = d.x+dir[i][0];
                int ny = d.y+dir[i][1];
                if(nx<0 || ny<0 || nx>=R ||ny>=C) continue;
                if(A[nx][ny]==-1) continue;
                cnt++;
                tempM[nx][ny] += nextR;
                //temp.add(new dust(nx, ny, A[nx][ny]));
            }
            tempM[d.x][d.y] += d.remain - nextR*cnt;
            //temp.add(new dust(d.x, d.y, A[d.x][d.y]));
        }
        //모든 합계가 끝나고 temp 다시 찾아야함
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(tempM[i][j]>0){
                    temp.add(new dust(i, j, tempM[i][j]));
                }
            }
        }
        A = tempM;
        dusts = temp;
    }

}
