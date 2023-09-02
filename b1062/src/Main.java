import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //"a, n, t, i, c"
    static int n;
    static int k;
    static boolean learn [] =new boolean[26];
    static int max=0;
    static ArrayList<String> words = new ArrayList();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        for(int i=0; i<n; i++){
            String temp = sc.next();
            temp = temp.replace("anta", "");
            temp = replace("tica", "");
            words.add(temp);
        }
        //5가지는 필수로 배워야함.

        if(k<5) max=0;
        else if(k==26) max = n;
        else{
            k=k-5;
            learn['a'-'a'] = true;
            learn['c'-'a'] = true;
            learn['t'-'a'] = true;
            learn['n'-'a'] = true;
            learn['i'-'a'] = true;
            findMax(0, 0);
        }
        System.out.println(max);
    }

    private static void findMax(int depth, int start) {
        if(depth==k){
            //max 세기
            int count=0;
            for(int i=0; i<n; i++){
                int j;
                char temp [] = words.get(i).toCharArray();
                for(j=0; j<temp.length; j++){
                    if(!learn[temp[j]-'a']) break;
                }
                if(j==temp.length) count++;
            }
            max = Math.max(count, max);
        }else{
            for(int i=start; i<26; i++){
                if(!learn[i]){
                    learn[i]=true;
                    findMax(depth+1, i);
                    learn[i]=false;
                }
            }
        }

    }
}
