import java.math.BigInteger;
import java.util.*;

public class Main {
    static ArrayList<int[]> steps= new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger count = new BigInteger("2");
        //주의!!) n이 100인 경우 long의 범위를 넘어간다
        System.out.println(count.pow(n).subtract(new BigInteger("1")));
        if(n <= 20) {
            hanoi(n, 1, 3, 2);

            for (int i = 0; i < steps.size(); i++) {
                int[] step = steps.get(i);
                System.out.println(step[0] + " " + step[1]);
            }
        }
        
    }

    private static void hanoi(int n, int from, int to, int via) {
        if(n == 1) { //하나의 원판만 남았으면 1 -> 3
            steps.add(new int[] {from, to});
        }else {
            //1.N-1개의 원판을 1 -> 2
            hanoi(n-1, from, via, to);
            steps.add(new int[] {from, to});
            //2.N-1개의 원판을 2 -> 3
            hanoi(n-1, via, to, from);
        }
    }
}
