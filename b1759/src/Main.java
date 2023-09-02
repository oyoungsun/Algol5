import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char [] all;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        all = new char[C];
        visit = new boolean[C];
        for(int i=0; i<C; i++){
            all[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(all);
        char [] make = new char[L];
        track(0, make, 0);
    }

    private static void track(int depth, char [] make, int start) {
        if(depth==L){
            if(make[0]=='a'&&make[1]=='i'){
                checkM(make);
            }
            if(checkM(make)){
                for(int i=0; i<L; i++){
                    System.out.print(make[i]);
                }
                System.out.println();
            }
            return;
        }
        for(int i=start; i<C; i++){
            if(visit[i]) continue;
            visit[i]=true;
            make[depth] = all[i];
            track(depth+1, make, i);
            visit[i]=false;
        }
    }

    private static boolean checkM(char[] make) {
        int mo=0;
        int ja=0;
        for(int i=0; i<L; i++){
            if(make[i]=='a'||make[i]=='e'||make[i]=='i'||make[i]=='o'||make[i]=='u'){
                mo++;
            }
            else ja++;
        }
        if(mo>=1&&ja>=2) return true;
        else return false;
    }
}
