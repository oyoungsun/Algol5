import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st= new StringTokenizer(br.readLine());
        ArrayList<int []> boomlist = new ArrayList<>();
        int time=0;
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken()); //n초
        int map[][] = new int[R][C];
        for(int i=0; i<R; i++){
            String temp = br.readLine();
            for(int j=0; j<C; j++){
                char c = temp.charAt(j);
                if(c=='O'){
                    map[i][j]=1;
                    boomlist.add(new int[]{i,j});
                }
                else map[i][j]=0;
            }
        }
        for(int t=0; t<N; t++){
            t +=1; //1초간 아무것도 안함
            boom();

        }

    }
}
