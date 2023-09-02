import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

class Apart{
    int num;
    int high;
    public Apart(int num, int high){
        this.num=num;
        this.high = high;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int array[] = new int[n];
        int count[] = new int[n];
        int dis[] = new int[n];
        Arrays.fill(dis, 100001);
        Stack<Apart> left = new Stack<>();
        Stack<Apart> right = new Stack<>();
        for(int i=0; i<n; i++){
            array[i] = sc.nextInt();
        }

        for(int i=0; i<n; i++){
            int now = array[i];
            while(!left.isEmpty()){
                if(left.peek().high>now){
                    if(Math.abs(left.peek().num-(i+1))<Math.abs(dis[i]-(i+1))){
                        dis[i] = left.peek().num;
                    }
                    break;
                }else{
                    left.pop();
                }
            }
            count[i] += left.size();
            if(!left.isEmpty()) {
                int gap = Math.abs(left.peek().num - (i + 1));
                if (gap < dis[i]) { // Index 차이가 더 적다면 무조건 바꾼다.
                    dis[i] = left.peek().num;
                }
            }
            left.push(new Apart(i+1, now));
        }

        for(int i=n-1; i>=0; i--){
            int now = array[i];
            while(!right.isEmpty()){
                if(right.peek().high>now){
                    if(right.peek().num-(i+1)<dis[i]-(i+1)){
                        dis[i] = right.peek().num;
                    }
                    break;
                }else{
                    right.pop();
                }
            }
            count[i] += right.size();
            right.push(new Apart(i+1, now));

        }
        for(int i=0; i<n; i++){
            if(count[i]!=0) {
                System.out.println(count[i]+" "+dis[i]);
            }else{
                System.out.println(0);
            }
        }
    }
}
