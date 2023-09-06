import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static String [][] friends;
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, String> hash; //자식/부모
//union find
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t-->0){
            n = Integer.parseInt(br.readLine());
            hash = new HashMap<>();
            friends = new String[n][2];
            for(int i=0; i<n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                friends[i][0] = st.nextToken();
                friends[i][1] = st.nextToken();
                setU(friends[i][0]);
                setU(friends[i][1]);
                String p = mergeU(friends[i][0], friends[i][1]);
                sb.append(findChild(p, i+1)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static int findChild(String p, int depth) {
        int cnt=0;
        for(int i=0; i<depth; i++){
            if(hash.get(friends[i][0])==p) cnt++;
            if(hash.get(friends[i][1])==p) cnt++;
        }
        return cnt;
    }

    private static String mergeU(String s, String s1) {
        String pa1 = findU(s);
        String pa2 = findU(s1);
        String parent;
        parent = compareS(pa1, pa2) ;

        if(pa1!=pa2){
            hash.put(s, parent);
            hash.put(s1, parent);
            return parent;
        }else{
            return parent;
        }
    }

    private static String findU(String s1) {
        if(s1 == hash.get(s1)){
            return s1;
        }
        hash.put(s1, findU(hash.get(s1)));
        return hash.get(s1);
    }

    private static String compareS(String s, String s1) {
        String [] temp = new String[]{s, s1};
        Arrays.sort(temp);
        return temp[0];
    }

    private static void setU(String child) {
        if(!hash.containsKey(child))
            hash.put(child, child);
        else return;
    }

}
