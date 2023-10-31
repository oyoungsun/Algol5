import java.util.*;

class node{
      int x;
      int y;
    public node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static node[] convient;
    static node penta;
    static int conN;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        while(testcase-->0) {
            conN = sc.nextInt();
            int sx = sc.nextInt();
            int sy = sc.nextInt();
            convient = new node[conN];
            for(int i=0; i<conN; i++){
                int conx = sc.nextInt();
                int cony = sc.nextInt();
                convient[i] = new node(conx, cony);
            }

            int penx = sc.nextInt();
            int peny = sc.nextInt();
            penta = new node(penx, peny);
            if(bfs(new node(sx, sy))){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }
        }

    }

    private static boolean bfs(node start) {

        boolean visited [] = new boolean[conN];
        Queue<node> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            node now = q.poll();
            if(Math.abs(now.x-penta.x) + Math.abs(now.y-penta.y) <= 1000) {
                return true;
            }
            for(int i=0; i<conN; i++){
                if(!visited[i]) {
                    int nx = convient[i].x;
                    int ny = convient[i].y;
                    int distance = Math.abs(now.x - nx) + Math.abs(now.y - ny);
                    if (distance <= 1000) {
                        visited[i] = true;
                        q.add(new node(nx, ny));
                    }
                }
            }
        }
        return false;
    }
}
