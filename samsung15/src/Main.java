import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, Ox,Oy;
    static int map[][];
    static int bx,by, rx,ry;
    static int tempbx,tempby, temprx,tempry;
    static int dir[][] = {{0,1},{1,0},{0,-1},{-1,0}};
    static int MAX_SLIP = 10;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =Integer.parseInt(st.nextToken());
        m =Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0; i<n; i++){
            String temp = br.readLine();
            for(int j=0; j<m; j++){
                char c = temp.charAt(j);
                if(c=='#'){
                    map[i][j]=-1;
                }else if(c=='B'){
                    map[i][j]=1;
                    bx=tempbx=i;by=tempby=j;
                }
                else if(c=='R'){
                    map[i][j]=1;
                    rx=temprx=i;ry=tempry=j;
                }else if(c=='O'){
                    Ox=i;
                    Oy=j;
                }else{
                    map[i][j]=0;
                }
            }
        }
        int result = dfs(0);
        if(result>0){
            System.out.println(result);
        }else{
            System.out.println(-1);
        }

    }
    public static int dfs(int count){
        int result=0;
        if(count==MAX_SLIP){
            return -1;
        }
        else {
            if(count==0){
                temprx=rx; tempry=ry;
                tempbx=bx; tempby=by;

            }
            for (int i = 0; i < 4; i++) {
                //map 초기화 하기 >0 이면 1로 바꾸기
                initial();
                boolean out = sliping(i);

                if (out) {
                    return count;
                }
                result = dfs(count + 1);
                if (result == -1) return -1;
            }
            return result;
        }
    }

    private static void initial() {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j]>0){
                    map[i][j]=1;
                }
            }
        }
    }

    public static boolean sliping(int d){
        //rx-> bx 각각이동시킴.
        //만약 rx가 벽에 닿으면 거리를 저장해둠
        //bx도 벽에 닿았는데 >0 이상이면 거리 비교해보고, 
        //bx거리>rx거리면 bx뒤 rx앞에 위치시킴
        //bx<rx면 bx앞 rx뒤에 위치시킴

        //rx이동
        int nx=temprx; int ny=tempry;
        int orix=temprx; int oriy=tempry;
        int max = Math.max(n, m);
        boolean move=false;
        for(int cnt=1; cnt<=max; cnt++){
            nx+=dir[d][0];
            ny+=dir[d][1];
            if(nx==Ox&&ny==Oy) return true;
            if(nx>=n ||ny>=m ||nx<0||ny<0) continue;
            if(map[nx][ny]==-1&&cnt>1) {//stop
                move=true;
                temprx=nx-dir[d][0];
                tempry=ny-dir[d][1];
                if(map[temprx][tempry]!=1){
                    map[temprx][tempry] = cnt;
                }else{ //벽앞에 이미 bx가 있었던 경우-> 바로 뒤
                    //벽_사탕사탕 되면일단 전진해두면 bx에 고쳐진다.
                    if (cnt > 2) {
                        temprx -= dir[d][0];
                        tempry -= dir[d][1];
                        map[temprx][tempry] = cnt;
                    }
                }
            }
        }
        if(move){
            map[orix][oriy]=0;}
        //bx 이동
        orix=tempbx; oriy=tempby;
        move=false;
        nx = tempbx; ny = tempby;
        for(int cnt=1; cnt<=max; cnt++){
            nx+=dir[d][0];
            ny+=dir[d][1];
            if(nx>=n ||ny>=m ||nx<0||ny<0) continue;
            if(map[nx][ny]==-1&&cnt>1){//한칸 이상 가서 stop
                move=true;
                tempbx -=dir[d][0];
                tempby -=dir[d][0];
                if(map[tempbx][tempby]>0){ //빈칸이 아님
                    if(map[tempbx][tempby]>cnt&&cnt>2){//두칸이상 가서 red>blue
                        temprx-=dir[d][0];
                        tempry-=dir[d][1]; //교체
                    }else if(map[tempbx][tempby]<=cnt&&cnt>2){//red<blue
                        tempbx-=dir[d][0];
                        tempby-=dir[d][1];
                        map[tempbx][tempby] = cnt;
                    }
                }else{
                    map[tempbx][tempby] = cnt;
                }
            }
            //벽아니면 계속 전진가능함
        }
        if(move){
            map[orix][oriy]=0;}
        return false;
    }
}