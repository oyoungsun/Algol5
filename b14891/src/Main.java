import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

class node{
    int num;
    int dir;
    public node(int num, int dir){
        this.num = num;
        this.dir = dir;
    }
}
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int gear[][];
    public static void main(String[] args) throws IOException {
        gear = new int[4][8]; //톱니바퀴 수는 고정
        for(int i=0; i<4; i++){
            String temp = br.readLine();
            for(int j=0; j<8; j++){
                gear[i][j] = temp.charAt(j) - '0';
            }
        }
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            movingGear(num, dir);
        }
        int answer = sumCount();
        System.out.println(answer);
    }

    private static int sumCount() {
        int sum=0;
        for(int i=0; i<4; i++){
            if(gear[i][0]==1){
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    public static void movingGear(int num, int dir){ // 첫 기어 돌림
        Queue<node> queue= new LinkedList<>();
        boolean moved [] =new boolean[4];
        Queue<node> move= new LinkedList<>();

        queue.offer(new node(num, dir));
        move.offer(new node(num, dir));
        while(!queue.isEmpty()){
            node out = queue.poll();
            int now = out.num;


            if(moved[now]) continue;
            moved[now]=true;

            //3시방향
            if(right(moved, now)){
                move.offer(new node(now+1, out.dir*-1)) ;
                queue.offer(new node(now+1, out.dir*-1));
            }
            //9시방향
            if(left(moved, now)) {
                move.offer(new node(now - 1, out.dir * -1));
                queue.offer(new node(now - 1, out.dir * -1));
            }
        }
        while(!move.isEmpty()) {
            node m = move.poll();
            moving(m.num, m.dir);
        }
    }

    private static boolean left(boolean moved[], int now) {
        if(now < 1) return false;
        if (moved[now - 1]) return false;
        if (gear[now][6] + gear[now - 1][2] == 1) return true;
        return false;
    }
    private static boolean right(boolean moved[], int now) {
        if (now > 2) return false;
        if (moved[now + 1]) return false;
        if (gear[now][2] + gear[now + 1][6] == 1) return true;
        return false;
    }
    //해당 방향으로 한번만 돌린다.
    private static void moving( int num, int dir) {

        if (dir == -1) { //반시계
            int temp = gear[num][0];
            for (int i = 0; i < 7; i++) {
                gear[num][i] = gear[num][i + 1];
            }
            gear[num][7] = temp;
        } else { //시계
            int temp = gear[num][7];
            for (int i = 7; i > 0; i--) {
                gear[num][i] = gear[num][i - 1];
            }
            gear[num][0] = temp;
        }
        //다른 기어와 닿는 index 는 2, 6임.
        //처음 도는 num을 큐에 넣고, 움직인 기어는 새로 큐에 넣어야함.
    }
}
