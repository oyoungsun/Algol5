import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> q = new LinkedList<>();
        Stack<Integer> s = new Stack<>();

        for(int i=0; i<n; i++){
            q.add(sc.nextInt());
        }
        int temp=1;
        while(!q.isEmpty()) {
            if(q.peek()==temp){
                q.poll();
                temp++;
            }else if(!s.isEmpty()&&s.peek()==temp){
                s.pop();
                temp++;
            }
            else{
                s.push(q.poll());
            }
        }
        while(!s.isEmpty()){
            if(s.peek()==temp) {
                s.pop();
                temp++;
            }else{
                System.out.println("Sad");
                return;
            }
        }
        System.out.println("Nice");
    }
}
